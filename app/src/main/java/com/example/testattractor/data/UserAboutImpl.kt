package com.example.testattractor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testattractor.domain.models.UserAbout
import com.example.testattractor.domain.repository.UserAboutRepository

class UserAboutImpl : UserAboutRepository {

    private val userListLD = MutableLiveData<List<UserAbout>>()
    private val userList = mutableListOf<UserAbout>()

    override fun addUserAbout(userAbout: UserAbout) {
        userList.add(userAbout)
        userUpdateList()
    }

    override fun getUserAbout(): LiveData<List<UserAbout>> {
        return userListLD
    }

    private fun userUpdateList() {
        userListLD.value = userList.toList()
    }
}