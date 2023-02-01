package com.bignerdranch.android.jetpack4fragmentlshardlivedatatest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ShareDataViewModel : ViewModel() {
    private var progress: MutableLiveData<Int>? = null
    fun getProgress(): LiveData<Int> {
        if (progress == null) {
            progress = MutableLiveData()
        }
        return progress as MutableLiveData<Int>
    }

    // 由于屏幕旋转导致的Activity重建，该方法不会被调用, 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源
    override fun onCleared() {
        super.onCleared()
        progress = null
    }
}