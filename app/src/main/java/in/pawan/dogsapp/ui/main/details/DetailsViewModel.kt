package `in`.pawan.dogsapp.ui.main.details

import `in`.pawan.dogsapp.BuildConfig
import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.data.MainRepository
import `in`.pawan.dogsapp.data.dto.*
import `in`.pawan.dogsapp.utils.NetworkHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    var networkStatusMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val breedDetailsLiveData: LiveData<ApiResponse<BreedImages>>
        get() = mutableBreedDetailsLiveData
    private var mutableBreedDetailsLiveData: MutableLiveData<ApiResponse<BreedImages>> =
        MutableLiveData()

    val trackEventLiveData: LiveData<ApiResponse<EventResponse>>
        get() = mutableTrackEventLiveData
    private var mutableTrackEventLiveData: MutableLiveData<ApiResponse<EventResponse>> =
        MutableLiveData()


    fun fetchBreedDetails(breedName: String) {
        if (networkHelper.isConnected()) {
            viewModelScope.launch {
                mainRepository.getBreedImages(breedName).collect {
                    mutableBreedDetailsLiveData.value = it
                }
            }
        } else {
            networkStatusMutableLiveData.value = false
        }
    }

    fun trackEventFromApi(breedName: String?, eventName: String) {
        val userData = UserData(androidId = "a123", ip = "168.1.1.1", os = "Android")
        val customData = CustomData(breed = breedName)
        val eventData = EventData("pawan_custom_param1")

        val event = Event(
            name = eventName,
            branchKey = BuildConfig.BranchKey,
            userData = userData,
            customData = customData,
            eventData = eventData
        )

        if (networkHelper.isConnected()) {
            viewModelScope.launch {
                mainRepository.trackEvent(event).collect {
                    mutableTrackEventLiveData.value = it
                }
            }
        } else {
            networkStatusMutableLiveData.value = false
        }
    }
}