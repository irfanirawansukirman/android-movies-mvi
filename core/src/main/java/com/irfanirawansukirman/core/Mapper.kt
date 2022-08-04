package com.irfanirawansukirman.core

interface Mapper<I, O> {

    fun before(previous: I?): O

    fun after(next: O?): I

    fun fromList(list: List<I>?): List<O> {
        return list?.mapNotNull { before(it) } ?: listOf()
    }

    fun toList(list: List<O>?): List<I> {
        return list?.mapNotNull { after(it) } ?: listOf()
    }
}