package com.devstruktor.motion.session

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.lifecycle.LifecycleOwner
import com.devstruktor.motion.AngleListener
import com.devstruktor.motion.MoveListener

class RotationDetectorSession(context: Context, lifecycleOwner: LifecycleOwner, samplingPeriod: Int,
                              private val yAngleListener: AngleListener? = null,
                              private val xAngleListener: AngleListener? = null,
                              listener: MoveListener? = null)
    : BaseMotionDetectorSession(context, lifecycleOwner, samplingPeriod, listener) {


    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    override val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR)

    private val orientation = FloatArray(3)
    private val rotationMatrix = FloatArray(9)

    private var lastAccuracy = SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM


    override fun onNewValues(x: Float, y: Float, z: Float, values: FloatArray) {


        SensorManager.getRotationMatrixFromVector(rotationMatrix, values)
        SensorManager.getOrientation(rotationMatrix, orientation)
        if (yAngleListener != null) {
            val v1 = orientation[1]
            val v1Calc = ((Math.toDegrees(v1.toDouble()) + 90).toInt()).toFloat()
            yAngleListener.invoke(v1Calc)
        }
        if(xAngleListener!=null)
        {
            val v1 = orientation[2]
            val v1Calc = ((Math.toDegrees(v1.toDouble()) + 90).toInt()).toFloat()
            xAngleListener.invoke(v1Calc)
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        lastAccuracy = p1

        when (lastAccuracy) {
            3 -> println("Acc high")
            2 -> println("Acc low")
            1 -> println("Acc medium")
        }


    }


}