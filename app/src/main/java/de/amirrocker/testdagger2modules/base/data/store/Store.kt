package de.amirrocker.testdagger2modules.base.data.store

import io.reactivex.Maybe

/**
 * Interface for any type of store. Do not
 * implement directly, instead use a
 * more descriptive subclass such as
 * MemoryStore or DiskStore
 */
interface Store<Key, Value> {

    fun putSingular(value:Value)

    fun putAll(list:List<Value>)

    fun clear()

    fun getSingular(key:Key):Maybe<Value>

    fun getAll():Maybe<List<Value>>

    /**
     * More descriptive interfaces for memory based stores
     */
    interface MemoryStore<Key,Value> : Store<Key, Value>

    interface DiskStore<Key,Value> : Store<Key, Value>
}