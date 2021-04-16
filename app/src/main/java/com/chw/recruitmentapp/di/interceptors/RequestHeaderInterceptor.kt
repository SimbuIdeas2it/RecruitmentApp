package co.openapp.bbc.di.interceptors

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.chw.recruitmentapp.data.repository.local.LocalRepository
import com.chw.recruitmentapp.data.repository.local.model.error.ErrorResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.net.ssl.SSLPeerUnverifiedException

class RequestHeaderInterceptor @Inject constructor(
    private val repository: LocalRepository,
    private val context: Context
) : Interceptor {

    companion object {
        private const val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
        private const val READ_TIMEOUT = "READ_TIMEOUT"
        private const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
//    override fun intercept(chain: Interceptor.Chain?): Response {
        val requestBuilder = chain?.request()?.newBuilder()!!
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json")
            .addHeader("x-device-id", getDeviceId())
            .addHeader("x-device-type", getDeviceType())
            .addHeader("x-device-tzoffset", getDeviceTimeZoneOffset())
                .addHeader("Dentulu-Userid", "2")
            .addHeader("x-app-name", getAppName())

        if (repository.getAccessToken() != null) {
            requestBuilder.addHeader("Authorization", "" + repository.getAccessToken()!!)
            //requestBuilder.addHeader("Authorization", "Bearer " + repository.getAccessToken()!!)
        }

        try {
            val request = chain.request()

            var connectTimeout = chain.connectTimeoutMillis()
            var readTimeout = chain.readTimeoutMillis()
            var writeTimeout = chain.writeTimeoutMillis()

            request.header(CONNECT_TIMEOUT)?.also {
                connectTimeout = it.toInt()
                requestBuilder.removeHeader(CONNECT_TIMEOUT)
            }

            request.header(READ_TIMEOUT)?.also {
                readTimeout = it.toInt()
                requestBuilder.removeHeader(READ_TIMEOUT)
            }

            request.header(WRITE_TIMEOUT)?.also {
                writeTimeout = it.toInt()
                requestBuilder.removeHeader(WRITE_TIMEOUT)
            }
            chain.run {
                withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
                withReadTimeout(readTimeout, TimeUnit.SECONDS)
                withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
            }
//            chain.call()
            val response = chain.proceed(requestBuilder.build())
            if (response.code < 200 || response.code > 299 || response.code == 404) {
                val errorBody = response?.body?.string()
                val errorObj = Gson().fromJson(errorBody, ErrorResponse::class.java)
                Log.e("errorBody", " == " + Gson().toJson(errorBody))
                if (errorObj != null) {
                    when (errorObj.code) {
                        1001, 1013, 1027, in 1007..1010, in 1022..1025, 500 -> {
                            throw Exception(errorObj.message, Throwable(errorObj.code.toString()))
                        }
                        else -> {
                            return response
                        }
                    }
                } else {
                    return response
                }
            }
            return response
        } catch (e: SocketTimeoutException) {
            throw SocketTimeoutException()
        } catch (e: UnknownHostException) {
            throw UnknownHostException()
        } catch (e: SSLPeerUnverifiedException) {
            throw SSLPeerUnverifiedException("Peer not authenticated")
        }
    }

    private fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    private fun getDeviceType(): String {
        return "android"
    }

    private fun getDeviceTimeZoneOffset(): String {
        return GregorianCalendar().timeZone.rawOffset.toString()
    }

    private fun getAppName(): String {
        return "elsa_sales_mobile"
    }
}