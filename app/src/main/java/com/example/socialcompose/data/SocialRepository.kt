package com.example.socialcompose.data

import com.example.socialcompose.model.Social
import com.example.socialcompose.model.SocialData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SocialRepository {
    private val socials = mutableListOf<Social>()

    init {
        if (socials.isEmpty()) {
            SocialData.socials.forEach {
                socials.add(
                    Social(
                        id = it.id,
                        name = it.name,
                        desc = it.desc,
                        image = it.image,
                        realease = it.realease
                    )
                )
            }
        }
    }

    fun searchByName(query: String): Flow<List<Social>> {
        return getAllSocials().map { social ->
            social.filter { social ->
                social.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun getAllSocials(): Flow<List<Social>> {
        return flowOf(socials)
    }

    fun getOrderSocialById(socialId: Int): Social {
        return socials.first {
            it.id == socialId
        }
    }

    companion object {
        @Volatile
        private var instance: SocialRepository? = null

        fun getInstance(): SocialRepository =
            instance ?: synchronized(this) {
                SocialRepository().apply {
                    instance = this
                }
            }
    }
}