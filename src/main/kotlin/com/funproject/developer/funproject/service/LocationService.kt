package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.repository.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class LocationService @Autowired constructor(
        private val locationRepository: LocationRepository
) {

    fun findAllLocations(): ArrayList<Location> {
        return locationRepository!!.findAll()
    }

    fun addLocation(location: Location) {
        locationRepository.save(location)
    }

    fun editLocation(location: Location) {
        locationRepository.save(location)
    }

    fun deleteLocation(id: Long) {
        val deletedLocation: Location = locationRepository.findById(id).orElse(null)
                ?: throw EntityNotFoundException("Location not found")
        locationRepository.delete(deletedLocation)
    }

}