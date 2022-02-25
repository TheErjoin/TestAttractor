package com.example.testattractor.domain.repository

import androidx.lifecycle.LiveData
import com.example.testattractor.domain.models.UserAbout

interface UserAboutRepository {

    fun addUserAbout(userAbout: UserAbout)

    fun getUserAbout(): LiveData<List<UserAbout>>
}