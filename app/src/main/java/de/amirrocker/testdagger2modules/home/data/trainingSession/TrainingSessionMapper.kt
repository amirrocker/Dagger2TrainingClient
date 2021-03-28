package de.amirrocker.testdagger2modules.home.data.trainingSession

import io.reactivex.functions.Function
import javax.inject.Inject

class TrainingSessionMapper @Inject constructor() : Function<TrainingSessionRaw, TrainingSession> {

    override fun apply(t: TrainingSessionRaw): TrainingSession {
        return TrainingSession(
            t.sessionId,
            t.title,
            t.description,
            t.user,
            t.version,
            t.createdAt,
            t.trainings
        )
    }
}
