package com.luaga.apartmentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.luaga.apartmentmanagement.data.Apartments
import com.luaga.apartmentmanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var reference: DatabaseReference // Khai báo DatabaseReference
    val apartmentList =ArrayList<Apartments>()
    lateinit var apartmentsAdapter: ApartmentsAdapter

    // Tạo một đối tượng FirebaseAuth
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView.selectedItemId = R.id.bottom_home
        bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bottom_home -> return@setOnItemSelectedListener true
                R.id.bottom_search -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            SearchActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_settings -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            SettingActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }

                R.id.bottom_profile -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            ProfileActivity::class.java
                        )
                    )

                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        mainBinding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddApartmentActivity::class.java)
            startActivity(intent)
        }

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Người dùng đã đăng nhập, lấy User UID
            val uid: String = currentUser.uid
            println("User UID: $uid")
            reference = FirebaseDatabase.getInstance().reference.child("users")
                .child(uid)
                .child("apartments")
        } else {
            // Người dùng chưa đăng nhập
            println("User chưa đăng nhập.")
        }
        retrieveDataFromDatabase()
    }
    fun retrieveDataFromDatabase(){
        // ChildEventListener
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                apartmentList.clear()
                for(eachApartment in snapshot.children){
                    val apartment = eachApartment.getValue(Apartments::class.java)
                    if(apartment != null){
                        println("apartmentId: ${apartment.apartmentId}")
                        println("apartmentNumber: ${apartment.apartmentNumber}")
                        println("floor: ${apartment.floor}")
                        println("area: ${apartment.area}")
                        println("price: ${apartment.price}")
                        println("priceGarbage: ${apartment.priceGarbage}")
                        println("priceInternet: ${apartment.priceInternet}")
                        println("numBathrooms: ${apartment.numBathrooms}")
                        println("numBedrooms: ${apartment.numBedrooms}")
                        println("gymService: ${apartment.gymService}")
                        println("laundryService: ${apartment.laundryService}")
                        println("swimmingService: ${apartment.swimmingService}")
                        println("parkingService: ${apartment.parkingService}")
                        println("*********************************************")
                        apartmentList.add(apartment)
                    }
                    apartmentsAdapter = ApartmentsAdapter(this@MainActivity, apartmentList)

                    mainBinding.recylerView.layoutManager = LinearLayoutManager(this@MainActivity)

                    mainBinding.recylerView.adapter = apartmentsAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu cần
            }
        })
    }

}
