package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
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
}