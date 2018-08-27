package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.*
import com.funproject.developer.funproject.model.exception.EntityNotFoundException
import com.funproject.developer.funproject.model.exception.EntityUsesInDBException
import com.funproject.developer.funproject.repository.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


interface LocationService {

    fun findAllLocations(): ArrayList<Location>

    fun addLocation(location: Location)

    fun editLocation(location: Location)

    fun deleteLocation(id: Long)

}