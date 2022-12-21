package `in`.pawan.dogsapp.data

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import kotlinx.coroutines.flow.Flow

interface RepoSource {
    suspend fun getAllDogsBreeds(): Flow<ApiResponse<Breed>>

    suspend fun getBreedImages(breed: String): Flow<ApiResponse<BreedImages>>
}