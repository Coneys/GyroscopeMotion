package com.devstruktor.motion

import java.util.*

class Barrier {

    var barriers: MutableList<BarrierValue> = ArrayList()

  /*  fun viewOnPosition(x: Int, y: Int) {

    }

    fun viewOnPositionX(x: Int) {

    }

    fun viewOnPositionY(y: Int) {

    }*/

    fun viewMovedVerticallyBy(y: Float) {
        barriers.add(BarrierValue.Movement(yInDp = y))
    }

    fun viewMovedHorizontallyBy(x: Float) {
        barriers.add(BarrierValue.Movement(x))
    }

    fun viewMovedBy(x: Float, y: Float) {
        barriers.add(BarrierValue.Movement(x, y))
    }


    fun build(): List<BarrierValue> {
        return barriers
    }

}