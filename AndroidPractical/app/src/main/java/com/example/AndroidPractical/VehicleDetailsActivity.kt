package com.example.realtimedatabasekotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimedatabasekotlin.databinding.ActivityVehicleDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_layout.view.*
import java.text.DecimalFormat
import java.util.*

class VehicleDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleDetailsBinding
    private val formatter = DecimalFormat("#,###")

    companion object {
        private const val LISTING_KEY = "LISTING"
    }

    //Passing info to attributes in Scrollable view for user to see all data on vehicle and call dealer button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_details)
        binding = ActivityVehicleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vehicleInfo = intent.getSerializableExtra(LISTING_KEY) as? Listing

        if (vehicleInfo != null) {
            Picasso.get().load(vehicleInfo.images?.firstPhoto?.large).into(this.binding.ivVehicleImage)
            this.binding.tvYearMakeModleDetails.text = vehicleInfo.year.toString() + " " + vehicleInfo.make + " " + vehicleInfo.model + " " + vehicleInfo.trim
            this.binding.tvPriceDistDetails.text = "$ " + formatter.format(vehicleInfo.listPrice) + " | " + formatter.format(
                vehicleInfo.mileage
            ) + " mi"

            this.binding.tvLocationDetails.text = vehicleInfo.dealer?.state
            this.binding.tvIntColorDetails.text =  vehicleInfo.interiorColor
            this.binding.tvExColorDetails.text = vehicleInfo.exteriorColor
            this.binding.tvDriveType.text = vehicleInfo.drivetype
            this.binding.tvTransmissionDetails.text = vehicleInfo.transmission
            this.binding.tvEngineDetails.text = vehicleInfo.engine
            this.binding.tvBodyType.text = vehicleInfo.bodytype

            this.binding.btnCallDealerDetails.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + vehicleInfo.dealer?.phone)
                this.startActivity(intent)
            }
        }


    }
}