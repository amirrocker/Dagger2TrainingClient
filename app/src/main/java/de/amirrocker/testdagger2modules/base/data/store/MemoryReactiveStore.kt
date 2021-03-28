package de.amirrocker.testdagger2modules.base.data.store

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import polanski.option.Option
import polanski.option.function.Func1
import javax.inject.Inject

class MemoryReactiveStore<Key, Value> @Inject constructor(
    val extractKeyFromModel: Func1<Value, Key>,
    val cache: Store.MemoryStore<Key, Value>
) : ReactiveStore<Key, Value> {


    val allSubject: Subject<Option<List<Value>>> by lazy {
        PublishSubject
            .create<Option<List<Value>>>()
            .toSerialized()
    }

    val subjectMap: HashMap<Key, Subject<Option<Value>>> by lazy {
        HashMap<Key, Subject<Option<Value>>>()
    }

    override fun storeSingular(model: Value) {
        val key = extractKeyFromModel.call(model)
        cache.putSingular(model)
        getOrCreateSubjectForKey(key)

        val allValues = cache.getAll().map { value: List<Value> -> Option.ofObj(value) }
            .blockingGet(Option.none())
        allSubject.onNext(allValues)
    }

    override fun storeAll(modelList: List<Value>) {
        cache.putAll(modelList)
        allSubject.onNext(Option.ofObj(modelList))
        publishInEachKey()
    }

    override fun replaceAll(modelList: List<Value>) {
        cache.clear()
        storeAll(modelList)
    }

    /**
     * Observable has a different inner workings than Flowable in RxJava2
     * TODO check to see whether it works with Observable
     */
    override fun getSingular(key: Key): Observable<Option<Value>> {
        return Observable.defer { getOrCreateSubjectForKey(key).startWith(getValue(key)) }
            .observeOn(Schedulers.computation())
    }

    override fun getAll(): Observable<Option<List<Value>>> {
        return Observable.defer { allSubject.startWith(getAllValues()) }
            .observeOn(Schedulers.computation())

    }

    /* private api */

    /* TODO make sure to wrap in Preconditions */
    fun getValue(key:Key): Option<Value>? =
        cache.getSingular(key).map { value -> Option.ofObj(value) }
            .blockingGet(Option.none())

    fun getAllValues():Option<List<Value>> =
        cache.getAll().map { value -> Option.ofObj(value) }
            .blockingGet(Option.none())


    fun getOrCreateSubjectForKey(key:Key): Subject<Option<Value>> {
        synchronized(subjectMap) {
            return Option.ofObj(subjectMap[key])
                .orDefault {
                    createAndStoreNewSubjectForKey(key)
                }
        }
    }

    private fun createAndStoreNewSubjectForKey(key: Key): Subject<Option<Value>> {
        val processor = PublishSubject
            .create<Option<Value>>()
            .toSerialized()
        synchronized(subjectMap) {
            subjectMap.put(key, processor)
        }
        return processor
    }

    /* Publishes the cached data in each independent stream only if it exists already */

    private fun publishInEachKey() {

        var keySet: Set<Key>
        synchronized(subjectMap) {
            keySet = HashSet(subjectMap.keys)
        }
        keySet.parallelStream()
            .map {
                val value = cache.getSingular(it).map { value:Value -> Option.ofObj(value) }.blockingGet(
                    Option.none()
                )
                publishInKey(it, value)
            }

    }

    private fun publishInKey(key:Key, model: Option<Value>) {
        val processor = subjectMap.get(key)
        Option.ofObj(processor).ifSome {
            it.onNext(model)
        }

    }
}


//class MemoryReactiveStore<Key, Value>(
//    val extractKeyFromModel: Function<Value, Key>,
//    val cache: Store.MemoryStore<Key, Value>
//) : ReactiveStore<Key, Value> {
//
//
//    override fun storeSingular(model: Value) {
//        TODO("Not yet implemented")
//    }
//
//    override fun storeAll(modelList: List<Value>) {
//        TODO("Not yet implemented")
//    }
//
//    override fun replaceAll(modelList: List<Value>) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getSingular(key: Key): Observable<Option<Value>> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getAll(): Observable<Option<List<Value>>> {
//        TODO("Not yet implemented")
//    }
//}