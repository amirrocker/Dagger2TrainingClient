package de.amirrocker.testdagger2modules.home.data.trainingSession

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface TrainingSessionService {

    @GET("api/simpleTraining")
    fun getTrainingSessions():Single<List<TrainingSessionRaw>>

}