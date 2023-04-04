package org.xavrs.hexagonal.springboot3.infrastructure.persistence.model

import org.springframework.data.redis.core.RedisHash
import org.xavrs.hexagonal.springboot3.domain.model.Item

@RedisHash("Item")
data class RedisItemModel(
    val reference: String,
    val color: String,
    val name: String,
    val id: String = "$reference-$color"
): DBModel<Item>{
    override fun toDomainModel(): Item {
        return Item.Builder(reference = reference, colorCode = color)
            .withName(name)
            .build()
    }
}