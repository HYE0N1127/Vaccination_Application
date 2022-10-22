package kr.hs.dgsw.hyeon.vaccination_application.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected val isLoading = MutableLiveData(false)

    fun getIsLoading(): LiveData<Boolean> = isLoading

    val onErrorEvent: MutableLiveData<Throwable> = MutableLiveData()

    private val flowJobList: MutableMap<String, Job> = mutableMapOf()

    fun <T> launchFlow(key: String, flow: Flow<T>, callback: (T) -> Unit) {
        flowJobList[key]?.cancel()
        flowJobList[key] = viewModelScope.launch {
            flow.catch {
                it.printStackTrace()
                onErrorEvent.postValue(it)
            }.collect { callback(it) }
        }
    }

}