package de.amirrocker.testdagger2modules.home.injection

import dagger.Binds
import dagger.Module
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ItemComparator
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentItemComparator
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentViewModel
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewHolder


@Module
abstract class MainViewFragmentModule {

    // tells Dagger to use the specific implementation
    // name of method is 'wurscht' - parameter type and return type are what dagger looks at.
//    @Binds
//    abstract fun provideRecyclerViewAdapter(recyclerViewAdapter: RecyclerViewAdapter): RecyclerView.Adapter<RecyclerView.ViewHolder>

    @Binds
    abstract fun providesItemComparator(itemComparator: MainViewFragmentItemComparator ): ItemComparator

    @Binds
    abstract fun providesOnTrainingSessionItemClicked( mainViewFragmentViewModel: MainViewFragmentViewModel ): TrainingSessionCardViewHolder.OnTrainingSessionItemClicked

}


//@Module
//class MainViewFragmentModule {
//
//    // tells Dagger to use the specific implementation
//    // name of method is 'wurscht' - parameter type and return type are what dagger looks at.
//    @Provides
//    @ActivityScope
//    fun provideRecyclerViewAdapter(onTrainingSessionItemClicked: TrainingSessionCardViewHolder.OnTrainingSessionItemClicked): RecyclerView.Adapter<RecyclerView.ViewHolder> =
//        RecyclerViewAdapter(onTrainingSessionItemClicked)
//
////    @Binds
////    fun providesItemComparator(itemComparator: MainViewFragmentItemComparator ): ItemComparator
//
//}