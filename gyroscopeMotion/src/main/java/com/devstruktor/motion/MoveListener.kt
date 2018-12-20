package com.devstruktor.motion

typealias MoveListener = (x: Float, y: Float, z: Float) -> Unit
typealias AngleListener = (angle: Float) -> Unit

fun mergeMoveListeners(moveListeners: Collection<MoveListener?>): MoveListener {
    val mergedListener: MoveListener = { x: Float, y: Float, z: Float ->
        moveListeners.forEach { it?.invoke(x, y, z) }
    }

    return mergedListener
}