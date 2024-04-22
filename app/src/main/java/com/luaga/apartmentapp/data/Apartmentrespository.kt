package com.luaga.apartmentapp.data

import kotlinx.coroutines.flow.Flow

class ApartmentRespository(private val apartmentDao: ApartmentDao) {

    suspend fun addAApartment(apartment: Apartment){
        apartmentDao.addAApartment(apartment)
    }

    fun getApartments(): Flow<List<Apartment>> = apartmentDao.getAllApartment()

    fun getAApartmentById(id:Long) :Flow<Apartment> {
        return apartmentDao.getAApartmentById(id)
    }

    suspend fun updateAApartment(apartment: Apartment){
        apartmentDao.updateAApartment(apartment)
    }

    suspend fun deleteAApartment(apartment: Apartment){
        apartmentDao.deleteAApartment(apartment)
    }

}