package com.rideshare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rideshare.model.Ride
import com.rideshare.network.Resource
import com.rideshare.repository.RideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideViewModel @Inject constructor(
    private val repository: RideRepository
) : ViewModel() {

    private val _rideState = MutableStateFlow<Resource<Ride>>(Resource.Idle())
    val rideState: StateFlow<Resource<Ride>> = _rideState

    private val _rideHistory = MutableStateFlow<Resource<List<Ride>>>(Resource.Idle())
    val rideHistory: StateFlow<Resource<List<Ride>>> = _rideHistory

    fun requestRide(originLat: Double, originLng: Double, destLat: Double, destLng: Double) {
        viewModelScope.launch {
            repository.requestRide(originLat, originLng, destLat, destLng).collectLatest {
                _rideState.value = it
            }
        }
    }

    fun loadRideHistory() {
        viewModelScope.launch {
            repository.getRideHistory().collectLatest { _rideHistory.value = it }
        }
    }
}
