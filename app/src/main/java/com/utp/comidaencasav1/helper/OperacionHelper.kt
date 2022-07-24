package com.utp.comidaencasav1.helper

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.math.BigInteger
import java.security.MessageDigest

class OperacionHelper {
    fun getBundle(requireActivity: FragmentActivity): Bundle? {
        return requireActivity.intent.extras
    }

    fun encryptSHA1(input: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        val messageDigest = md.digest(input.toByteArray())
        val no = BigInteger(1, messageDigest)
        var hashtext = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    }
}