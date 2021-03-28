package de.amirrocker.testdagger2modules.application

import dagger.Module
import dagger.Provides
import de.amirrocker.testdagger2modules.base.common.providers.TimestampProvider
import de.amirrocker.testdagger2modules.base.data.store.MemoryReactiveStore
import de.amirrocker.testdagger2modules.base.data.store.ReactiveStore
import de.amirrocker.testdagger2modules.base.data.store.Store
import de.amirrocker.testdagger2modules.base.data.cache.Cache
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession


@Module
class PersistenceModule {

//    @Binds
//    abstract fun providePersistence(persistence:Cache<String, String>): Store.MemoryStore<String, String>


    @Provides
    fun provideCache():Store.MemoryStore<String, TrainingSession> = Cache<String, TrainingSession>(
        {
            it.toString()
        }, TimestampProvider(), 10000
    )

    @Provides
    fun provideReactiveStore():ReactiveStore<String, TrainingSession> = MemoryReactiveStore<String, TrainingSession>(
        {
            it.toString()
        }, provideCache()
    )

}