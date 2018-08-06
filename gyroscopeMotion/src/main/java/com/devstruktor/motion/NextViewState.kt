package com.devstruktor.motion

data class NextViewState(val positionX: Int, val positionY: Int,
                         var translationX: Float = 0f, var translationY: Float = 0f) {
    fun shouldXBeBlockedByBarrier(barrierX: Float): Boolean {
        return shouldBeBlockedByBarrier(barrierX, translationX)
    }

    fun shouldYBeBlockedByBarrier(barrierY: Float): Boolean {
        return shouldBeBlockedByBarrier(barrierY, translationY)
    }

    private fun shouldBeBlockedByBarrier(barrier: Float, translation: Float): Boolean {
        return when {
            barrier == -1f -> false
            barrier * translation < 0 -> false
            barrier < 0 -> translation < barrier
            barrier > 0 -> translation > barrier
            else -> true
        }
    }
}