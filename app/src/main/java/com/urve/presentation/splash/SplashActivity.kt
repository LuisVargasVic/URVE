package com.urve.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.urve.*
import com.urve.databinding.ActivitySplashBinding
import com.urve.domain.Register
import com.urve.remote.listener.RegisterListener
import com.urve.utils.navigateToActivityAffinity
import com.urve.utils.simpleAlertDialog

class SplashActivity : AppCompatActivity(), RegisterListener {

    private lateinit var mBinding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        viewModel = SplashViewModel()
        mFirebaseAuth = FirebaseAuth.getInstance()

        // Wait 1 second so the splash screen could be viewed
        val handler = Handler()
        handler.postDelayed({
            mFirebaseAuth.addAuthStateListener {

                if (it.currentUser != null) {
                    mFirebaseAuth = it
                    viewModel.getRegister(this)
                } else {
                    // navigateToActivityTop(SessionActivity())
                }
            }
        }, 1000)
    }

    override fun preExecute() {

    }

    override fun postExecute(register: Register) {
        if (mFirebaseAuth.currentUser != null && mFirebaseAuth.currentUser!!.isEmailVerified) {
            if (!register.auth) {
                // navigateToActivityTop(SessionActivity())
            }
        } else {
            // navigateToActivityTop(SessionActivity())
        }
    }

    override fun errorMessage(message: String?) {
        simpleAlertDialog(message) {
            // navigateToActivityTop(SessionActivity())
        }?.show()
    }

    override fun session() {
        navigateToActivityAffinity(SplashActivity())
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeListener()
    }
}