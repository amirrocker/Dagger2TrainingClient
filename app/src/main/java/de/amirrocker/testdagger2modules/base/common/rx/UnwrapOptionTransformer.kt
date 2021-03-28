package de.amirrocker.testdagger2modules.base.common.rx

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import polanski.option.Option
import polanski.option.OptionUnsafe

class UnwrapOptionTransformer<T> : ObservableTransformer<Option<T>, T> {

    override fun apply(upstream: Observable<Option<T>>): ObservableSource<T> {
        return upstream
                .filter { option: Option<T> -> option.isSome }
                .doOnEach {
                    println("it: ${it.value}")
                }
            .map {
                OptionUnsafe.getUnsafe(it)
            }
    }

    companion object {
        fun <T> create():UnwrapOptionTransformer<T> = UnwrapOptionTransformer()
    }


}