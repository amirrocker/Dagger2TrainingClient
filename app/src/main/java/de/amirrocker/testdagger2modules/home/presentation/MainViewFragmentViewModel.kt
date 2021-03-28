package de.amirrocker.testdagger2modules.home.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import de.amirrocker.testdagger2modules.home.domain.RetrieveTrainingSessionList
import de.amirrocker.testdagger2modules.registration.RegistrationActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import javax.inject.Inject

class MainViewFragmentViewModel @Inject constructor(
    val retrieveTrainingSessionList: RetrieveTrainingSessionList,
    val trainingSessionDisplayableItemMapper: TrainingSessionDisplayableItemMapper
) :ViewModel() {

    val TAG = MainViewFragmentViewModel::class.java.simpleName

    private val compositeDisposable : CompositeDisposable by lazy {
        CompositeDisposable()
    }

    val trainingSessionListLiveData: MutableLiveData<List<TrainingSession>> by lazy {
        MutableLiveData()
    }

    init {
        compositeDisposable.add(bindToTrainingSessions())
    }

    private fun bindToTrainingSessions(): Disposable {
        return retrieveTrainingSessionList
            .getBehaviorStream(Option.none())
            .observeOn(Schedulers.computation())
//            .map(trainingSessionDisplayableItemMapper) // without mapping to entites
            .subscribe(
                {
                    trainingSessionListLiveData.postValue(it)
                },
                {
                    error(it.localizedMessage)
                },
                {
                    println("MainViewFragmentViewModel onComplete called")
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

//    fun getTrainingSessionListLiveData() = trainingSessionListLiveData

    fun startNewTraining() {
        println("starting a new training in the existing session")
    }

}
