package com.luaga.apartmentmanagement.data

data class Apartments(
    val apartmentId: String = "",
    val apartmentNumber: String= "",
    val floor: Int = 0,
    val area: Double = 0.0,
    val price: Double = 0.0,
    val priceGarbage: Double = 0.0,
    val priceInternet: Double = 0.0,
    val numBedrooms: Int = 0,
    val numBathrooms: Int = 0,
    val gymService: Boolean = false,
    val laundryService: Boolean= false,
    val parkingService: Boolean= false,
    val swimmingService: Boolean= false
) {

}
