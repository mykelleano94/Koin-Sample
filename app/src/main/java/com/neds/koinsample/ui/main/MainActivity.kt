package com.neds.koinsample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neds.koinsample.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm: UserViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupObserver()
    }

    private fun setupObserver() {
        vm.users.observe(this, {
//            when (it.status) {
//                Status.SUCCESS ->
//                    it.data?.let { users ->
//                        users.forEach { user ->
//                            Log.d(TAG, "setupObserver->SUCCESS: user=$user")
//                        }
//                    }
//                Status.ERROR -> Log.e(TAG, "setupObserver->ERROR: error: ${it.message}")
//                Status.LOADING -> Log.d(TAG, "setupObserver->LOADING: loading")
//            }
            it.forEach { user -> Log.d(TAG, "setupObserver: user=$user") }
        })

        vm.loading.observe(this, {
            Log.d(TAG, "setupObserver: loading=$it")
        })

        vm.errorMessage.observe(this, {
            Log.d(TAG, "setupObserver: errorMessage=$it")
        })
    }

    companion object {
        const val TAG = "UserActivity"
    }
}