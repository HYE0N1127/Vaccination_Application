package kr.hs.dgsw.hyeon.vaccination_application.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.usecase.center.local.InsertCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.remote.GetRemoteCenterDataUseCase
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseViewModel
import kr.hs.dgsw.hyeon.vaccination_application.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getRemoteCenterDataUseCase: GetRemoteCenterDataUseCase,
    private val insertCenterDataUseCase: InsertCenterDataUseCase,
): BaseViewModel() {


    val collectSuccessEvent = SingleLiveEvent<Unit>()
    val collectFailureEvent = SingleLiveEvent<Unit>()

    private val _centerList = MutableLiveData<List<Center>>()
    val centerList : LiveData<List<Center>> get() = _centerList

    init {
        selectCenterData()
    }

    private fun selectCenterData() {
        isLoading.postValue(true)

        for (i in 1 until 11) {
            searchCenterList(i)
        }

        isLoading.postValue(false)
    }

    private fun searchCenterList(page: Int) = viewModelScope.launch {
        getRemoteCenterDataUseCase(page).catch {
            collectFailureEvent.call()
        }.collect {
            it.forEach { center ->
                insertCenterData(center)
            }
            collectSuccessEvent.call()
        }

    }

    private fun insertCenterData(center: Center) = viewModelScope.launch(Dispatchers.IO) {
        insertCenterDataUseCase(center)
    }
}