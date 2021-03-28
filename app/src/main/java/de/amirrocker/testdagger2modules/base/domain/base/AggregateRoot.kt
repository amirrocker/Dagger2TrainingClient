package de.amirrocker.testdagger2modules.base.data

import de.amirrocker.testdagger2modules.base.domain.base.Event
import java.util.*
import kotlin.collections.ArrayList


interface Aggregate {
    fun applyChange(event: Event<Aggregate>)
}

open abstract class AggregateRoot(
    private val id: UUID
) : Aggregate {

    private var version = -1

    private val changes: ArrayList<Event<*>> = valueOf()

    fun getChanges(): List<Event<*>> {
        return changes
    }

    protected fun loadsFromHistory(history: List<Event<Aggregate>>) {
        history.forEach { event: Event<Aggregate> ->
            event.applyOn(this)
            changes.add(event)
            version += 1
        }
    }

    override fun applyChange(event: Event<Aggregate>) {
        event.applyOn(this)
        changes.add(event)
    }


    /* TODO make something better up ... */
    private fun valueOf() = ArrayList<Event<*>>()

    fun getId(): UUID {
        return id
    }

//    fun setId(id: UUID) {
//        this.id = id
//    }

    fun getVersion(): Int {
        return version
    }

    fun setVersion(version: Int) {
        this.version = version
    }

}
