package com.luaga.apartmentmanagement

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.luaga.apartmentmanagement.data.Apartments
import com.luaga.apartmentmanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var reference: DatabaseReference // Khai báo DatabaseReference
    val apartmentList =ArrayList<Apartments>()
    lateinit var apartmentsAdapter: ApartmentsAdapter
    val firebaseStorage : FirebaseStorage = FirebaseStorage.getInstance()
    val storageReference: StorageReference = firebaseStorage.reference


    val imageNameList = ArrayList<String>()

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
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = apartmentsAdapter.getApartmentId(viewHolder.adapterPosition)
                reference.child(id).removeValue()

                //delete()
                val imageName = apartmentsAdapter.getImageName(viewHolder.adapterPosition)
                val imageReference = storageReference.child("images").child(imageName)

                imageReference.delete()



                Toast.makeText(applicationContext,"The apartment was deleted",Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(mainBinding.recylerView)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll){
            showDialogMessage()
        }
        return super.onOptionsItemSelected(item)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun showDialogMessage(){
        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Xóa tất cả căn hộ")
        dialogMessage.setMessage("Nếu bấm Có, tất cả các căn hộ sẽ bị xóa" + "Nếu bạn muốn xóa một căn hộ cụ thể, bạn có thể vuốt mục sang phải hoặc sang trái")
        dialogMessage.setNegativeButton("Cancel", DialogInterface.OnClickListener{
            dialogInterface, i -> dialogInterface.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener{
            dialogMessage, i ->
            reference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(eachApartment in snapshot.children) {
                        val apartment = eachApartment.getValue(Apartments::class.java)
                        if (apartment != null) {
                            imageNameList.add(apartment.imageName)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

                val imageReference = storageReference.child("images")
                imageReference.delete()
                reference.removeValue().addOnCompleteListener{
                    task -> if(task.isSuccessful){
                                for(imageName in imageNameList){
                                    val imageReference = storageReference.child("images").child(imageName)
                                    imageReference.delete()
                                }
                                apartmentsAdapter.notifyDataSetChanged()
                                Toast.makeText(applicationContext,"Tất cả căn hộ đã bị xóa", Toast.LENGTH_SHORT).show()
                            }
                }
        })
        dialogMessage.create().show()
    }
}
