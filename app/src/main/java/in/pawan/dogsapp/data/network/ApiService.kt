package `in`.pawan.dogsapp.data.network

import `in`.pawan.dogsapp.data.dto.Breed
import `in`.pawan.dogsapp.data.dto.BreedImages
import `in`.pawan.dogsapp.data.dto.CreateLinkResponse
import `in`.pawan.dogsapp.data.dto.Event
import `in`.pawan.dogsapp.data.dto.EventResponse
import `in`.pawan.dogsapp.data.dto.Link
import `in`.pawan.dogsapp.data.dto.LinkData
import `in`.pawan.dogsapp.data.dto.LinkResponse
import `in`.pawan.dogsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url

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

    @POST(Constants.STANDARD_EVENT)
    suspend fun trackStandard(@Body event: Event): Response<EventResponse>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET(Constants.DEEP_LINK)
    suspend fun readDeepLink(@Query("url") url: String, @Query("branch_key") apiKey: String): Response<LinkResponse>

    //@Headers("Content-Type: application/json")
    @POST(Constants.DEEP_LINK)
    suspend fun createDeepLink(@Body linkData: LinkData): Response<CreateLinkResponse>
}