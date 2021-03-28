package de.amirrocker.testdagger2modules.base.presentation.recyclerview

class DisplayableItem<T>(
    var _type: Int = 0,
    var _model : T
) {

    companion object {
        fun <T> toDisplayableItem(model:T, type:Int) =
            DisplayableItem(type, model)
    }

    fun type(type:Int):DisplayableItem<T> {
        _type = type
        return this
    }

    fun model(model:T):DisplayableItem<T> {
        _model = model
        return this
    }
}
