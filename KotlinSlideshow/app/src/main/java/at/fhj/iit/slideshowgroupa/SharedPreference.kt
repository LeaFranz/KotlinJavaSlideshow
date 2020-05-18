package at.fhj.iit.slideshowgroupa

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val prefName = "SlideshowPref"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    // saving data
    fun save(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    //getting data
    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }
}