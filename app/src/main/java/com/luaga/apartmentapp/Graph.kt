package com.luaga.apartmentapp

import android.content.Context
import androidx.room.Room
import com.luaga.apartmentapp.data.ApartmentDatabase
import com.luaga.apartmentapp.data.ApartmentRespository

object Graph {
    lateinit var database: ApartmentDatabase

    val apartmentRepository by lazy{
        ApartmentRespository(apartmentDao = database.apartmentDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, ApartmentDatabase::class.java, "apartmentlist.db").build()
    }

}