package com.luaga.apartmentmanagement.data

data class Apartments(
    val apartmentId: String = "",
    val apartmentNumber: String= "",
    val floor: Number = 0,
    val area: Number = 0,
    val price: Number = 0,
    val priceGarbage: Number = 0,
    val priceInternet: Number = 0,
    val numBedrooms: Number = 0,
    val numBathrooms: Number = 0,
    val gymService: Boolean = false,
    val laundryService: Boolean= false,
    val parkingService: Boolean= false,
    val swimmingService: Boolean= false
) {

}