package org.xavrs.hexagonal.springboot3.infrastructure.adapter

import org.springframework.stereotype.Component
import org.xavrs.hexagonal.springboot3.domain.model.ColorCode
import org.xavrs.hexagonal.springboot3.domain.model.Item
import org.xavrs.hexagonal.springboot3.domain.model.Reference
import org.xavrs.hexagonal.springboot3.domain.port.ItemDBPort
import org.xavrs.hexagonal.springboot3.infrastructure.persistence.repository.ItemOracleRepository
import org.xavrs.hexagonal.springboot3.infrastructure.persistence.repository.ItemRedisRepository

@Component
class ItemDBPortAdapter(
    private val itemRedisRepository: ItemRedisRepository
): ItemDBPort {

   override fun getItem(reference: Reference, colorCode: ColorCode): Item? {
           val item = itemRedisRepository.findById("${reference.value}-${colorCode.value}")
           return if(item.isPresent){
               item.get().toDomainModel()
           }else{
               null
           }
       }
}