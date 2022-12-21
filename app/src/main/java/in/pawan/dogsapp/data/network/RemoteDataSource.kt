package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import retrofit2.Response

internal interface RemoteDataSource {
    suspend fun getAllDogsBreed(): Response<Breed>

    suspend fun getDogBreedImages(breed: String): Response<BreedImages>
}