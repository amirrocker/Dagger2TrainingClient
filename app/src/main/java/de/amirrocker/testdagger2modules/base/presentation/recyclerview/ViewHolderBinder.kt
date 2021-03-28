package de.amirrocker.testdagger2modules.base.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView

interface ViewHolderBinder {

    /**
     * populates the passed ViewHolder with the details of DisplayableItem.
     * @See DisplayableItem<T>
     */
    fun bind(viewHolder:RecyclerView.ViewHolder, item: DisplayableItem<Any>)
}