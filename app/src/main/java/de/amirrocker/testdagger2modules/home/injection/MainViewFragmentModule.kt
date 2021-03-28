package de.amirrocker.testdagger2modules.home.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import dagger.Binds
import dagger.Module
import dagger.Provides
import de.amirrocker.testdagger2modules.base.common.precondition.AndroidPrecondition
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ItemComparator
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.RecyclerViewAdapter
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderBinder
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderFactory
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentItemComparator
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentViewModel
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewHolder

import dagger.multibindings.*
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionConstants


@Module
abstract class MainViewFragmentModule {
//class MainViewFragmentModule {

//    @Provides
//    @Singleton
//    fun providesStorage(storage:Storage) = InMemoryStorage()

    // tells Dagger to use the specific implementation
    // name of method is 'wurscht' - parameter type and return type are what dagger looks at.
    @Binds
    abstract fun provideRecyclerViewAdapter(recyclerViewAdapter: RecyclerViewAdapter): RecyclerView.Adapter<RecyclerView.ViewHolder>

    @Binds
    abstract fun providesItemComparator(itemComparator: MainViewFragmentItemComparator ): ItemComparator

//    @Binds
//    @IntoMap
//    @IntKey(TrainingSessionConstants.DisplayableTypes.ACTIVE)
//    abstract fun providesTrainingSessionCardViewHolderFactory(factory:TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory):ViewHolderFactory
//
//    @Binds
//    @IntoMap
//    @IntKey(TrainingSessionConstants.DisplayableTypes.ACTIVE)
//    abstract fun providesTrainingSessionCardViewHolderBinder(binder:TrainingSessionCardViewHolder.TrainingSessionCardViewHolderBinder):ViewHolderBinder




//    @Provides
//    fun providesRecyclerViewAdapter(itemComparator: ItemComparator,
//                                    factoryMap: Map<Int, ViewHolderFactory>,
//                                    binderMap: Map<Int, ViewHolderBinder>,
//                                    androidPrecondition: AndroidPrecondition
//    ): RecyclerViewAdapter = RecyclerViewAdapter(itemComparator, factoryMap, binderMap, androidPrecondition)
//
//    @Provides
//    fun providesItemComparator():ItemComparator = MainViewFragmentItemComparator()

//    @Provides
//    fun providesTrainingSessionCardViewHolderFactory(context: Context):ViewHolderFactory {
//        return TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory(context)
//    }
//
//    @Provides
//    fun providesTrainingSessionCardViewHolderBinder():ViewHolderBinder {
//        return TrainingSessionCardViewHolder.TrainingSessionCardViewHolderBinder()
//    }


}