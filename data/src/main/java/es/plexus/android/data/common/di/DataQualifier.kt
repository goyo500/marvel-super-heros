package es.plexus.android.data.common.di

import javax.inject.Qualifier

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcherNamed

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkHttpClientNamed

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkManagerNamed

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SuperHeroApiClientNamed
