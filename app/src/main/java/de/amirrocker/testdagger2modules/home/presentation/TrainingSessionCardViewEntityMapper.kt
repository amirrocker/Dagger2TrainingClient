package de.amirrocker.testdagger2modules.home.presentation

import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import io.reactivex.functions.Function
import javax.inject.Inject

class TrainingSessionCardViewEntityMapper @Inject constructor() : Function<TrainingSession, TrainingSessionCardViewEntity> {

    override fun apply(t: TrainingSession): TrainingSessionCardViewEntity {
        return TrainingSessionCardViewEntity(
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