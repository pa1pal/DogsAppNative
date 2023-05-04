package `in`.pawan.dogsapp

import android.util.Log
import io.branch.referral.BranchError
import io.branch.referral.network.BranchRemoteInterface
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

class OkhttpBranchNetworkInterface : BranchRemoteInterface() {

    private val TAG: String? = "OkhttpBranchNetworkInterface"
    private val okHttpClient =
        OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS).writeTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS).build()

    override fun doRestfulGet(url: String?): BranchResponse {
        val request = url?.let { endPoint ->
            Request.Builder().url(endPoint).build()
        }

        Log.d(TAG, "doRestfulGet: $url")

        return handleNetworkRequest(request)
    }

    override fun doRestfulPost(url: String?, payload: JSONObject?): BranchResponse {
        val request = url?.let { endPoint ->
            Request.Builder().url(endPoint).post(
                payload.toString().toRequestBody("application/json".toMediaTypeOrNull())
            ).build()
        }

        Log.d(TAG, "doRestfulPost: $url")
        return handleNetworkRequest(request)

    }

    private fun handleNetworkRequest(request: Request?): BranchResponse {
        try {
            val response = request?.let { req ->
                okHttpClient.newCall(req).execute()
            }

            val rb = response?.body
            if (rb != null){
                val rbObject = JSONObject(rb.string())
                val referringParams = JSONObject(rbObject.getString("data"))
                if (referringParams.has("+clicked_branch_link") && referringParams.get("+clicked_branch_link") == true) {
//                    val branchUniversalObject =
//                        referringParams.toString(),. toObject<BUODeeplinkParams>()
                }
            }
            return response?.code?.let { code ->
                BranchResponse(rb?.string(), code)
            }!!
        }catch(exception : Exception) {
            if (exception is SocketTimeoutException) {
                // add desired retry logic, then eventually throw BranchError.ERR_BRANCH_REQ_TIMED_OUT
                throw BranchRemoteException(BranchError.ERR_BRANCH_REQ_TIMED_OUT)
            }else{
                // handle other failures as needed, throw BranchError.ERR_BRANCH_NO_CONNECTIVITY as a default
                throw BranchRemoteException(BranchError.ERR_BRANCH_NO_CONNECTIVITY)
            }
        }
    }
}