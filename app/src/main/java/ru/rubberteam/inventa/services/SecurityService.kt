package ru.rubberteam.inventa.services

import android.content.SharedPreferences
import org.apache.commons.codec.digest.DigestUtils
import ru.rubberteam.inventa.activities.login.LoginConstants.ACTUAL_PIN_CODE_HASH_KEY


class SecurityService() {

    fun checkUser(login: String, password: String): Boolean {
        //TODO go to the rest
        return login.length == password.length
    }

    fun checkPinCode(pinCode: String, mSettings: SharedPreferences): Boolean {
        return mSettings.getString(ACTUAL_PIN_CODE_HASH_KEY, "") == stringToHashSha256(pinCode)
    }

    fun isUserLogged(mSettings: SharedPreferences): Boolean {
        return mSettings.contains(ACTUAL_PIN_CODE_HASH_KEY)
    }


    fun saveHashPinCode(pinCode: String, mSettings: SharedPreferences) {
        val hashPinCode = stringToHashSha256(pinCode)
        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.putString(ACTUAL_PIN_CODE_HASH_KEY, hashPinCode)
        editor.apply()
    }

    private fun stringToHashSha256(str: String): String {
        return DigestUtils.sha256Hex(str)
    }

}