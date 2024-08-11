package com.be.havesomefun.presentation.response

import com.be.havesomefun.application.dto.IOLDto

class BookMarkResponse {
    var id: Long? = null
    var addDate: String? = null
    var location: String? = null
    var time: String? = null
    var geohash7: String? = null

    companion object {
        fun of (iolDto: IOLDto) : BookMarkResponse {
            val bookMarkResponse = BookMarkResponse()
            bookMarkResponse.id = iolDto.id
            bookMarkResponse.addDate = iolDto.createdTime
            bookMarkResponse.location = "경상북도 포항시"
            bookMarkResponse.time = iolDto.time
            bookMarkResponse.geohash7 = iolDto.geohash7

            return bookMarkResponse
        }
    }
}