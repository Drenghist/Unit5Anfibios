package com.example.unit5anfibios

import android.app.Application
import com.example.unit5anfibios.data.AppContainer
import com.example.unit5anfibios.data.DefaultAppContainer

class AnfibiosFotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}