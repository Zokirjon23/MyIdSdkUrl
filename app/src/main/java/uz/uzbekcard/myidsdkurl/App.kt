package uz.uzbekcard.myidsdkurl

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MyShp.init(this)
    }
}