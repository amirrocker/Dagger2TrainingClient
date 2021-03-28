package de.amirrocker.testdagger2modules.home.presentation

import de.amirrocker.testdagger2modules.base.presentation.recyclerview.DisplayableItem
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ItemComparator

/**
 * TODO find out whether this is correctly implemented ! I have a feeling that it is not ;:)
 * referenz equality == ?
 * content equality equals() ?
 */
class MainViewFragmentItemComparator : ItemComparator {

    override fun areItemsTheSame(
        itemA: DisplayableItem<Any>,
        itemB: DisplayableItem<Any>
    ): Boolean = itemA == itemB

    override fun areContentsTheSame(
        itemA: DisplayableItem<Any>,
        itemB: DisplayableItem<Any>
    ): Boolean = itemA.equals(itemB)
}