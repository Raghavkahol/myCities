package com.example.keeptruckin

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

open class BaseViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val lifecycleState = createPublishSubject<ViewModelLifecycleState>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun bindDisposable(action: () -> Disposable) {
        compositeDisposable.add(action())
    }

    open fun onBind() {
    }
}

fun <T> createPublishSubject(): PublishSubject<T> = PublishSubject.create()

fun <T> PublishSubject<T>.hasNoObservers(): Boolean = !this.hasObservers()