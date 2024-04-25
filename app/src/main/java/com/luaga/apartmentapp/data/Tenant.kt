package com.luaga.apartmentapp.data

data class Tenant(
    val name: String,
    val email: String,
    val phone: String,
    val idCardNumber: String,
    val leaseStartDate: String,
    val leaseEndDate: String,
    val permanentAddress: String
)