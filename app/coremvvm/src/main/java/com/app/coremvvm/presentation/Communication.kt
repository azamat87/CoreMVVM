package com.app.coremvvm.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.coremvvm.core.Mapper

class Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Update<T> : Mapper.Unit<T>

    interface Mutable<T> : Observe<T>, Update<T>

    abstract class Abstract<T>(protected val mutableLiveData: MutableLiveData<T>): Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            mutableLiveData.observe(owner, observer)
        }
    }

    abstract class UiUpdate<T: Any>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun map(data: T) {
            mutableLiveData.value = data
        }
    }

    abstract class PostUpdate<T: Any>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun map(data: T) = mutableLiveData.postValue(data)
    }

    abstract class SingleUiUpdate<T: Any> : UiUpdate<T>(SingleLiveEvent())

    abstract class SinglePostUpdate<T: Any> : PostUpdate<T>(SingleLiveEvent())


    class Empty<T> : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) = Unit

        override fun map(data: T) = Unit

    }

}