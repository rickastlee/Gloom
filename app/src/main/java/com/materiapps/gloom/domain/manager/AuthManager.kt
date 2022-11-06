package com.materiapps.gloom.domain.manager

import android.content.Context
import androidx.core.content.edit
import com.materiapps.gloom.domain.manager.base.BasePreferenceManager
import com.materiapps.gloom.domain.repository.GithubAuthRepository
import com.materiapps.gloom.rest.utils.ifSuccessful
import com.materiapps.gloom.utils.coroutine

class AuthManager(context: Context, private val authRepo: GithubAuthRepository) {

    private val sharedPrefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
    var authToken: String
        get() = sharedPrefs.getString("authToken", "")!!
        set(value) = sharedPrefs.edit {
            putString("authToken", value)
        }

    val isSignedIn get() = authToken.isNotEmpty()

}