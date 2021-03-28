package de.amirrocker.testdagger2modules.home.data.trainingSession

import de.amirrocker.testdagger2modules.base.data.store.ReactiveStore
import de.amirrocker.testdagger2modules.home.data.account.UserManager
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import javax.inject.Inject

class TrainingSessionRepository @Inject constructor(
    private val accountService: UserManager,
    private val trainingSessionMapper:TrainingSessionMapper,
    private val trainingSessionService: TrainingSessionService,
    private val store:ReactiveStore<String, TrainingSession>
) {

    fun getAllTrainingSessions():Observable<Option<List<TrainingSession>>> {
        return store.getAll()
    }

    fun fetchTrainingSessions():Completable {
        return trainingSessionService.getTrainingSessions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMapObservable {
                Observable.fromIterable(it)
            }
            .map(trainingSessionMapper)
            .toList()
            .doOnSuccess(store::replaceAll)
            .toCompletable()
    }



}