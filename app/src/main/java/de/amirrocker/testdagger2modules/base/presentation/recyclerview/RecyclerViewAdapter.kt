package de.amirrocker.testdagger2modules.base.presentation.recyclerview

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.amirrocker.testdagger2modules.home.data.trainingSession.*
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewEntityMapper
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewHolder
import io.reactivex.Single
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor(
    val onTrainingSessionItemClicked: TrainingSessionCardViewHolder.OnTrainingSessionItemClicked
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // the list of items to display
    private val modelItems = mutableListOf<TrainingSession>()


    /* TODO deal with null values! Having !! operator is not a viable solution */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder = TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory(parent.context).createViewHolder(parent)

        viewHolder.itemView.setOnClickListener { onTrainingSessionItemClicked(viewHolder.getAdapterPosition()) }

        return viewHolder
    }

    private fun onTrainingSessionItemClicked(adapterPosition: Int) {
        onTrainingSessionItemClicked.onTrainingSessionSelected(modelItems[adapterPosition])
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = modelItems[position]
        (holder as TrainingSessionCardViewHolder).bind(TrainingSessionCardViewEntityMapper().apply(item))
    }

    override fun getItemCount(): Int = modelItems.size

    fun update(items:List<TrainingSession>) {
//        androidPrecondition.assertUIThread()

        updateAllItems(items)

//        if(modelItems.isEmpty()) {
//            updateAllItems(items)
//        }else{
//            updateDiffItemsOnly(items)
//        }
    }

    fun updateAllItems(items:List<TrainingSession>) = Single.just(items)
        .doOnSuccess(this::updateItemsInModel)
        .subscribe { _, _ -> notifyDataSetChanged()}

    private fun updateItemsInModel(items: List<TrainingSession>) {
        (modelItems as ArrayList).clear()
        modelItems.addAll(items)
    }
}