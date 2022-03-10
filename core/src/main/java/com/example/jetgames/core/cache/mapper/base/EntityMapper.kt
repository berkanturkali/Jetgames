package com.example.jetgames.core.cache.mapper.base

interface EntityMapper<E, T> {

    fun mapFromEntity(entity: E): T

    fun mapToEntity(type: T): E

    fun mapTypeList(entities: List<E>): List<T> {
        return entities.mapTo(mutableListOf(), ::mapFromEntity)
    }
}