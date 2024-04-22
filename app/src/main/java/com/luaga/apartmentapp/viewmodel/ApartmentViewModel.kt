package com.luaga.apartmentapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//name = "Alice Smith",
//contact = "alice.smith@example.com",
//phone = "555-123-4567",
//identityCardNumber = "GHI789012",
//moveInDate = "2023-07-01",
//leaseExpiryDate = "2024-06-30"
class ApartmentViewModel:ViewModel(){
    var apartmentNameState by mutableStateOf("")
    var apartmentContactState by mutableStateOf("")
    var apartmentPhoneState by mutableStateOf("")
    var apartmentIdentityCardNumberState by mutableStateOf("")
    var apartmentMoveInDateState by mutableStateOf("")
    var apartmentLeaseExpiryDateInDateState by mutableStateOf("")

    fun onApartmentNameChanged(newString: String){
        apartmentNameState = newString
    }
    fun onApartmentContactChanged(newString: String){
        apartmentContactState = newString
    }
    fun onApartmentPhoneChanged(newString: String){
        apartmentPhoneState = newString
    }
    fun onApartmentIdentityCardNumberChanged(newString: String){
        apartmentIdentityCardNumberState= newString
    }
    fun onApartmentMoveInDateChanged(newString: String){
        apartmentMoveInDateState = newString
    }
    fun onApartmentLeaseExpiryDateInDateChanged(newString: String){
        apartmentLeaseExpiryDateInDateState= newString
    }
}