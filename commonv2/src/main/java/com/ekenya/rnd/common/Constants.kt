package com.ekenya.rnd.common

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment

object Constants {
    /**
     * The Base Package ID path for the application module
     *
     * The app module and all other modules should be relative to this path
     * E.G if base = 'com.ekenya.rnd', then app id would be 'com.ekenya.rnd.app', then module id, like support should be 'com.ekenya.rnd.support'
     */
    @JvmField
    var BASE_PACKAGE_NAME = "com.ekenya.rnd"
}

