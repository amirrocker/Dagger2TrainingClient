package de.amirrocker.testdagger2modules.base.domain.base

import java.util.*



abstract class Event<T> {

    private val id: UUID = UUID.randomUUID()
    private val version = 1

    abstract fun applyOn(aggregate: T)

}