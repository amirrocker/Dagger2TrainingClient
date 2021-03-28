package de.amirrocker.testdagger2modules.base.presentation.recyclerview

interface ItemComparator {

    fun areItemsTheSame(itemA:DisplayableItem<Any>, itemB:DisplayableItem<Any>): Boolean

    fun areContentsTheSame(itemA:DisplayableItem<Any>, itemB:DisplayableItem<Any>) : Boolean

}