package com.example.testattractor.presentation.gallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testattractor.R
import com.example.testattractor.databinding.ActivityGalleryBinding
import com.example.testattractor.domain.models.Gallery

class GalleryActivity : AppCompatActivity() {

    private val binding: ActivityGalleryBinding by viewBinding()
    private lateinit var adapterG: GalleryAdapter
    private val viewModel: GalleryViewModel by viewModels()
    private val permissions: Array<String> by lazy {
        arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    }

    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it != null) {
                    val count: Int = it.data?.clipData!!.itemCount
                    for (i in 0 until count) {
                        viewModel.addShopItem(Gallery(it.data?.clipData?.getItemAt(i)?.uri.toString()))
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        getPermissionForGallery()
        initRecycler()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        adapterG.galleryOnLongListener = {
            viewModel.deletePhoto(it)
        }

        binding.fab.setOnClickListener {
            pickImage()
        }
    }

    private fun initObservers() {
        viewModel.galleryList.observe(this, {
            adapterG.submitList(it)
        })
    }

    private fun initRecycler() {
        binding.recycler.apply {
            adapterG = GalleryAdapter()
            adapter = adapterG
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun pickImage() {
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "image/*"
            registerForActivity.launch(this)
        }
    }

    private fun getPermissionForGallery() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 1)
                }
            }
            else -> {
                pickImage()
            }
        }
    }
}