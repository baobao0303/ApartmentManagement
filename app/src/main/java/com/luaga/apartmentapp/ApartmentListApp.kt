package com.luaga.apartmentapp

import android.app.Application

class ApartmentListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}