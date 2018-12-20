package com.devstruktor.easymotion

import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devstruktor.motion.moveWithGyroscope
import com.devstruktor.motion.session.RotationDetectorSession
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveWithGyroscope(text_view) {

            applyForce {
                horizontalMultiplier = 2f
                verticalMultiplier = 2f
            }

            moveListener { x: Float, y: Float, z: Float ->
                println("MOVED X: $x Y: $y Z: $z")

            }

        }.start(this, this)

        RotationDetectorSession(this, this, SensorManager.SENSOR_DELAY_GAME, {
        },{
            println("Angle X $it")
        })

    }
}
