package com.luaga.apartmentapp.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apartments-table")
data class Apartment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "apartments-number")
    val apartment_number: String,

    @ColumnInfo(name = "apartments-floor")
    val floor: Int,

    @ColumnInfo(name = "apartments-owner_id")
    val owner_id: Int,

    @ColumnInfo(name = "apartments-num_bedrooms")
    val num_bedrooms: Int,

    @ColumnInfo(name = "apartments-num_bathrooms")
    val num_bathrooms: Int,

    @ColumnInfo(name = "apartments-area_sqft")
    val area_sqft: Int,

    @ColumnInfo(name = "apartments-rent")
    val rent: Int,

    @ColumnInfo(name = "apartments-internet_fee")
    val internet_fee: Int,

    @ColumnInfo(name = "apartments-garbage_fee")
    val garbage_fee: Int,

    @ColumnInfo(name = "apartments-status")
    val status: String,

    @ColumnInfo(name = "apartments-water_usage_current")
    val water_usage_current: Int?,

    @ColumnInfo(name = "apartments-beds_quantity")
    val beds_quantity: Int,

    @ColumnInfo(name = "apartments-beds_damaged")
    val beds_damaged: Int,

    @ColumnInfo(name = "apartments-sofas_quantity")
    val sofas_quantity: Int,

    @ColumnInfo(name = "apartments-sofas_damaged")
    val sofas_damaged: Int,

    @ColumnInfo(name = "apartments-tables_quantity")
    val tables_quantity: Int,

    @ColumnInfo(name = "apartments-tables_damaged")
    val tables_damaged: Int,

    @ColumnInfo(name = "apartments-chairs_quantity")
    val chairs_quantity: Int,

    @ColumnInfo(name = "apartments-chairs_damaged")
    val chairs_damaged: Int,

    @ColumnInfo(name = "apartments-appliances_quantity")
    val appliances_quantity: Int,

    @ColumnInfo(name = "apartments-appliances_damaged")
    val appliances_damaged: Int,

    @ColumnInfo(name = "apartments-gym")
    val gym: Boolean,

    @ColumnInfo(name = "apartments-swimming_pool")
    val swimming_pool: Boolean,

    @ColumnInfo(name = "apartments-laundry")
    val laundry: Boolean,

    @ColumnInfo(name = "apartments-parking")
    val parking: Boolean
)

object DummyApartments {
    val apartments = listOf(
        Apartment(
            apartment_number = "A101",
            floor = 1,
            owner_id = 1,
            num_bedrooms = 2,
            num_bathrooms = 1,
            area_sqft = 1000,
            rent = 1500,
            internet_fee = 0,
            garbage_fee = 0,
            status = "available",
            water_usage_current = null,
            beds_quantity = 2,
            beds_damaged = 0,
            sofas_quantity = 1,
            sofas_damaged = 1,
            tables_quantity = 1,
            tables_damaged = 0,
            chairs_quantity = 4,
            chairs_damaged = 0,
            appliances_quantity = 3,
            appliances_damaged = 0,
            gym = false,
            swimming_pool = false,
            laundry = false,
            parking = false
        ),
        Apartment(
            apartment_number = "B202",
            floor = 2,
            owner_id = 1,
            num_bedrooms = 3,
            num_bathrooms = 2,
            area_sqft = 1200,
            rent = 1800,
            internet_fee = 50,
            garbage_fee = 20,
            status = "occupied",
            water_usage_current = null,
            beds_quantity = 3,
            beds_damaged = 0,
            sofas_quantity = 1,
            sofas_damaged = 0,
            tables_quantity = 2,
            tables_damaged = 0,
            chairs_quantity = 6,
            chairs_damaged = 0,
            appliances_quantity = 4,
            appliances_damaged = 1,
            gym = false,
            swimming_pool = true,
            laundry = true,
            parking = true
        ),
        Apartment(
            apartment_number = "C303",
            floor = 3,
            owner_id = 2,
            num_bedrooms = 1,
            num_bathrooms = 1,
            area_sqft = 800,
            rent = 1200,
            internet_fee = 40,
            garbage_fee = 15,
            status = "available",
            water_usage_current = null,
            beds_quantity = 1,
            beds_damaged = 0,
            sofas_quantity = 1,
            sofas_damaged = 0,
            tables_quantity = 1,
            tables_damaged = 0,
            chairs_quantity = 2,
            chairs_damaged = 0,
            appliances_quantity = 2,
            appliances_damaged = 0,
            gym = false,
            swimming_pool = true,
            laundry = false,
            parking = false
        )
    )
}
