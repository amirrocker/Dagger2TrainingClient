package de.amirrocker.testdagger2modules.training.sessiondetails.presentation

import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import io.reactivex.functions.Function
import javax.inject.Inject

class TrainingSessionDetailsCardViewEntityMapper @Inject constructor() : Function<TrainingSession, TrainingSessionDetailsCardViewEntity> {

    override fun apply(t: TrainingSession): TrainingSessionDetailsCardViewEntity {
        return TrainingSessionDetailsCardViewEntity(
            t.sessionId,
            t.title,
            t.version,
            t.createdAt,
            t.trainings,
            t.user,
            t.description
        )
    }


}