package com.example.realtimedatabasekotlin

import java.io.Serializable

interface FirebaseCallback {
    fun onResponse(response: Response)
}

data class Response(var listings: List<Listing>? = null, var exception: Exception? = null) {}

data class Dealer(var address: String? = null, var backfill: Boolean? = null, var carfaxId: String? = null, var cfxMicrositeUrl: String? = null,
                  var city: String? = null, var dealerAverageRating: Double? = null, var dealerInventoryUrl: String? = null, var dealerLeadType: String? = null,
                  var dealerReviewComments: String? = null, var dealerReviewCount: Double? = null, var dealerReviewDate: String? = null, var dealerReviewRating: Long? = null,
                  var dealerReviewReviewer: String? = null, var latitude: String? = null, var longitude: String? = null, var name: String? = null, var onlineOnly: Boolean? = null,
                  var phone: String? = null, var state: String? = null, var zip: String? = null) : Serializable {}

data class FirstPhoto(var large: String? = null, var medium: String? = null, var small: String? = null) : Serializable {}

data class Images(var baseUrl: String? = null, var firstPhoto: FirstPhoto? = null, var large: List<String>? = null, var medium: List<String>? = null, var small: List<String>? = null) : Serializable {}

data class Listing(var dealer: Dealer? = null, var drivetype: String? = null, var bodytype: String? = null, var engine: String? = null, var exteriorColor: String? = null,
                   var id: String? = null, var images: Images? = null, var interiorColor: String? = null, var listPrice: Int? = null, var currentPrice: Double? = null,
                   var make: String? = null, var mileage: Long? = null, var model: String? = null, var transmission: String? = null, var trim: String? = null,
                   var vin: String? = null, var year: Long? = null) : Serializable {}