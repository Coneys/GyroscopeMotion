package com.devstruktor.motion

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class MotionDetectorSession(val context: Context, val lifecycleOwner: LifecycleOwner,
                            val samplingPeriod: Int,
                            val requests: List<RegistrationRequest>)
    : SensorEventListener, LifecycleObserver, MotionSession {


    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerator = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val x = event.values[1]
            val y = event.values[0]

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

    }

    private fun getTempViewState(nextViewState: NextViewState, force: ForceValue, x: Float, y: Float): NextViewState {
        val nextTranslationX = x * (context.dip(force.horizontalMultiplier))
        val nextTranslationY = y * (context.dip(force.verticalMultiplier))

        println("NEXT Y TRANSLATION $nextTranslationY with $y and mult ${force.verticalMultiplier} dip from mult ${context.dip(force.verticalMultiplier)}")

        return nextViewState.copy(translationX = nextViewState.translationX + nextTranslationX,
                translationY = nextViewState.translationY + nextTranslationY)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun doOnPause() {
        sensorManager.unregisterListener(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun doOnResume() {
        sensorManager.registerListener(this, accelerator,
                samplingPeriod)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun stopListening() {
        sensorManager.unregisterListener(this)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    fun Context.dip(px: Int) = dip(px.toFloat())
    fun Context.dip(px: Float) = (px * resources.displayMetrics.density)
    fun Context.dipAndRound(px: Float) = (px * resources.displayMetrics.density).toInt()


}