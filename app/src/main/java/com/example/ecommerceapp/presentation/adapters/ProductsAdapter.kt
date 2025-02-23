package com.example.ecommerceapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ItemLayoutVerticalPreviewBinding
import com.example.ecommerceapp.domain.model.Product

class ProductsAdapter() : ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(ProductCallBack()) {

    var onItemClick : (Product) -> Unit = {}
    var onAddToCartClick : (Product) -> Unit ={}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        Log.d("ProductsAdapter", "onCreateViewHolder: Creating ViewHolder for item at position $viewType")  // Log ViewHolder creation
        return ProductsViewHolder(ItemLayoutVerticalPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        Log.d("ProductsAdapter", "onBindViewHolder: Binding product at position $position")  // Log before binding the product
        holder.bind(getItem(position))
    }

    class ProductCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            Log.d("ProductsAdapter", "areItemsTheSame: Comparing items with IDs ${oldItem.id} and ${newItem.id}")  // Log when comparing items
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            Log.d("ProductsAdapter", "areContentsTheSame: Comparing content of products with IDs ${oldItem.id} and ${newItem.id}")  // Log when comparing item content
            return oldItem == newItem
        }
    }





    inner class ProductsViewHolder(
        private val binding: ItemLayoutVerticalPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            Log.d("ProductsAdapter", "bind: Binding product with ID ${product.id}")  // Log when binding the product

            binding.itemName.text = product.title
            binding.itemName.isSelected = true
            binding.itemPrice.text = product.price.toString()

            Glide.with(binding.root.context)
                .load(product.image)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemImage)


            Log.d("ProductsAdapter", "bind: Loading image for product ${product.title}")  // Log when loading the image

            binding.root.setOnClickListener {
                onItemClick.invoke(product)
            }


            binding.buttonCart.setOnClickListener {
                onAddToCartClick.invoke(product)
            }


        }


    }


}
