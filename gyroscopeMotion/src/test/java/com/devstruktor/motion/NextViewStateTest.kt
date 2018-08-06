package com.devstruktor.motion

import org.junit.Test

class NextViewStateTest {

    @Test
    fun should_not_be_blocked_both_grater_than_0() {

        val state = NextViewState(-1, -1, 5f, 5f)
        assert(!state.shouldXBeBlockedByBarrier(6f))

    }

    @Test
    fun should_be_blocked_both_grater_than_0() {

        val state = NextViewState(-1, -1, 8f, 5f)
        assert(state.shouldXBeBlockedByBarrier(6f))

    }

    @Test
    fun should_be_blocked_both_lower_than_0() {

        val state = NextViewState(-1, -1, -10f, 5f)
        assert(state.shouldXBeBlockedByBarrier(-8f))

    }

    @Test
    fun should_not_be_blocked_both_lower_than_0() {

        val state = NextViewState(-1, -1, -10f, 5f)
        assert(!state.shouldXBeBlockedByBarrier(-12f))

    }

    @Test
    fun should_not_be_blocked_if_barrier_is_greater_than_0() {
        val state = NextViewState(-1, -1, -10f, 5f)
        assert(!state.shouldXBeBlockedByBarrier(4f))
    }

    @Test
    fun should_not_be_blocked_if_translation_is_greater_than_0() {
        val state = NextViewState(-1, -1, 2f, 5f)
        assert(!state.shouldXBeBlockedByBarrier(-4f))
    }
}