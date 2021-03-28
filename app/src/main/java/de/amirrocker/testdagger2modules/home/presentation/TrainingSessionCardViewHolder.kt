package de.amirrocker.testdagger2modules.home.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.aachartmodel.aainfographics.aachartcreator.*
import de.amirrocker.testdagger2modules.R
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.DisplayableItem
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderBinder
import de.amirrocker.testdagger2modules.base.presentation.recyclerview.ViewHolderFactory
import javax.inject.Inject

class TrainingSessionCardViewHolder @Inject constructor(
    val itemView:View
) : RecyclerView.ViewHolder(itemView) {

//    TODO replace with Binding asap
    var title: TextView
    var date: TextView
    var user: TextView
    var description: TextView
    var version: TextView
    var chartView: AAChartView

    init {
        title = itemView.findViewById(R.id.tv_item_training_session_title)
        date = itemView.findViewById(R.id.tv_item_training_date)
        user = itemView.findViewById(R.id.tv_item_training_environment)
        description = itemView.findViewById(R.id.tv_item_training_session_description)
        version = itemView.findViewById(R.id.tv_item_training_session_version)
        chartView = itemView.findViewById(R.id.chv_trainings_plot)
    }

    private fun configureChartView() {
        val chartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .title("Line Chart Trainings")
            .subtitle("A Subtitle text")
//            .backgroundColor("#336699")
            .markerSymbol(AAChartSymbolType.Circle)
            .markerSymbolStyle(AAChartSymbolStyleType.InnerBlank)
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("Training One: ")
                    .data(arrayOf(
                        1.25678, 1.35678, 2.15678, 1.85678, 2.12658, 0.92678, 1.65678, 2.35678, 2.85678, 0.55678
                    )),
                AASeriesElement()
                    .name("Training Two: ")
                    .data(arrayOf(
                        3.25678, 5.45678, 4.65678, 3.05678, 1.52658, 3.92678, 4.65678, 2.85678, 4.15678, 2.95678
                    ))
            ))
        val aaChartOptions = AAOptionsConstructor.configureChartOptions(chartModel)

//        chartView.aa_drawChartWithChartModel(chartModel)
        chartView.aa_drawChartWithChartOptions(chartOptions = aaChartOptions)
    }

    fun bind(viewEntity: TrainingSessionCardViewEntity) {
        title.setText(viewEntity._title)
        date.setText(viewEntity._createdAt)
        user.setText(viewEntity._user)
        description.setText(viewEntity._description)
//        TODO I feel there is a better to do this ??? Maybe once we Binding we get rid of this trash?
        version.setText(String.format(itemView.context.getString(R.string.item_training_session_version_text_template), viewEntity._version))

        configureChartView()

    }

    class TrainingSessionCardViewHolderFactory @Inject constructor(
        override val context:Context
    ) : ViewHolderFactory(context) {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            return TrainingSessionCardViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_list_item_training_session, parent, false)
            )
        }
    }

    class TrainingSessionCardViewHolderBinder @Inject constructor() : ViewHolderBinder {

        override fun bind(viewHolder: RecyclerView.ViewHolder, item: DisplayableItem<Any>) {
            val trainingSessionCardViewHolder = TrainingSessionCardViewHolder::class.java.cast(viewHolder)
            val itemEntity = TrainingSessionCardViewEntity::class.java.cast(item._model)
            trainingSessionCardViewHolder.bind(itemEntity)
        }
    }



}