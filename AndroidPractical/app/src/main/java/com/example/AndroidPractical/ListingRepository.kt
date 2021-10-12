package com.example.realtimedatabasekotlin

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Single

class ListingRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://carfax-for-consumers.firebaseio.com/"),
    private val listingsRef: DatabaseReference = rootRef.child("assignment")
) {
    fun getResponse(callback: FirebaseCallback) {
        listingsRef.get().addOnCompleteListener { task ->
            var response = Response()
            if (task.isSuccessful) {
                val result = task.result
                // result is a List<DataSnapshot>- need to map each DataSnapshot object into a Listing object
                result?.let {
                    response = result.getValue(Response::class.java)!!
                }
            }
            else {
                response.exception = task.exception

            }
            callback.onResponse(response)
        }
    }

    //Retrieving info from database using ReactiveX and returns a single
    fun getResponseRx(): Single<Response> {
        return Single.create { emitter ->
            listingsRef.get().addOnSuccessListener { result ->
                val res = result.getValue(Response::class.java)
                if (res != null) {
                    emitter.onSuccess(res)
                }
            }
                .addOnFailureListener { e ->
                    print("Error getting tasks: $e")
                    emitter.onError(e)
                }
        }
    }
}