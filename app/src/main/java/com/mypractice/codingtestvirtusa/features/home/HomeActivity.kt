package com.mypractice.codingtestvirtusa.features.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mypractice.codingtestvirtusa.R
import com.mypractice.codingtestvirtusa.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        observerSetup()

        btnFetchData.setOnClickListener {
            homeViewModel.getUserData()
        }
    }

    private fun observerSetup() {
        homeViewModel.res.observe(this, Observer {
            txtData.text = ""
            when (it.status) {
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    it.data.let { res ->
                        Log.d("response", "" + res?.data?.email)
                        txtData.text = homeViewModel._responseEmails
                    }
                }
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    Snackbar.make(rootView, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}