package com.lxc.recruitment.di

import com.lxc.recruitment.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return HttpService.getUser()
    }

    @Singleton
    @Provides
    fun providePersonalService(): PersonalService {
        return HttpService.getPersonal()
    }

    @Singleton
    @Provides
    fun provideCompanyService(): CompanyService {
        return HttpService.getCompany()
    }

    @Singleton
    @Provides
    fun provideAdminService(): AdminService {
        return HttpService.getAdmin()
    }
}