package com.example.testattractor.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.testattractor.R
import com.example.testattractor.databinding.ActivityMainBinding
import com.example.testattractor.domain.models.Company
import com.example.testattractor.domain.models.User
import com.example.testattractor.domain.models.UserAbout
import com.example.testattractor.presentation.gallery.GalleryActivity
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private val list = mutableListOf(
        Company(
            "Attractor",
            "Android-developer"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launchWhenCreated {
            addUserAboutFun()
        }
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.btnNext.setOnClickListener {
            Intent(this, GalleryActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun initObservers() {
        viewModel.userList.observe(this, {
            binding.tvFirstName.text = it[0].user.first_name
            binding.tvSecondName.text = it[0].user.second_name
            binding.tvEducation.text = it[0].user.education
            binding.tvCompany.text = it[0].user.company[0].name
            binding.tvPosition.text = it[0].user.company[0].position
            binding.ivPhoto.load(it[0].user.photo)
        })
    }

    private fun isButton(isVisible: Boolean) {
        binding.progressGallery.isVisible = isVisible
        binding.btnNext.isEnabled = !isVisible
    }

    private suspend fun addUserAboutFun() {
        isButton(true)
        val education = loadEducation()
        val firstName = loadFirstName()
        val secondName = loadSecondName()
        val photo = loadPhoto()
        viewModel.addUserAbout(
            UserAbout(
                User(
                    education,
                    firstName,
                    photo,
                    secondName,
                    list
                )
            )
        )
        isButton(false)
        Toast.makeText(
            this,
            getString(R.string.nicemett),
            Toast.LENGTH_SHORT
        ).show()
    }

    private suspend fun loadEducation(): String {
        delay(1000)
        return getString(R.string.high)
    }

    private suspend fun loadFirstName(): String {
        delay(1000)
        return getString(R.string.surname)
    }

    private suspend fun loadSecondName(): String {
        delay(1000)
        return getString(R.string.myname)
    }

    private suspend fun loadPhoto(): String {
        delay(1000)
        return getString(R.string.photo)
    }


}