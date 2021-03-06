package com.ibnsina.utils.interfaces

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

interface LifecycleObserver : androidx.lifecycle.LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

    fun onSaveInstanceState()

    fun onAttach()

    fun onViewCreated()

    fun onCreateView()

    fun onDestroyView()
}