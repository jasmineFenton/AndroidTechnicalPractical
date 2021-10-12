package com.example.realtimedatabasekotlin

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*
import java.text.DecimalFormat

class RecyclerAdapter(private val listings: List<Listing>) : RecyclerView.Adapter<RecyclerAdapter.ListingHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingHolder {
        val inflatedView = parent.inflate(R.layout.card_layout, false)
        return ListingHolder(inflatedView)

    }
    override fun getItemCount(): Int {
        return listings.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ListingHolder, position: Int)
    {
        val listing = listings[position]
        holder.bindListing(listing)
    }

    class ListingHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var listing = Listing()
        init {
            v.setOnClickListener(this)
        }

        companion object {
            private const val LISTING_KEY = "LISTING"
        }

        fun bindListing(listing: Listing) {
            this.listing = listing
            val formatter = DecimalFormat("#,###")

            Picasso.get().load(listing.images?.firstPhoto?.large).into(this.view.vehicleImage)

            //Receiving and outputting the Vehicles basic data to user in a card view
            this.view.tvVehicleYearMakeModel.text = listing.year.toString() + " " + listing.make + " " + listing.model + " " + listing.trim
            this.view.tvPriceAndMileage.text = "$ " + formatter.format(listing.listPrice) + " | " + formatter.format(
                listing.mileage
            ) + " mi"
            this.view.tvLocation.text = listing.dealer?.address + " " + listing.dealer?.state

            //Adding a Click Listener to the card's button to trigger action (phone) to open, to call dealer
            this.view.btnCallDealer.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + listing.dealer?.phone)
                this.view.context!!.startActivity(intent)
            }

        }

        //onClick to trigger new activity when vehicle card is tapped on
        override fun onClick(v: View?) {

            val intent = Intent(this.view.context, VehicleDetailsActivity::class.java)
            intent.putExtra(LISTING_KEY, this.listing)
            this.view.context!!.startActivity(intent)

        }
    }
}
