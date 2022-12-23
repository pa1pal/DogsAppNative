package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import retrofit2.Response

internal interface RemoteDataSource {
    suspend fun getAllDogsBreed(): Response<Breed>

    suspend fun getDogBreedImages(breed: String): Response<BreedImages>

    suspend fun trackEvent(event: Event): Response<EventResponse>
}