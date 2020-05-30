package com.example.keeptruckin

import android.content.Intent

sealed class ViewModelLifecycleState{
    data class StartWithRequest(val request: Int) : ViewModelLifecycleState()
}

