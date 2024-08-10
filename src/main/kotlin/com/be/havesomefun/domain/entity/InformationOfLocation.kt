package com.be.havesomefun.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class InformationOfLocation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var time: String? = null,
    var geohash: String? = null,
    var population: Int? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var si: String? = null,
    var gu: String? = null,
    var dong: String? = null
)