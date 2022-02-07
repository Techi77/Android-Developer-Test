package com.example.androiddevelopertest

import android.content.ContentProviderOperation
import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    private const val CARDUSERNUMBER = "cardUserNumber"
    private const val CUSTOMCURRENCY = "customCurrency"

    fun customPreference(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.cardUserNumber
        get() = getInt(CARDUSERNUMBER, 0)
        set(value) {
            editMe {
                it.putInt(CARDUSERNUMBER, value)
            }
        }
    var SharedPreferences.customCurrency
        get() = getString(CUSTOMCURRENCY, "R01035")
        set(value) {
            editMe {
                it.putString(CUSTOMCURRENCY, value)
            }
        }
}