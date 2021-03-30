package de.amirrocker.testdagger2modules.home.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import de.amirrocker.testdagger2modules.base.injection.scopes.ActivityScope
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.RecyclerViewAdapter
//import dagger.multibindings.*
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderBinder
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderFactory
import de.amirrocker.testdagger2modules.base.presentation.utils.ViewModelUtil
import de.amirrocker.testdagger2modules.home.presentation.MainViewFragmentViewModel
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewHolder

/**
 * TODO !! refactor this module out ! contents can be moved into MainViewFragmentModule once we can build and run
 *
 */
@Module
class MainViewFragmentViewModelModule {

    @Provides
    fun provideViewModelProviderFactory(viewModelUtil:ViewModelUtil, viewModel:MainViewFragmentViewModel): ViewModelProvider.Factory {
        return viewModelUtil.createFor(viewModel)
    }

    @Provides
    fun provideFactoryMap(): Map<Int, ViewHolderFactory> = HashMap()

    @Provides
    fun provideBinderMap(): Map<Int, ViewHolderBinder> = HashMap()

    @Provides
//    @IntoMap
//    @IntKey(Repository.TYPE_NORMAL)
    fun providesTrainingSessionCardViewHolderFactory(context: Context):ViewHolderFactory {
        return TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory(context)
    }

    @Provides
    fun providesTrainingSessionCardViewHolderBinder():ViewHolderBinder {
        return TrainingSessionCardViewHolder.TrainingSessionCardViewHolderBinder()
    }

    @Provides
    @ActivityScope
    fun provideRecyclerViewAdapter(onTrainingSessionItemClicked: TrainingSessionCardViewHolder.OnTrainingSessionItemClicked): RecyclerView.Adapter<RecyclerView.ViewHolder> =
        RecyclerViewAdapter(onTrainingSessionItemClicked)

}