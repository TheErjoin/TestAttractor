package com.example.testattractor.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testattractor.data.UserAboutImpl
import com.example.testattractor.domain.models.UserAbout
import com.example.testattractor.domain.usecase.userabout.AddUserAboutUseCase
import com.example.testattractor.domain.usecase.userabout.GetUserAboutUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = UserAboutImpl()

    private val addUserAboutUseCase = AddUserAboutUseCase(repository)

    private val getUserAboutUseCase = GetUserAboutUseCase(repository)

    val userList = getUserAboutUseCase.getUserAbout()

    fun addUserAbout(userAbout: UserAbout) {
        viewModelScope.launch {
            addUserAboutUseCase.addUserAbout(userAbout)
        }
    }

}