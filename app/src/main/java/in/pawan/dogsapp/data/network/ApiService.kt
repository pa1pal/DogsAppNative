package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import `in`.pawan.dogsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("breeds/list")
    suspend fun allDogsBreed(): Response<Breed>

    @GET("breed/{breed}/images/random")
    suspend fun breedImages(@Path("breed") breed: String): Response<BreedImages>

    /**
     * Directly added url because Retrofit base url is different
     */
    @POST(Constants.CUSTOM_EVENT)
    suspend fun trackEvent(@Body event: Event): Response<EventResponse>
}