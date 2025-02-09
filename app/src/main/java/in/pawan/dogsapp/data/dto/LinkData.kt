package `in`.pawan.dogsapp.data.dto

import com.google.gson.annotations.SerializedName
import `in`.pawan.dogsapp.BuildConfig


data class LinkResponse(
    val data: LinkData,
    val type: Int,
    val feature: String
)

data class LinkData(
    @SerializedName("+url") val url: String = "",
    @SerializedName("\$marketing_title") val marketingTitle: String = "",
    @SerializedName("~link_type") val linkType: String = "",
    @SerializedName("~creation_source") val creationSource: Int = 0,
    @SerializedName("~marketing") val marketing: Boolean = false,
    @SerializedName("branch_key") val branchKey: String = BuildConfig.branchKey,
    @SerializedName("~id") val id: String = "",
    val breed: String = "",
    @SerializedName("~feature") val feature: String = "",
)

data class CreateLinkResponse(
    val url: String
)