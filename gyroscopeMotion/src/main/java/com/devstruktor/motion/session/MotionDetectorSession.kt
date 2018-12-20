package com.devstruktor.motion.session

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.LifecycleOwner
import com.devstruktor.motion.ForceValue
import com.devstruktor.motion.MoveListener
import com.devstruktor.motion.NextViewState
import com.devstruktor.motion.RegistrationRequest

class MotionDetectorSession(val requests: List<RegistrationRequest>, context: Context, lifecycleOwner: LifecycleOwner, samplingPeriod: Int, listener: MoveListener?)
    : BaseMotionDetectorSession(context, lifecycleOwner, samplingPeriod, listener) {


    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    override val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    var xSum:Float = 0f
    var ySum:Float = 0f

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onNewValues(x: Float, y: Float, z: Float, values: FloatArray) {
        xSum+=x
        ySum+=y

        println("XSUM $xSum YSUM $ySum")
        requests.forEach {
            it.apply {

                val temp = getTempViewState(nextViewState, force, x, y)

                if (!barriers.any { it.isBlockedHorizontally(temp) }) {
                    view.translationX = temp.translationX
                    nextViewState.translationX = temp.translationX
                }
                if (!barriers.any { it.isBlockedVertically(temp) }) {
                    view.translationY = temp.translationY
                    nextViewState.translationY = temp.translationY
                }
            }
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    private fun getTempViewState(nextViewState: NextViewState, force: ForceValue, x: Float, y: Float): NextViewState {
        val nextTranslationX = x * (context.dip(force.horizontalMultiplier))
        val nextTranslationY = y * (context.dip(force.verticalMultiplier))

        return nextViewState.copy(translationX = nextViewState.translationX + nextTranslationX,
                translationY = nextViewState.translationY + nextTranslationY)
    }
}