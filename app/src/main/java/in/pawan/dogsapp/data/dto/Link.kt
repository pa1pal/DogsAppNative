package `in`.pawan.dogsapp.data.dto

import com.google.gson.annotations.SerializedName



data class Link(
    @SerializedName("alias") val alias: String? = null,
    @SerializedName("branch_key") val branchKey: String? = null,
    @SerializedName("campaign") val campaign: String? = null,
    @SerializedName("channel") val channel: String? = null,
    @SerializedName("data") val data: Data? = Data(),
    @SerializedName("feature") val feature: String? = null,
    @SerializedName("stage") val stage: String? = null,
    @SerializedName("tags") val tags: List<String>? = listOf(),
)

data class Data(
    @SerializedName("\$android_url") val androidUrl: String? = null,
    @SerializedName("\$android_url_xx") val androidUrlXx: String? = null,
    @SerializedName("\$android_wechat_url") val androidWechatUrl: String? = null,
    @SerializedName("\$blackberry_url") val blackberryUrl: String? = null,
    @SerializedName("~ad_set_id") val adSetId: String? = null,
    @SerializedName("~ad_set_name") val adSetName: String? = null,
    @SerializedName("~agency_id") val agencyId: String? = null,
    @SerializedName("~branch_ad_format") val branchAdFormat: String? = null,
    @SerializedName("~campaign") val campaign: String? = null,
    @SerializedName("~campaign_id") val campaignId: String? = null,
    @SerializedName("~channel") val channel: String? = null,
    @SerializedName("~creative_name") val creativeName: String? = null,
    @SerializedName("~customer_placement") val customerPlacement: String? = null,
    @SerializedName("~feature") val feature: String? = null,
    @SerializedName("~id") val id: String? = null,
    @SerializedName("~link_type") val linkType: String? = null,
    @SerializedName("~marketing") val marketing: String? = null,
    @SerializedName("~secondary_ad_format") val secondaryAdFormat: String? = null,
    @SerializedName("~stage") val stage: String? = null,
    @SerializedName("~tags") val tags: List<String>? = listOf(),
    @SerializedName("\$canonical_url") val canonicalUrl: String? = null,
    @SerializedName("~creation_source") val creationSource: String? = null,
    @SerializedName("\$deeplink_path") val deeplinkPath: String? = null,
    @SerializedName("\$desktop_url") val desktopUrl: String? = null,
    @SerializedName("\$desktop_web_only") val desktopWebOnly: Boolean? = null,
    @SerializedName("\$fallback_url") val fallbackUrl: String? = null,
    @SerializedName("\$fallback_url_xx") val fallbackUrlXx: String? = null,
    @SerializedName("\$fire_url") val fireUrl: String? = null,
    @SerializedName("\$huawei_url") val huaweiUrl: String? = null,
    @SerializedName("\$ipad_url") val ipadUrl: String? = null,
    @SerializedName("\$ios_url") val iosUrl: String? = null,
    @SerializedName("\$ios_url_xx") val iosUrlXx: String? = null,
    @SerializedName("\$ios_wechat_url") val iosWechatUrl: String? = null,
    @SerializedName("\$mobile_web_only") val mobileWebOnly: Boolean? = null,
    @SerializedName("\$og_description") val ogDescription: String? = null,
    @SerializedName("\$og_image_url") val ogImageUrl: String? = null,
    @SerializedName("\$og_title") val ogTitle: String? = null,
    @SerializedName("\$samsung_url") val samsungUrl: String? = null,
    @SerializedName("\$web_only") val webOnly: Boolean? = null,
    @SerializedName("\$windows_phone_url") val windowsPhoneUrl: String? = null,

)

