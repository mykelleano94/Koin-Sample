package com.neds.koinsample

import com.neds.koinsample.data.api.UserApiService
import com.neds.koinsample.data.model.BaseViewModel
import com.neds.koinsample.data.repository.UserRepository
import com.neds.koinsample.ui.main.UserViewModel
import com.neds.koinsample.util.NetworkUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun initOkHttpClient(): OkHttpClient =
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else
        OkHttpClient.Builder().build()

val modules = module {

    // Web service
    single { initOkHttpClient() }
    single {
        Retrofit
            .Builder()
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    single { NetworkUtil(get()) }

    // Services
    single { get<Retrofit>().create(UserApiService::class.java) as UserApiService }

    // Repositories
    single { UserRepository(get()) }

    // ViewModel
    viewModel { BaseViewModel() }
    viewModel { UserViewModel(get(), get()) }
}
