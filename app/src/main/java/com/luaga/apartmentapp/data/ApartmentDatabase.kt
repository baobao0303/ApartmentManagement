package com.luaga.apartmentapp.data
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Apartment::class],
    version = 1,
    exportSchema = false
)
abstract class ApartmentDatabase : RoomDatabase() {
    abstract fun apartmentDao(): ApartmentDao
}