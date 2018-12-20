package com.devstruktor.motion.session

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.devstruktor.motion.MotionSession
import com.devstruktor.motion.MoveListener

abstract class BaseMotionDetectorSession(val context: Context, val lifecycleOwner: LifecycleOwner,
                                         val samplingPeriod: Int, val listener: MoveListener?)
    : SensorEventListener, LifecycleObserver, MotionSession {

    abstract val sensor :Sensor
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    abstract fun onNewValues(x: Float, y: Float, z: Float, values: FloatArray)

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == sensor.type) {
            val x = event.values[1]
            val y = event.values[0]
            val z = event.values[2]

            listener?.invoke(x, y, z)

            onNewValues(x,y,z,event.values)

        }
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun doOnPause() {
        sensorManager.unregisterListener(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun doOnResume() {
        sensorManager.registerListener(this, sensor, samplingPeriod)
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