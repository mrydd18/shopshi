package com.example.ecommerceapp.data

import com.example.ecommerceapp.data.local.entity.ProductsDbo
import com.example.ecommerceapp.data.remote.dto.ProductDto
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.model.Rating


fun Product.toProductsDbo(): ProductsDbo? {
    return this.id?.let {
        this.title?.let { it1 ->
            this.image?.let { it2 ->
                ProductsDbo(
                    id = it,
                    image = it2,
                    title = it1,
                )
            }
        }
    }
}



fun ProductsDbo.toProduct(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = null,
        description = null,
        category = null,
        image = this.image,
        rating = null
    )
}

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = Rating(
            rate = rating?.rate,
        )
    )
}


