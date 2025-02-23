package com.example.ecommerceapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemCartBinding
import com.example.ecommerceapp.domain.model.Product

class CartAdapter : ListAdapter<Product, CartAdapter.CartViewHolder>(ItemCallBack()) {

    var onItemDeleteClick: (product: Product) -> Unit = {}
    var onItemClick: (product: Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        Log.d("CartAdapter", "onCreateViewHolder: Creating ViewHolder for item")
        return CartViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        val product = getItem(position)
        Log.d("CartAdapter", "onBindViewHolder: Binding item at position $position - ${product.title}")
        holder.bind(product)
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Log.d("CartAdapter", "bind: Setting product ${product.title} with price ${product.price}")

            textProductName.text = product.title
            textProductPrice.text = product.price.toString()

            Glide.with(imageProduct)
                .load(product.image)
                .into(imageProduct)

            buttonRemove.setOnClickListener {
                Log.d("CartAdapter", "bind: Delete clicked for ${product.title}")
                onItemDeleteClick.invoke(product)
            }

            imageProduct.setOnClickListener {
                Log.d("CartAdapter", "bind: Product clicked - ${product.title}")
                onItemClick.invoke(product)
            }
        }
    }

    class ItemCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            val result = oldItem.id == newItem.id
            Log.d("CartAdapter", "areItemsTheSame: ${oldItem.id} == ${newItem.id} -> $result")
            return result
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            val result = oldItem == newItem
            Log.d("CartAdapter", "areContentsTheSame: ${oldItem.id} content comparison -> $result")
            return result
        }
    }
}