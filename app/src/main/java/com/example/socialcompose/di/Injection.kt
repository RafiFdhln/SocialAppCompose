package com.example.socialcompose.di

import com.example.socialcompose.data.SocialRepository

object Injection {
    fun provideRepository(): SocialRepository {
        return SocialRepository.getInstance()
    }
}