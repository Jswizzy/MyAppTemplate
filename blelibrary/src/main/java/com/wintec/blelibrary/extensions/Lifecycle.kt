package com.wintec.blelibrary.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

inline fun LifecycleOwner.ifStateAtLeastCreated(block: LifecycleOwner.() -> Unit ) {
    ifStateAtLeast(Lifecycle.State.CREATED, block)
}

inline fun LifecycleOwner.ifStateAtLeast(state: Lifecycle.State, block: LifecycleOwner.() -> Unit) {
    if (stateIsAtLeast(state)) {
        block()
    }
}

fun LifecycleOwner.stateIsAtLeast(state: Lifecycle.State) = lifecycle.currentState.isAtLeast(state)