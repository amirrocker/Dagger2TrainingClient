package de.amirrocker.testdagger2modules.training.sessiondetails.data

import de.amirrocker.testdagger2modules.base.data.store.ReactiveStore
import de.amirrocker.testdagger2modules.home.data.account.UserManager
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSessionMapper
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionId
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import javax.inject.Inject

class TrainingSessionDetailsRepository @Inject constructor(
    private val accountService: UserManager,
    private val trainingSessionMapper:TrainingSessionMapper,
    private val trainingSessionDetailsService: TrainingSessionDetailsService,
    private val store:ReactiveStore<String, TrainingSession>
) {

    fun getTrainingSessionBySessionId(key:TrainingSessionId):Observable<Option<TrainingSession>> {
        return store.getSingular(key)
    }

    fun fetchTrainingSession(sessionId:TrainingSessionId):Completable {
        return trainingSessionDetailsService.getTrainingSessionById(sessionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapObservable {
                Observable.just(it)
            }
            .map(trainingSessionMapper)
            .toList()
            .doOnSuccess(store::replaceAll)
            .toCompletable()
    }



}