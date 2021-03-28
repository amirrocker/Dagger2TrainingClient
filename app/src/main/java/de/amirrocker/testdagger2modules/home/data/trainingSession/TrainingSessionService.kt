package de.amirrocker.testdagger2modules.home.data.trainingSession

import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionId
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainingSessionService {

    @GET("/api/simpleTraining")
    fun getTrainingSessions():Single<List<TrainingSessionRaw>>

}