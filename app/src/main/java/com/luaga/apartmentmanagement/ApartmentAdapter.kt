package com.luaga.apartmentmanagement

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.luaga.apartmentmanagement.data.Apartments
import com.luaga.apartmentmanagement.databinding.ApartmentsItemBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class ApartmentsAdapter(
    var context: Context,
    var apartmentList:ArrayList<Apartments>
) : RecyclerView.Adapter<ApartmentsAdapter.ApartmentsViewHolder>(){
    inner class  ApartmentsViewHolder(val adapterBinding : ApartmentsItemBinding) : RecyclerView.ViewHolder(adapterBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentsViewHolder {
        val binding = ApartmentsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ApartmentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return apartmentList.size
    }

    override fun onBindViewHolder(holder: ApartmentsViewHolder, position: Int) {
        holder.adapterBinding.apartmentNumber.text = "Căn hộ: ${apartmentList[position].apartmentNumber}"
//        holder.adapterBinding.area.text = apartmentList[position].area.toString()
//        holder.adapterBinding.floor.text = apartmentList[position].floor.toString()
//        holder.adapterBinding.numberBedrooms.text = apartmentList[position].numBedrooms.toString()
//        holder.adapterBinding.numberBathrooms.text = apartmentList[position].numBathrooms.toString()
//        holder.adapterBinding.price.text = apartmentList[position].price.toString()
//        holder.adapterBinding.priceInternet.text = apartmentList[position].priceInternet.toString()
//        holder.adapterBinding.priceGarbage.text = apartmentList[position].priceGarbage.toString()
//        holder.adapterBinding.gymService.isChecked = apartmentList[position].gymService
//        holder.adapterBinding.laundryService.isChecked = apartmentList[position].laundryService
//        holder.adapterBinding.parkingService.isChecked = apartmentList[position].parkingService
//        holder.adapterBinding.swimmingService.isChecked = apartmentList[position].swimmingService

        val imagUrl = apartmentList[position].url
        Picasso.get().load(imagUrl).into(holder.adapterBinding.imageView2, object : Callback{
            override fun onSuccess() {
                holder.adapterBinding.progressBar.visibility = View.INVISIBLE
            }

            override fun onError(e: Exception?) {
                Toast.makeText(context, e?.localizedMessage,Toast.LENGTH_SHORT).show()
            }
        })

        holder.adapterBinding.linearLayout.setOnClickListener{
            val intent = Intent(context,UpdateApartmentActivity::class.java)
            intent.putExtra("id",apartmentList[position].apartmentId)
            intent.putExtra("apartmentNumber",apartmentList[position].apartmentNumber)
            intent.putExtra("area",apartmentList[position].area)
            intent.putExtra("floor",apartmentList[position].floor)
            intent.putExtra("numberBedrooms",apartmentList[position].numBedrooms)
            intent.putExtra("numberBathrooms",apartmentList[position].numBathrooms)
            intent.putExtra("price",apartmentList[position].price)
            intent.putExtra("priceInternet",apartmentList[position].priceInternet)
            intent.putExtra("priceGarbage",apartmentList[position].priceGarbage)
            intent.putExtra("gymService",apartmentList[position].gymService)
            intent.putExtra("laundryService",apartmentList[position].laundryService)
            intent.putExtra("parkingService",apartmentList[position].parkingService)
            intent.putExtra("swimmingService",apartmentList[position].swimmingService)
            intent.putExtra("imageUrl",imagUrl)
            intent.putExtra("imageName",apartmentList[position].imageName)
            context.startActivity(intent)
        }
        // Trình nghe sự kiện cho nút "Thông tin người thuê"
        holder.adapterBinding.button.setOnClickListener {
            val intent = Intent(context, UserInformationActivity::class.java)
            intent.putExtra("apartmentNumber", apartmentList[position].apartmentNumber)
            intent.putExtra("area", apartmentList[position].area)
            intent.putExtra("floor", apartmentList[position].floor)
            intent.putExtra("numberBedrooms",apartmentList[position].numBedrooms)
            intent.putExtra("numberBathrooms",apartmentList[position].numBathrooms)
            intent.putExtra("price",apartmentList[position].price)
            intent.putExtra("priceInternet",apartmentList[position].priceInternet)
            intent.putExtra("priceGarbage",apartmentList[position].priceGarbage)
            intent.putExtra("gymService",apartmentList[position].gymService)
            intent.putExtra("laundryService",apartmentList[position].laundryService)
            intent.putExtra("parkingService",apartmentList[position].parkingService)
            intent.putExtra("swimmingService",apartmentList[position].swimmingService)
            intent.putExtra("imageUrl",imagUrl)
            intent.putExtra("imageName",apartmentList[position].imageName)
            context.startActivity(intent)
        }
    }
    fun getApartmentId(position: Int): String{
        return apartmentList[position].apartmentId
    }
    fun getImageName(position: Int): String {
        return apartmentList[position].imageName
    }
}