package kr.hs.dgsw.hyeon.vaccination_application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.usecase.center.local.GetLocalCenterDataUseCase
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocalCenterDataUseCase: GetLocalCenterDataUseCase
) : BaseViewModel() {

    private val _localCenterList = MutableLiveData<List<Center>>()
    val localCenterList: LiveData<List<Center>> get() = _localCenterList


    fun getUserData() = viewModelScope.launch(Dispatchers.IO) {
        isLoading.postValue(true)

        runCatching { getLocalCenterDataUseCase() }
            .onSuccess { _localCenterList.postValue(it) }
            .onFailure { onErrorEvent.postValue(it) }

        isLoading.postValue(false)
    }

}