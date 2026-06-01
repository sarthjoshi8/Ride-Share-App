package com.rideshare.network

import com.rideshare.model.Ride
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("api/rides/request")
    suspend fun requestRide(@Body body: Map<String, @JvmSuppressWildcards Any>): Response<Ride>

    @GET("api/rides/history")
    suspend fun getRideHistory(): Response<List<Ride>>

    @POST("api/rides/{id}/cancel")
    suspend fun cancelRide(@Path("id") rideId: String): Response<Ride>

    @POST("api/rides/{id}/rate")
    suspend fun rateRide(@Path("id") rideId: String, @Body body: Map<String, Any>): Response<Unit>
}

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()
}
