package com.example.testattractor.domain.usecase.userabout

import com.example.testattractor.domain.repository.UserAboutRepository

class GetUserAboutUseCase(private val repository: UserAboutRepository) {

    fun getUserAbout() = repository.getUserAbout()

}