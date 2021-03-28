package de.amirrocker.testdagger2modules.home.domain

import de.amirrocker.testdagger2modules.base.common.rx.UnwrapOptionTransformer
import de.amirrocker.testdagger2modules.base.domain.ReactiveInteractor
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSessionRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import polanski.option.Option
import javax.inject.Inject

class RetrieveTrainingSessionList @Inject constructor(
    val trainingSessionRepository: TrainingSessionRepository
) : ReactiveInteractor.RetrieveInteractor<String, List<TrainingSession>> {


    override fun getBehaviorStream(params: Option<String>): Observable<List<TrainingSession>> {
        return trainingSessionRepository.getAllTrainingSessions()
            .flatMapSingle { sessions:Option<List<TrainingSession>> ->
                fetchSessionsWhenNone(sessions)
            }
            .compose(UnwrapOptionTransformer.create())

    }

    private fun fetchSessionsWhenNone(sessions:Option<List<TrainingSession>>): Single<Option<List<TrainingSession>>> {
        return fetchWhenNone(sessions).andThen(Single.just(sessions))
    }

    fun fetchWhenNone(sessions:Option<List<TrainingSession>>):Completable {
        if(sessions.isNone) {
            return trainingSessionRepository.fetchTrainingSessions()
        } else {
            return Completable.complete()
        }
    }


    fun getMockBehaviorStream(params: Option<String>): Observable<List<TrainingSession>> {
        return Observable.empty()
    }
}