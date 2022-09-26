package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable

class SoundViewModel : BaseObservable() {
    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    val title: String?
        get() = sound?.name
}