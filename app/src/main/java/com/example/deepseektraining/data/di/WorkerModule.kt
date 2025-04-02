package com.example.deepseektraining.data.di

/*import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.MapKey
import kotlin.reflect.KClass
import javax.inject.Provider
import javax.inject.Singleton

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Module
@InstallIn(SingletonComponent::class)
interface WorkerFactoryModule {
    @dagger.Binds
    @IntoMap
    @WorkerKey(MovieUpdateWorker::class)
    fun bindMovieUpdateWorker(factory: MovieUpdateWorker.Factory): ChildWorkerFactory
}

interface ChildWorkerFactory {
    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    @Provides
    @Singleton
    fun provideAppWorkerFactory(
        workerFactories: Map<Class<out ListenableWorker>, @JvmSuppressWildcards Provider<ChildWorkerFactory>>
    ): AppWorkerFactory {
        return AppWorkerFactory(workerFactories)
    }
}*/