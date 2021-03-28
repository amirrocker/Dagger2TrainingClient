package de.amirrocker.testdagger2modules.base.data.cache

import de.amirrocker.testdagger2modules.base.common.providers.TimestampProvider
import de.amirrocker.testdagger2modules.base.common.utils.ListUtils
import de.amirrocker.testdagger2modules.base.data.store.Store
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject


open class Cache<Key,Value> @Inject constructor(
    val extractKeyFromModel: Function<Value, Key>,
    val timestampProvider: TimestampProvider,
    val timeoutMs:Long
) : Store.MemoryStore<Key, Value> {

    lateinit var itemLifeSpanMs : Option<Long>

    private val cache: Map<Key, CacheEntry<Value>> = ConcurrentHashMap<Key, CacheEntry<Value>>()


    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    /**
     * store a singular value inside the cache
     */
    override fun putSingular(value: Value) {
        val disposable = Single.fromCallable(Callable {
            extractKeyFromModel.apply(value)
        })
            .subscribeOn(Schedulers.computation())
            .subscribe(Consumer {
                (cache as ConcurrentHashMap).put(key = it,
                    value = CacheEntryFactory<Key, Value>(timestampProvider).createCacheEntry(value))
            })
        compositeDisposable.add(disposable)
    }

    /**
     * put all values inside the list into the cache
     *
     */
    override fun putAll(list: List<Value>) {
        val disposable = Flowable.fromIterable<Value>(list)
            .toMap(
                extractKeyFromModel,
                Function { value: Value ->
                    CacheEntryFactory<Key, Value>(timestampProvider).createCacheEntry(value)
                }
            )
            .subscribeOn(Schedulers.computation())
            .subscribe(Consumer { map: Map<Key, CacheEntry<Value>> ->
                (cache as ConcurrentHashMap).putAll(map)
            })
        compositeDisposable.add(disposable)
    }

    override fun clear() {
        (cache as ConcurrentHashMap).clear()
    }

    override fun getSingular(key: Key): Maybe<Value> =
        Maybe.fromCallable(Callable { cache.containsKey(key) })
            .filter {
                isPresent: Boolean -> isPresent
            }
            .map {
                cache[key] ?: error("Cache[$key] throws error - is value: ${cache[key]}")
            }
            .filter(this::notExpired)
            .map {
                it.cachedObject
            }


    override fun getAll(): Maybe<List<Value>> {
        return Flowable.fromIterable<CacheEntry<Value>>(cache.values)
            .filter(Predicate { cacheEntry:CacheEntry<Value> ->
                notExpired(
                    cacheEntry
                )
            })
            .map { it.cachedObject }
            .toList()
//            .filter(ListUtils::isNotEmpty)
            .filter { t: MutableList<Value> -> ListUtils().isNotEmpty(t) }
            .subscribeOn(Schedulers.computation())
            as Maybe<List<Value>>
    }

    private fun notExpired(cacheEntry: CacheEntry<Value>) =
        itemLifeSpanMs.match(
            { lifespan -> cacheEntry.creationTimestamp+lifespan > timestampProvider.currentTimeMillis()},
            { true })

}