package com.luaga.apartmentapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luaga.apartmentapp.Graph
import com.luaga.apartmentapp.data.Apartment
import com.luaga.apartmentapp.data.ApartmentRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//name = "Alice Smith",
//contact = "alice.smith@example.com",
//phone = "555-123-4567",
//identityCardNumber = "GHI789012",
//moveInDate = "2023-07-01",
//leaseExpiryDate = "2024-06-30"
class ApartmentViewModel(
    private val apartmentRespository: ApartmentRespository = Graph.apartmentRepository
):ViewModel(){
    var apartmentNumberState by mutableStateOf("")
    var floorState by mutableStateOf(0)
    var ownerIdState by mutableStateOf(0)
    var numBedroomsState by mutableStateOf(0)
    var numBathroomsState by mutableStateOf(0)
    var areaSqftState by mutableStateOf(0)
    var rentState by mutableStateOf(0)
    var internetFeeState by mutableStateOf(0)
    var garbageFeeState by mutableStateOf(0)
    var statusState by mutableStateOf("")
    var waterUsageCurrentState by mutableStateOf(0)
    var bedsQuantityState by mutableStateOf(0)
    var bedsDamagedState by mutableStateOf(0)
    var sofasQuantityState by mutableStateOf(0)
    var sofasDamagedState by mutableStateOf(0)
    var tablesQuantityState by mutableStateOf(0)
    var tablesDamagedState by mutableStateOf(0)
    var chairsQuantityState by mutableStateOf(0)
    var chairsDamagedState by mutableStateOf(0)
    var appliancesQuantityState by mutableStateOf(0)
    var appliancesDamagedState by mutableStateOf(0)
    var gymState by mutableStateOf(false)
    var swimmingPoolState by mutableStateOf(false)
    var laundryState by mutableStateOf(false)
    var parkingState by mutableStateOf(false)

    fun onApartmentNumberChanged(newString: String) {
        apartmentNumberState = newString
    }

    fun onFloorChanged(newValue: Int) {
        floorState = newValue
    }

    fun onOwnerIdChanged(newValue: Int) {
        ownerIdState = newValue
    }

    fun onNumBedroomsChanged(newValue: Int) {
        numBedroomsState = newValue
    }

    fun onNumBathroomsChanged(newValue: Int) {
        numBathroomsState = newValue
    }

    fun onAreaSqftChanged(newValue: Int) {
        areaSqftState = newValue
    }

    fun onRentChanged(newValue: Int) {
        rentState = newValue
    }

    fun onInternetFeeChanged(newValue: Int) {
        internetFeeState = newValue
    }

    fun onGarbageFeeChanged(newValue: Int) {
        garbageFeeState = newValue
    }

    fun onStatusChanged(newString: String) {
        statusState = newString
    }

    fun onWaterUsageCurrentChanged(newValue: Int) {
        waterUsageCurrentState = newValue
    }

    fun onBedsQuantityChanged(newValue: Int) {
        bedsQuantityState = newValue
    }

    fun onBedsDamagedChanged(newValue: Int) {
        bedsDamagedState = newValue
    }

    fun onSofasQuantityChanged(newValue: Int) {
        sofasQuantityState = newValue
    }

    fun onSofasDamagedChanged(newValue: Int) {
        sofasDamagedState = newValue
    }

    fun onTablesQuantityChanged(newValue: Int) {
        tablesQuantityState = newValue
    }

    fun onTablesDamagedChanged(newValue: Int) {
        tablesDamagedState = newValue
    }

    fun onChairsQuantityChanged(newValue: Int) {
        chairsQuantityState = newValue
    }

    fun onChairsDamagedChanged(newValue: Int) {
        chairsDamagedState = newValue
    }

    fun onAppliancesQuantityChanged(newValue: Int) {
        appliancesQuantityState = newValue
    }

    fun onAppliancesDamagedChanged(newValue: Int) {
        appliancesDamagedState = newValue
    }

    fun onGymChanged(newValue: Boolean) {
        gymState = newValue
    }

    fun onSwimmingPoolChanged(newValue: Boolean) {
        swimmingPoolState = newValue
    }

    fun onLaundryChanged(newValue: Boolean) {
        laundryState = newValue
    }

    fun onParkingChanged(newValue: Boolean) {
        parkingState = newValue
    }

    lateinit var getAllApartments: Flow<List<Apartment>>

    init {
        viewModelScope.launch {
            getAllApartments = apartmentRespository.getApartments()
        }
    }

    fun addApartment(apartment: Apartment){
        viewModelScope.launch(Dispatchers.IO) {
            apartmentRespository.addAApartment(apartment = apartment)
        }
    }

    fun getAApartmentById(id:Long): Flow<Apartment> {
        return apartmentRespository.getAApartmentById(id)
    }

    fun updateApartment(apartment: Apartment){
        viewModelScope.launch(Dispatchers.IO) {
            apartmentRespository.updateAApartment(apartment = apartment)
        }
    }

    fun deleteApartment(apartment: Apartment){
        viewModelScope.launch(Dispatchers.IO) {
            apartmentRespository.deleteAApartment(apartment = apartment)
            getAllApartments =apartmentRespository.getApartments()
        }
    }
}