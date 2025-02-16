package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.CreateLinkResponse
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import `in`.pawan.dogsapp.data.dto.LinkData
import `in`.pawan.dogsapp.data.dto.LinkResponse
import retrofit2.Response
import javax.inject.Inject

class DogsRemoteData @Inject constructor(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getAllDogsBreed(): Response<Breed> {
        return apiService.allDogsBreed()
    }

    override suspend fun getDogBreedImages(breed: String): Response<BreedImages> {
        return apiService.breedImages(breed)
    }

    override suspend fun trackEvent(event: Event): Response<EventResponse> {
        return apiService.trackEvent(event)
    }

    override suspend fun trackStandard(event: Event): Response<EventResponse> {
        return apiService.trackStandard(event)
    }

    override suspend fun createDeepLink(linkData: LinkData): Response<CreateLinkResponse> {
        return apiService.createDeepLink(linkData)
    }

    override suspend fun readDeepLink(url: String, branchKey: String): Response<LinkResponse> {
        return apiService.readDeepLink(url, branchKey)
    }
}