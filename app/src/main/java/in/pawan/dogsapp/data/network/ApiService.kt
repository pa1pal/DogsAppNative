package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("list")
    suspend fun allDogsBreed(): Response<Breed>

    @GET("breed/{breed}/images")
    suspend fun breedImages(@Path("breed") breed: String): Response<BreedImages>
}