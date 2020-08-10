package com.urve.presentation.splash

import androidx.lifecycle.ViewModel
import com.urve.data.VerifyRepository
import com.urve.remote.listener.RegisterListener

class SplashViewModel() : ViewModel() {

    private var repository: VerifyRepository = VerifyRepository()

    fun getRegister(registerListener: RegisterListener) {
        repository.getRegister(registerListener)
    }

    fun removeListener() {
        repository.removeListener()
    }

}