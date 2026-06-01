package com.rideshare.model

import com.google.android.gms.maps.model.LatLng

data class Ride(
    val id: String = "",
    val driverId: String = "",
    val passengerId: String = "",
    val origin: LatLng = LatLng(0.0, 0.0),
    val destination: LatLng = LatLng(0.0, 0.0),
    val originName: String = "",
    val destinationName: String = "",
    val status: RideStatus = RideStatus.SEARCHING,
    val fare: Double = 0.0,
    val distanceKm: Double = 0.0,
    val durationMin: Int = 0,
    val driverName: String = "",
    val driverRating: Float = 0f,
    val vehicleNumber: String = "",
    val createdAt: Long = System.currentTimeMillis(),
)

enum class RideStatus { SEARCHING, MATCHED, DRIVER_ARRIVING, IN_PROGRESS, COMPLETED, CANCELLED }
