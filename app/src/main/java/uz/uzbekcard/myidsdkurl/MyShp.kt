package uz.uzbekcard.myidsdkurl

import android.content.Context

class MyShp(context: Context){
    private var myPref = context.getSharedPreferences("my_shp", Context.MODE_PRIVATE)

    companion object{
        private var shp: MyShp? = null

        fun init(context: Context) : MyShp {
            if (shp == null){
                shp = MyShp(context)
            }
            return shp!!
        }
    }

    var url : String
        set(value) = myPref.edit().putString("url",value).apply()
        get() { return myPref.getString("url", "http://10.1.2.163:55555/myid?code=")!! }

}