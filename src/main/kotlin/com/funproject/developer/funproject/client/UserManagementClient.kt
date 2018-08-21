package com.funproject.developer.funproject.client

import com.funproject.developer.funproject.dto.userDto.ContributorDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(value = "clm-user-management-service", path = "user")
interface UserManagementClient {

    @GetMapping("/loadAllContributors")
    fun findAllContributors(): ArrayList<ContributorDto>

}