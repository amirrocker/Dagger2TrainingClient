package de.amirrocker.testdagger2modules.home.data.trainingSession

import de.amirrocker.testdagger2modules.base.data.Aggregate
import de.amirrocker.testdagger2modules.base.data.AggregateRoot
import de.amirrocker.testdagger2modules.base.domain.base.Event
import java.util.*

/**
 * TrainingSession is an Aggregate containing the necessary entities and value objects to
 * compose a complete session with all the training runs.
 * Each session consists of an id, a createDate, a user who created the session,
 * a title and a description and of course most important a list of trainings associated
 * with this session. We
 *
 *
 *
 *
 */
data class TrainingSession(
    val sessionId:  String,
    val title:  String,
    val description:  String,
    val user:  String,
    val version:String,
    val createdAt:  String,
    val trainings:  List<Training>
) : AggregateRoot(UUID.randomUUID()) {



    override fun applyChange(event: Event<Aggregate>) {
        super.applyChange(event)

    }
}

// TODO move ??
data class TrainingSessionRaw(
    val sessionId:  String,
    val title:  String,
    val description:  String,
    val user:  String,
    val version:String,
    val createdAt:  String,
    val trainings:  List<Training>
) {}

// TODO move in their own separate classes and files
data class Log(
    val log: String
) {}

data class HyperParameter(
    val epochs: String,
    val learningRate: String
) {}

data class ModelParameter(
    val model: String,
    val optimizer: String,
    val loss:  String
) {}

class Training(
    val id: String,
    val env: String,
    val file: String,
    val createdAt: String,
    val executedAt: String,
    val modelParameters: ModelParameter,
    val log: Log,
    val hyperParameters: HyperParameter
) {}
