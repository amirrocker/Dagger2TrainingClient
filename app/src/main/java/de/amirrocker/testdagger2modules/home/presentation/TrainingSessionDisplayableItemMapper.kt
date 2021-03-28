package de.amirrocker.testdagger2modules.home.presentation

import de.amirrocker.testdagger2modules.base.presentation.recyclerview.DisplayableItem
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSession
import io.reactivex.Flowable
import io.reactivex.functions.Function
import javax.inject.Inject

class TrainingSessionDisplayableItemMapper @Inject constructor(
    val trainingSessionCardViewEntityMapper: TrainingSessionCardViewEntityMapper
) : Function<List<TrainingSession>, List<DisplayableItem<Any>>> {

    override fun apply(listOfTrainingSessions: List<TrainingSession>): List<DisplayableItem<Any>> {
        return Flowable.fromIterable(listOfTrainingSessions)
            .map {
                wrapInDisplayableItem(trainingSessionCardViewEntityMapper.apply(it))
            }
            .toList()
            .blockingGet()
    }

    private fun wrapInDisplayableItem(viewEntity: TrainingSessionCardViewEntity):DisplayableItem<Any> =
        DisplayableItem.toDisplayableItem(viewEntity,
                TrainingSessionConstants.DisplayableTypes.ACTIVE
            )
}
