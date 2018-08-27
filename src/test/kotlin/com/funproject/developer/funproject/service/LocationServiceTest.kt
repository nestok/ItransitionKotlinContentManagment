package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.model.Location
import com.funproject.developer.funproject.repository.LocationRepository
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean


@RunWith(SpringRunner::class)
@SpringBootTest
class LocationServiceTest {

    @Autowired
    private lateinit var locationService: LocationService

    @MockBean
    private lateinit var locationRepository: LocationRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addLocationAndFindItTest() {
        val location = Location(name = "Bathroom")
        locationService.addLocation(location)
        Mockito.verify(locationRepository).save(location)
    }

    @Test
    fun findAllRepliesTest() {
        val result = locationService.findAllLocations()
        assertNotNull(result)
    }

}