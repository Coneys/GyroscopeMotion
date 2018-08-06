package com.devstruktor.motion

class ForceValue(val horizontalMultiplier: Float, val verticalMultiplier: Float)
class Force {
    var verticalMultiplier: Float = 1f
    var horizontalMultiplier: Float = 1f

    fun build() = ForceValue(horizontalMultiplier, verticalMultiplier)
}
