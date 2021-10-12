package com.example.realtimedatabasekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimedatabasekotlin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ListingsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var listings: List<Listing>

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true) //Allowing Offline Functionality
        viewModel = ViewModelProvider(this).get(ListingsViewModel::class.java)
        this.listings = ArrayList()

        getResponseRx()

        //implementing binding to interact with views, connecting backend to ui attributes
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
    }

    //Retrieving info from Firebase
    private fun getResponse() {
        viewModel.getResponse(object: FirebaseCallback {
            override fun onResponse(response: Response) {
                listings = response.listings!!
                adapter = RecyclerAdapter(listings)
                binding.recyclerView.adapter = adapter
            }
        })
    }

    //Retrieving info from database using ReactiveX to subscribe to a Single Observable
    private fun getResponseRx() {
        viewModel.getResponseRx().subscribe{res ->
            res.listings!!.also { listings = it }
            adapter = RecyclerAdapter(this.listings)
            binding.recyclerView.adapter = adapter
        }
    }
}



