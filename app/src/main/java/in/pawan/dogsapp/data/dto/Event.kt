package `in`.pawan.dogsapp.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.Keep
import `in`.pawan.dogsapp.BuildConfig
import kotlinx.parcelize.RawValue

@Keep
@Parcelize
data class Event(
    @SerializedName("branch_key")
    val branchKey: String? = BuildConfig.branchKey,
    @SerializedName("custom_data")
    val customData: CustomData? = null,
    @SerializedName("customer_event_alias")
    val customerEventAlias: String? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("user_data")
    val userData: UserData?,
    @SerializedName("event_data")
    val eventData: EventData,
//    @kotlinx.android.parcel.RawValue
//    @SerializedName("content_items")
//    val contentItems: List<ContentItems>
) : Parcelable

@Keep
data class ContentItems(
    @SerializedName("\$content_schema")
    val contentSchema: String? = "COMMERCE_PRODUCT",
    @SerializedName("\$og_title")
    val ogTitle: String? = "Nike Shoe",
    @SerializedName("\$og_description")
    val ogDescription: String? = "Start loving your steps",
    @SerializedName("\$og_image_url")
    val ogImageUrl: String? = "http://example.com/img1.jpg",
    @SerializedName("\$canonical_identifier")
    val canonicalIdentifier: String? = "nike/1234",
    @SerializedName("\$publicly_indexable")
    val publiclyIndexable: Boolean? = false,
    @SerializedName("\$price")
    val price: Double? = 101.2,
    @SerializedName("\$locally_indexable")
    val locallyIndexable: Boolean? = true,
    @SerializedName("\$quantity")
    val quantity: Int? = 1,
    @SerializedName("\$sku")
    val sku: String? = "1101123445",
    @SerializedName("\$product_name")
    val productName: String? = "Runner",
    @SerializedName("\$product_brand")
    val productBrand: String? = "Nike",
    @SerializedName("\$product_category")
    val productCategory: String? = "Sporting Goods",
    @SerializedName("\$product_variant")
    val productVariant: String? = "XL",
    @SerializedName("\$rating_average")
    val ratingAverage: Double? = 4.2,
    @SerializedName("\$rating_count")
    val ratingCount: Int? = 5,
    @SerializedName("\$rating_max")
    val ratingMax: Double? = 2.2,
    @SerializedName("\$creation_timestamp")
    val creationTimestamp: Long? = 1499892854966,
    @SerializedName("\$exp_date")
    val expDate: Long? = 1499892854966,
    @SerializedName("\$keywords")
    val keywords: List<String>? = listOf("sneakers", "shoes"),
    @SerializedName("\$address_street")
    val addressStreet: String? = "230 South LaSalle Street",
    @SerializedName("\$address_city")
    val addressCity: String? = "Chicago",
    @SerializedName("\$address_region")
    val addressRegion: String? = "IL",
    @SerializedName("\$address_country")
    val addressCountry: String? = "US",
    @SerializedName("\$address_postal_code")
    val addressPostalCode: String? = "60604",
)

@Keep
@Parcelize
data class EventData(
    @SerializedName("custom_param_1")
    val customParam1: String? = null,
    @SerializedName("custom_param_2")
    val customParam2: String? = null,
    @SerializedName("transaction_id")
    val transactionId: String? = "trans_Id_1232343434",
    @SerializedName("currency")
    val currency: String? = "USD",
    @SerializedName("revenue")
    val revenue: Double? = 180.2,
    @SerializedName("shipping")
    val shipping: Double? = 10.5,
    @SerializedName("tax")
    val tax: Double? = 13.5,
    @SerializedName("coupon")
    val coupon: String? = "",
    @SerializedName("custom_param_3")
    val customParam3: String? = null

) : Parcelable

@Keep
@Parcelize
data class CustomData(
    @SerializedName("breed")
    val breed: String? = null
) : Parcelable

@Keep
@Parcelize
class Metadata : Parcelable

@Keep
@Parcelize
data class UserData(
    @SerializedName("aaid")
    val aaid: String? = null,
    @SerializedName("advertising_ids")
    val advertisingIds: AdvertisingIds? = null,
    @SerializedName("android_id")
    val androidId: String? = null,
    @SerializedName("app_version")
    val appVersion: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("developer_identity")
    val developerIdentity: String? = null,
    @SerializedName("environment")
    val environment: String? = null,
    @SerializedName("ip")
    val ip: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("limit_ad_tracking")
    val limitAdTracking: Boolean? = null,
    @SerializedName("local_ip")
    val localIp: String? = null,
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("os")
    val os: String? = null,
    @SerializedName("os_version")
    val osVersion: Int? = null,
    @SerializedName("screen_dpi")
    val screenDpi: Int? = null,
    @SerializedName("screen_height")
    val screenHeight: Int? = null,
    @SerializedName("screen_width")
    val screenWidth: Int? = null
) : Parcelable

@Keep
@Parcelize
data class AdvertisingIds(
    @SerializedName("oaid")
    val oaid: String? = null
) : Parcelable