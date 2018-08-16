package com.funproject.developer.funproject.model

import javax.persistence.*

@Entity
@Table(name="person")
data class User(
    val username: String = "",

    var password: String = "",

    val email: String = "",

    val firstname: String = "",

    val lastname: String = "",
    
    var is_deleted: Boolean = false,

    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.ROLE_USER,

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1) {

}