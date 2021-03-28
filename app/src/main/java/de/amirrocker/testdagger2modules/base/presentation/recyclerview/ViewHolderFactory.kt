package de.amirrocker.testdagger2modules.base.presentation.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 * Instantiate a viewHolder based on its type.
 * See DisplayableItem
 */
abstract class ViewHolderFactory(
    open val context:Context
) {
    abstract fun createViewHolder(parent:ViewGroup): RecyclerView.ViewHolder
}