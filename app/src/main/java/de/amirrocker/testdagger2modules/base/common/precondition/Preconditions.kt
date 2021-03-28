package de.amirrocker.testdagger2modules.base.common.precondition

sealed class Preconditions {

    companion object {

        /* check for null */
        fun <T> get(reference:T?):T {  // =
            return checkNotNull(reference) as T
        }

        private fun <T> checkNotNull(reference:T):T {
            if( reference == null ) {
                throw NullPointerException()
            }
            return reference
        }
    }

}