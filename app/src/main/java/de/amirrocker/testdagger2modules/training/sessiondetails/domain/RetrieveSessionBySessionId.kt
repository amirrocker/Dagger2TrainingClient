package de.amirrocker.testdagger2modules.training.sessiondetails.domain

import de.amirrocker.testdagger2modules.base.common.rx.UnwrapOptionTransformer
import de.amirrocker.testdagger2modules.base.domain.ReactiveInteractor
import de.amirrocker.testdagger2modules.home.data.trainingSession.*
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionId
import de.amirrocker.testdagger2modules.training.sessiondetails.data.TrainingSessionDetailsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import polanski.option.Option
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RetrieveSessionBySessionId @Inject constructor(
    val trainingSessionDetailsRepository: TrainingSessionDetailsRepository
) : ReactiveInteractor.RetrieveInteractor<String, TrainingSession> {


    override fun getBehaviorStream(params: Option<String>): Observable<TrainingSession> {
        return trainingSessionDetailsRepository.getTrainingSessionBySessionId(params.orDefault { "ID1" })
            .flatMapSingle { session:Option<TrainingSession> ->
                fetchSessionsWhenNone(session)
            }
            .compose(UnwrapOptionTransformer.create())
    }

    private fun fetchSessionsWhenNone(sessions:Option<TrainingSession>): Single<Option<TrainingSession>> {
        return fetchWhenNone(sessions).andThen(Single.just(sessions))
    }

    fun fetchWhenNone(session:Option<TrainingSession>):Completable {
        if(session.isNone) {
            return trainingSessionDetailsRepository
                        .fetchTrainingSession(getTrainingSessionId(session))
        } else {
            return Completable.complete()
        }
    }

    private fun getTrainingSessionId(sessions: Option<TrainingSession>):TrainingSessionId {
        val trainingSession = sessions
            .orDefault { createTrainingSession() }
        return trainingSession.sessionId.orEmpty()
    }

    private fun createTrainingSession() =
        TrainingSession("ID-9999",
            "Title-9999",
            "We should not see this session! There is an error somewhere along the way.", "", "", "",
            listOf(Training("", "", "", "", "",
                ModelParameter("", "", ""), Log(""),
                HyperParameter("", "")
            ))
        )



    fun getMockBehaviorStream(params: Option<String>): Observable<List<TrainingSession>> {
        return Observable.empty()
    }
}