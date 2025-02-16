package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.CreateLinkResponse
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import `in`.pawan.dogsapp.data.dto.LinkData
import `in`.pawan.dogsapp.data.dto.LinkResponse
import retrofit2.Response

internal interface RemoteDataSource {
    suspend fun getAllDogsBreed(): Response<Breed>

    suspend fun getDogBreedImages(breed: String): Response<BreedImages>

    suspend fun trackEvent(event: Event): Response<EventResponse>

    suspend fun trackStandard(event: Event): Response<EventResponse>

    suspend fun createDeepLink(linkData: LinkData): Response<CreateLinkResponse>

    suspend fun readDeepLink(url: String, branchKey: String): Response<LinkResponse>
}