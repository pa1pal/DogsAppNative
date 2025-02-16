package `in`.pawan.dogsapp

import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.data.MainRepository
import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.utils.NetworkHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.pawan.dogsapp.data.dto.LinkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    var networkStatusMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val readDeepLinkLiveData: LiveData<ApiResponse<LinkResponse>>
        get() = mutableReadDeepLinkLiveData
    private var mutableReadDeepLinkLiveData: MutableLiveData<ApiResponse<LinkResponse>> = MutableLiveData()

    fun readDeepLink(url: String) {
        if (networkHelper.isConnected()) {
            viewModelScope.launch {
                mainRepository.readDeepLink(url).collect {
                   mutableReadDeepLinkLiveData.value = it
                }
            }
        } else {
            networkStatusMutableLiveData.value = false
        }
    }
}