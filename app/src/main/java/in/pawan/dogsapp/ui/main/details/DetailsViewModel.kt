package `in`.pawan.dogsapp.ui.main.details

import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.data.MainRepository
import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
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
}