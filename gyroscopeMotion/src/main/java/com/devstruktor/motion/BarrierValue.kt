package com.devstruktor.motion

sealed class BarrierValue {

    abstract fun isBlockedVertically(state: NextViewState): Boolean
    abstract fun isBlockedHorizontally(state: NextViewState): Boolean

    internal class Position : BarrierValue() {
        override fun isBlockedVertically(state: NextViewState): Boolean {
            return true
        }

        override fun isBlockedHorizontally(state: NextViewState): Boolean {
            return true
        }
    }

    internal class Movement(val xInDp: Float = -1f, val yInDp: Float = -1f) : BarrierValue() {
        override fun isBlockedVertically(state: NextViewState): Boolean {
            return state.shouldYBeBlockedByBarrier(yInDp)
        }

        override fun isBlockedHorizontally(state: NextViewState): Boolean {
            return state.shouldXBeBlockedByBarrier(xInDp)

        }

    }

}


