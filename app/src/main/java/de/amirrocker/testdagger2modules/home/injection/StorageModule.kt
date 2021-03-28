package de.amirrocker.testdagger2modules.home.injection

import dagger.Binds
import dagger.Module
import de.amirrocker.testdagger2modules.home.data.account.InMemoryStorage
import de.amirrocker.testdagger2modules.home.data.account.Storage

@Module
abstract class StorageModule {

//    @Provides
//    @Singleton
//    fun providesStorage(storage:Storage) = InMemoryStorage()

    // tells Dagger to use the specific implementation
    // name of method is 'wurscht' - parameter type and return type are what dagger looks at.
    @Binds
    abstract fun provideStorage(storage: InMemoryStorage): Storage


}