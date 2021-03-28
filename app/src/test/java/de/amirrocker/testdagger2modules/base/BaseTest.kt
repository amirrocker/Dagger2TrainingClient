package de.amirrocker.base

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner.StrictStubs::class)
abstract class BaseTest {

    @Rule
    lateinit var rule: MockitoRule

    @Rule @JvmField
    val overrideScheduler = RxSchedulerOverrideRule()

}