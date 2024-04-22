package com.luaga.apartmentapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ApartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAApartment(apartmentEntity: Apartment)

    // Loads all Apartments from the Apartment table
    @Query("Select * from `apartments-table`")
    abstract fun getAllApartment(): Flow<List<Apartment>>

    @Update
    abstract suspend fun updateAApartment(apartmentEntity: Apartment)

    @Delete
    abstract suspend fun deleteAApartment(apartmentEntity: Apartment)

    @Query("Select * from `apartments-table` where id=:id")
    abstract fun getAApartmentById(id:Long): Flow<Apartment>


}