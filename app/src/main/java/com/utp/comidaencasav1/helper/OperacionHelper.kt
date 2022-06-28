package com.utp.comidaencasav1.helper

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class OperacionHelper {
    fun getBundle(requireActivity: FragmentActivity): Bundle? {
        return requireActivity.intent.extras
    }
}