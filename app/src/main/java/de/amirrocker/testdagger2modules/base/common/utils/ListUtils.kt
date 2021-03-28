package de.amirrocker.testdagger2modules.base.common.utils

class ListUtils {

    /**
     * returns a union of two lists
     */
    fun <T> union(list1:List<T>, list2:List<T>):List<T> =
       object : ArrayList<T>() {
           init {
               addAll(list1)
               addAll(list2)
           }
       }


    fun <T> isNotEmpty(list: List<T>):Boolean =
        list.isNotEmpty()

}