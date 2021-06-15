package com.ibnsina.utils.interfaces

import com.ibnsina.utils.logE

@Suppress("unused")
open class LogLifecycleObserver(private val tag: String = "LogLifecycleObserver") : LifecycleObserver{

    override fun onStart() {
        logE(tag, "onStart" + " :  " + System.currentTimeMillis())
    }

    override fun onResume() {
        logE(tag, "onResume" + " :  " + System.currentTimeMillis())
    }

    override fun onCreate() {
        logE(tag, "onCreate" + " :  " + System.currentTimeMillis())
    }

    override fun onPause() {
        logE(tag, "onPause" + " :  " + System.currentTimeMillis())
    }

    override fun onStop() {
        logE(tag, "onStop" + " :  " + System.currentTimeMillis())
    }

    override fun onDestroy() {
        logE(tag, "onDestroy" + " :  " + System.currentTimeMillis())
    }

    override fun onSaveInstanceState() {
        logE(tag, "onSaveInstanceState" + " :  " + System.currentTimeMillis())
    }

    override fun onAttach() {
        logE(tag, "onAttach" + " :  " + System.currentTimeMillis())
    }

    override fun onViewCreated() {
        logE(tag, "onViewCreated" + " :  " + System.currentTimeMillis())
    }

    override fun onCreateView() {
        logE(tag, "onCreateView" + " :  " + System.currentTimeMillis())
    }

    override fun onDestroyView() {
        logE(tag, "onDestroyView" + " :  " + System.currentTimeMillis())
    }
}