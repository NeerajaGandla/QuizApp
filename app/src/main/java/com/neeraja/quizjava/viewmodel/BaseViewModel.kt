package com.neeraja.quizjava.viewmodel

import androidx.lifecycle.LiveData

interface BaseViewModel {
    fun getPosition(): LiveData<Int>
    fun savePosition(position: Int)
    fun getTime(): LiveData<Long>
    fun saveTime(time: Long)
}