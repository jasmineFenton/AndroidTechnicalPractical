package com.example.realtimedatabasekotlin

import androidx.lifecycle.ViewModel
import io.reactivex.Single

class ListingsViewModel(private val repository: ListingRepository = ListingRepository()): ViewModel() {
    fun getResponse(callback: FirebaseCallback) {
        repository.getResponse(callback)
    }

    fun getResponseRx(): Single<Response> {
        return repository.getResponseRx()
    }
}