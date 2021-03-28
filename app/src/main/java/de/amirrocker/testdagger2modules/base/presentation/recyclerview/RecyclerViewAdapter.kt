package de.amirrocker.testdagger2modules.base.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.amirrocker.testdagger2modules.base.common.precondition.AndroidPrecondition
import de.amirrocker.testdagger2modules.home.data.trainingSession.*
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewEntity
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewEntityMapper
import de.amirrocker.testdagger2modules.home.presentation.TrainingSessionCardViewHolder
import io.reactivex.Single
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor(
//    private val factoryList:List<TrainingSession>,
//    private val comparator: ItemComparator,
//    private val factoryMap: Map<Int, ViewHolderFactory>,
//    private val binderMap: Map<Int, ViewHolderBinder>,
//    private val androidPrecondition: AndroidPrecondition
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private class DiffCallback(
//        val oldItems:List<DisplayableItem<Any>>,
//        val newItems:List<DisplayableItem<Any>>,
//        val comparator: ItemComparator
//    ) : DiffUtil.Callback() {
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
//            comparator.areItemsTheSame(oldItems[oldItemPosition], newItems.get(newItemPosition))
//
//        override fun getOldListSize(): Int = oldItems.size
//
//
//        override fun getNewListSize(): Int = newItems.size
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
//            comparator.areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
//    }

    // the list of items to display
    private val modelItems = mutableListOf<TrainingSession>()

//    private fun createMockList(): List<TrainingSession> =
//        listOf(
//            TrainingSession("sessionId1", "version1", "createdAt1",
//                listOf(
//                    Training("id1", "env1", "file1",
//                        ModelParameter("model1", "optimizer1", "loss1"),
//                        Log("log1"),
//                        HyperParameter("epochs1", "learningRate1")
//                        ),
//                    Training("id2", "env2", "file2",
//                        ModelParameter("model2", "optimizer2", "loss2"),
//                        Log("log2"),
//                        HyperParameter("epochs2", "learningRate2")
//                    )
//                )
//            ),
//            TrainingSession("sessionId2", "version2", "createdAt2",
//                listOf(
//                    Training("id1", "env1", "file1",
//                        ModelParameter("model1", "optimizer1", "loss1"),
//                        Log("log1"),
//                        HyperParameter("epochs1", "learningRate1")
//                        ),
//                    Training("id2", "env2", "file2",
//                        ModelParameter("model2", "optimizer2", "loss2"),
//                        Log("log2"),
//                        HyperParameter("epochs2", "learningRate2")
//                    )
//                )
//            ),
//        )




    /* TODO deal with null values! Having !! operator is not a viable solution */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory(parent.context).createViewHolder(parent)

    }
//        TrainingSessionCardViewHolder.TrainingSessionCardViewHolderFactory(parent.context)
//            .createViewHolder(parent)

//        factoryMap[viewType].let {
//            it!!.createViewHolder(parent)
//        }
//        viewType.let {
//            modelItems
//        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = modelItems[position]
        (holder as TrainingSessionCardViewHolder).bind(TrainingSessionCardViewEntityMapper().apply(item))


//        TrainingSessionCardViewHolder.TrainingSessionCardViewHolderBinder()
//            .bind(holder, item)

//        binderMap[item._type]?.let {
//            it.bind(holder, item)
//        }
    }

    override fun getItemCount(): Int = modelItems.size



//    fun calculateDiff(newItems:List<DisplayableItem<Any>>):DiffUtil.DiffResult =
//        DiffUtil.calculateDiff(DiffCallback(modelItems, newItems, comparator ))

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

//    /**
//     * Do not use for first adapter update. It is much slower than {@link RecyclerView.Adapter#notifyDataSetChanged}
//     *
//     */
//    fun updateDiffItemsOnly(items:List<DisplayableItem<Any>>) {
//            Single.fromCallable {
//                calculateDiff(items)
//            }
//            .doOnSuccess { updateItemsInModel(items) }
//            .subscribe(::updateAdapterWithDiffResult)
//    }
//
//    private fun updateItemsInModel(items: List<DisplayableItem<Any>>) {


    private fun updateItemsInModel(items: List<TrainingSession>) {
        (modelItems as ArrayList).clear()
        modelItems.addAll(items)
    }

//
//    fun updateAdapterWithDiffResult(result:DiffUtil.DiffResult) =
//        result.dispatchUpdatesTo(this)


}