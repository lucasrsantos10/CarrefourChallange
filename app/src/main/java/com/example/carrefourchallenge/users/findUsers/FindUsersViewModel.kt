package com.example.carrefourchallenge.users.findUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrefourchallenge.domain.users.Users
import com.example.carrefourchallenge.domain.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindUsersViewModel @Inject constructor(private var repository: UsersRepository): ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val usersList: MutableLiveData<List<Users>> = MutableLiveData()
    val filteredUsersList: MutableLiveData<List<Users>> = MutableLiveData()
    val showErrorSnackbar: MutableLiveData<String> = MutableLiveData()

    init {
        getUsersList()
    }

    private fun getUsersList() {
        viewModelScope.launch {
            try {
                loading.postValue(true)
                usersList.postValue(repository.getUsersList())
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorSnackbar.postValue("Oops, tivemos um problema ao pegar os usuarios")
            } finally {
                loading.postValue(false)
            }
        }
    }

    fun onSearchingUser(text: String?) {
        val list = mutableListOf<Users>()
        if (!text.isNullOrEmpty()) {
            usersList.value?.forEach {
                if(it.name?.contains(text) == true) {
                    list.add(it)
                }
            }
            filteredUsersList.postValue(list)
        } else {
            usersList.postValue(usersList.value)
        }
    }
}