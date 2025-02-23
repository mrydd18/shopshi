package com.example.ecommerceapp.data

import com.example.ecommerceapp.data.local.entity.ProductsDbo
import com.example.ecommerceapp.domain.model.Product


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

