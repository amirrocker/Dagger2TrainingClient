package de.amirrocker.testdagger2modules.base.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import polanski.option.Option

interface ReactiveInteractor {

    /**
     * send changes to the data layer.
     * getSingle
     */
    interface SendInteractor<Params, Result> : ReactiveInteractor {
        fun getSingle(params: Option<Params>):Single<Result>
    }

    interface RetrieveInteractor<Params, Object> : ReactiveInteractor {
        fun getBehaviorStream(params:Option<Params>):Observable<Object>
    }

    interface RequestInteractor<Params, Result> : ReactiveInteractor {
        fun getSingle(params: Option<Params>):Single<Result>
    }

    /**
     *
     */
    interface DeleteInteractor<Params, Result> : ReactiveInteractor {
        fun delete(params: Option<Params>):Single<Result>
    }

    interface RefreshInteractor<Params> : ReactiveInteractor {
        fun refresh(params: Option<Params>):Completable
    }


}