package com.be.havesomefun.domain.entity

import com.be.havesomefun.domain.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
data class InformationOfLocation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var time: String? = null,
    var avgDecibel: Double = 0.0,
    var avgLightLux: Double = 0.0,
    var population: Double = 0.0,
    var geohash7: String? = null,

) : BaseEntity()