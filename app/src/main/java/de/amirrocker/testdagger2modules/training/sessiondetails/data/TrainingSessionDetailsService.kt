package de.amirrocker.testdagger2modules.training.sessiondetails.data

import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSessionRaw
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionId
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainingSessionDetailsService {

    @GET("/api/trainingSession/{trainingSessionId}")
    fun getTrainingSessionById(@Path("trainingSessionId") trainingSessionId: TrainingSessionId):Single<TrainingSessionRaw>

}