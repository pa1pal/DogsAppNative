package `in`.pawan.dogsapp.data

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.CreateLinkResponse
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import `in`.pawan.dogsapp.data.dto.LinkData
import `in`.pawan.dogsapp.data.dto.LinkResponse
import kotlinx.coroutines.flow.Flow

interface RepoSource {
    suspend fun getAllDogsBreeds(): Flow<ApiResponse<Breed>>

    suspend fun getBreedImages(breed: String): Flow<ApiResponse<BreedImages>>

    suspend fun trackEvent(event: Event): Flow<ApiResponse<EventResponse>>

    suspend fun createDeepLink(linkData: LinkData): Flow<ApiResponse<CreateLinkResponse>>

    suspend fun readDeepLink(url: String): Flow<ApiResponse<LinkResponse>>

}