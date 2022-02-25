package com.example.testattractor.domain.usecase.userabout

import com.example.testattractor.domain.models.UserAbout
import com.example.testattractor.domain.repository.UserAboutRepository

class AddUserAboutUseCase(private val repository: UserAboutRepository) {

    fun addUserAbout(userAbout: UserAbout) {
        repository.addUserAbout(userAbout)
    }

}