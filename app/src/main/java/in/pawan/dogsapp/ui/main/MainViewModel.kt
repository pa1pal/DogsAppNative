package `in`.pawan.dogsapp.ui.main

import `in`.pawan.dogsapp.data.ApiResponse
import `in`.pawan.dogsapp.data.MainRepository
import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.utils.NetworkHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    var networkStatusMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val breedListLiveData: LiveData<ApiResponse<Breed>>
        get() = mutableBreedListLiveData
    private var mutableBreedListLiveData: MutableLiveData<ApiResponse<Breed>> = MutableLiveData()

    fun fetchBreedList() {
        if (networkHelper.isConnected()) {
            viewModelScope.launch {
                mainRepository.getAllDogsBreeds().collect {
                    mutableBreedListLiveData.value = it
                }
            }
        } else {
            networkStatusMutableLiveData.value = false
        }
    }
}