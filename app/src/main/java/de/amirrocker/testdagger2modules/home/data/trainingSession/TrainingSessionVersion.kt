package de.amirrocker.testdagger2modules.home.data.trainingSession

import de.amirrocker.hadesGatekeeper.domain.common.DateRange

typealias VersionNumber = Int

data class TrainingSessionVersion(
    val versionNumber: VersionNumber,
    var versionStatus: TrainingSessionVersionStatus,
    private val policyStatus: TrainingSessionStatus,
    private val coverPeriod: DateRange,
    private val versionPeriod: DateRange,
)  {

}