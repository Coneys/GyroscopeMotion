package com.devstruktor.motion

import android.content.Context
import android.hardware.SensorManager
import android.view.View
import androidx.lifecycle.LifecycleOwner

class GyroscopeRegistration internal constructor(private val views: List<View>, val barriers: List<BarrierValue>, private val force: ForceValue) {


    fun start(context: Context,
              lifecycleOwner: LifecycleOwner,
              samplingPeriod: Int = SensorManager.SENSOR_DELAY_GAME)
            : MotionSession {

        return MotionDetectorSession(context, lifecycleOwner, samplingPeriod, toRegistrationRequest())
    }

    internal fun toRegistrationRequest(): List<RegistrationRequest> {
        return views.map { RegistrationRequest(NextViewState(it.x.toInt(), it.y.toInt()), it, barriers, force) }
    }


}

class GyroscopeMovement(var views: List<View>) {


    var barriers: List<BarrierValue> = ArrayList()
    var force: ForceValue? = null

    fun build(): GyroscopeRegistration {
        return GyroscopeRegistration(views, barriers, force ?: ForceValue(1f, 1f))
    }


    fun blockWhen(block: Barrier.() -> Unit) {
        barriers = Barrier().apply(block).build()
    }

    fun applyForce(block: Force.() -> Unit) {
        force = Force().apply(block).build()
    }


}


class RegistrationRequest(val nextViewState: NextViewState, val view: View, val barriers: List<BarrierValue>, val force: ForceValue)


fun moveWithGyroscope(vararg view: View, block: GyroscopeMovement.() -> Unit): GyroscopeRegistration {
    return GyroscopeMovement(view.toList()).apply(block).build()
}

fun startMovingWithGyroscope(context: Context,
                             lifecycleOwner: LifecycleOwner,
                             vararg registration: GyroscopeRegistration, samplingPeriod: Int = SensorManager.SENSOR_DELAY_GAME ): MotionDetectorSession {

    val requests = registration.flatMap { it.toRegistrationRequest() }
    return MotionDetectorSession(context, lifecycleOwner, samplingPeriod, requests)
}


