package com.example.carrefourchallenge.users.userInformation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrefourchallenge.domain.repositories.UsersRepository
import com.example.carrefourchallenge.domain.users.Repository
import com.example.carrefourchallenge.domain.users.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInformationsViewModel @Inject constructor(private val repository: UsersRepository): ViewModel() {

    val showErrorToast: MutableLiveData<String> = MutableLiveData()
    val loadingRepository: MutableLiveData<Boolean> = MutableLiveData(false)
    val repoNames: MutableLiveData<List<Repository>> = MutableLiveData()
    val userInfo: MutableLiveData<UserInformation> = MutableLiveData()
    val loadingScreen: MutableLiveData<Boolean> = MutableLiveData(false)

    private fun getUserInfo(userName: String) {
        viewModelScope.launch {
            try {
                loadingScreen.postValue(true)
                userInfo.postValue(repository.getUserInfo(userName))
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorToast.postValue("Oops, tivemos um problema ao carregar as informações do usuario")
            }finally {
                loadingScreen.postValue(false)
            }
        }
    }

    private fun getRepos(userName: String) {
        viewModelScope.launch {
            try {
                loadingRepository.postValue(true)
                repoNames.postValue(repository.getRepositories(userName))
            }catch (e:Exception) {
                e.printStackTrace()
                showErrorToast.postValue("Oops, tivemos um problema ao carregar os repositórios")
            } finally {
                loadingRepository.postValue(false)
            }
        }
    }

    fun onInit(userName: String) {
        getUserInfo(userName)
        getRepos(userName)
    }
}