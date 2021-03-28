package de.amirrocker.testdagger2modules.base.data

import java.util.*

/**
 * How can we model the super call ?
 *  super("The params: " + missingParam + " are missing in received object: " + rawObject);
 *  inside the constructor?
 */
class EssentialParamMissingException(
    val msg:String,
    val rawObject:Any
) : RuntimeException(msg) {
}