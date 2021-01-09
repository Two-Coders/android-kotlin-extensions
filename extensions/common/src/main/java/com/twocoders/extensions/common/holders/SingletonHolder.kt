package com.twocoders.extensions.common.holders

open class SingletonHolder<out T, in A>(creator: (A) -> T) {

    @Volatile
    private var instance: T? = null
    private var creator: ((A) -> T)? = creator

    fun getInstance(arg: A): T {

        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}