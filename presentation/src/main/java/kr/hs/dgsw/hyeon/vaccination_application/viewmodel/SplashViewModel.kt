package kr.hs.dgsw.hyeon.vaccination_application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.usecase.center.local.GetLocalCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.local.InsertCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.remote.GetRemoteCenterDataUseCase
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseViewModel
import kr.hs.dgsw.hyeon.vaccination_application.util.SingleLiveEvent
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getRemoteCenterData: GetRemoteCenterDataUseCase,
    private val insertCenterDataUseCase: InsertCenterDataUseCase,
): BaseViewModel() {

    val collectDoneEvent = SingleLiveEvent<Unit>()

    private val _centerList = MutableLiveData<List<Center>>()
    val centerList : LiveData<List<Center>> get() = _centerList

    private val pageList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    init {
        selectCenterData()
    }

    private fun selectCenterData() {
        isLoading.postValue(true)

        pageList.forEach { page ->
            searchCenterList(page)
            _centerList.value?.forEach {
                insertCenterData(it)
            }
        }

        isLoading.postValue(false)
        collectDoneEvent.call()
    }

    private fun insertCenterData(center: Center) = viewModelScope.launch(Dispatchers.IO) {
        insertCenterDataUseCase(center)
    }


    private fun searchCenterList(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        getRemoteCenterData(page).collect {
            _centerList.postValue(it)
        }
    }
}