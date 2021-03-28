package de.amirrocker.testdagger2modules.home.presentation

import de.amirrocker.testdagger2modules.home.data.trainingSession.Training

typealias TrainingSessionId = String
typealias TrainingSessionCardViewEntityTitle = String
typealias TrainingSessionCardViewEntityVersion = String
typealias TrainingSessionCardViewEntityCreatedAt = String
typealias TrainingSessionCardViewEntityTrainingsList = List<Training>
typealias TrainingSessionCardViewEntityUser = String
typealias TrainingSessionCardViewEntityDescription = String

class TrainingSessionCardViewEntity(
    var _sessionId:TrainingSessionId,
    var _title:TrainingSessionCardViewEntityTitle,
    var _version:TrainingSessionCardViewEntityVersion,
    var _createdAt: TrainingSessionCardViewEntityCreatedAt,
    var _trainings: TrainingSessionCardViewEntityTrainingsList,
    var _user: TrainingSessionCardViewEntityUser,
    var _description: TrainingSessionCardViewEntityDescription

) {

    fun id(pId:TrainingSessionId): TrainingSessionCardViewEntity {
        this._sessionId = pId
        return this
    }

    fun title(pTitle:TrainingSessionCardViewEntityTitle): TrainingSessionCardViewEntity {
        this._title = pTitle
        return this
    }

    fun version(pVersion:TrainingSessionCardViewEntityVersion): TrainingSessionCardViewEntity {
        this._version = pVersion
        return this
    }

    fun createdAt(pCreatedAt: TrainingSessionCardViewEntityCreatedAt): TrainingSessionCardViewEntity {
        this._createdAt = pCreatedAt
        return this
    }

    fun user(pUser: TrainingSessionCardViewEntityUser): TrainingSessionCardViewEntity {
        this._user = pUser
        return this
    }

    fun description(pDescription: TrainingSessionCardViewEntityDescription): TrainingSessionCardViewEntity {
        this._description = pDescription
        return this
    }

    fun trainings(pTrainings: TrainingSessionCardViewEntityTrainingsList): TrainingSessionCardViewEntity {
        this._trainings = pTrainings
        return this
    }

}
