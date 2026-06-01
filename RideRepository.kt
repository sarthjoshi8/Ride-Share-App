package com.rideshare.repository

import com.rideshare.model.Ride
import com.rideshare.network.ApiService
import com.rideshare.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RideRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun requestRide(originLat: Double, originLng: Double, destLat: Double, destLng: Double): Flow<Resource<Ride>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.requestRide(
                mapOf(
                    "originLat" to originLat, "originLng" to originLng,
                    "destLat" to destLat, "destLng" to destLng
                )
            )
            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error("Failed to request ride: ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getRideHistory(): Flow<Resource<List<Ride>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getRideHistory()
            if (response.isSuccessful) emit(Resource.Success(response.body() ?: emptyList()))
            else emit(Resource.Error("Error: ${response.message()}"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }
}
