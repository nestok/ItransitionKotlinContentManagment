package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.Location
import com.funproject.developer.funproject.service.LocationService
import com.funproject.developer.funproject.service.LocationServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/location")
class LocationController @Autowired constructor(
        private val locationService: LocationService
) {

    @GetMapping("/")
    fun findAllLocations(): ArrayList<Location> {
        return locationService.findAllLocations()
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun addLocation(@RequestBody location: Location) {
        locationService.addLocation(location)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun editLocation(@RequestBody location: Location) {
        locationService.editLocation(location)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteLocation(@PathVariable(value = "id") id: Long) {
        locationService.deleteLocation(id)
    }

}