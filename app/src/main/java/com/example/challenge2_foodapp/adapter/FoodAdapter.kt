package com.example.challenge2_foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.utils.toCurrencyFormat
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class FoodAdapter(private val food: List<FoodEntity>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FoodEntity)
    }

    var isListView = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        private const val TYPE_LIST = 1
        private const val TYPE_GRID = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LIST -> {
                val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
                ListViewHolder(view)
            }

            TYPE_GRID -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.food_grid_item, parent, false)
                GridViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val food = food[position]

        when (holder.itemViewType) {
            TYPE_LIST -> {
                val listViewHolder = holder as ListViewHolder
                listViewHolder.foodTitle.text = food.name
                listViewHolder.foodPrice.text = food.price.toDouble().toCurrencyFormat()
                listViewHolder.foodImage.setImageResource(food.image)
                holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(food) }
            }

            TYPE_GRID -> {
                val gridViewHolder = holder as GridViewHolder
                gridViewHolder.foodTitle.text = food.name
                gridViewHolder.foodPrice.text = food.price.toDouble().toCurrencyFormat()
                gridViewHolder.foodImage.setImageResource(food.image)
                holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(food) }
            }
        }
    }

    override fun getItemCount(): Int {
        return food.size
    }

    override fun getItemViewType(position: Int): Int {
        // Return tipe item berdasarkan posisi (misalnya, pilih TYPE_LIST atau TYPE_GRID)
        return if (isListView) {
            TYPE_LIST
        } else {
            TYPE_GRID
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTitle: MaterialTextView = itemView.findViewById(R.id.tv_makanan)
        val foodPrice: MaterialTextView = itemView.findViewById(R.id.tv_harga)
        val foodImage: ShapeableImageView = itemView.findViewById(R.id.iv_makanan)
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTitle: MaterialTextView = itemView.findViewById(R.id.tv_nama_grid)
        val foodPrice: MaterialTextView = itemView.findViewById(R.id.tv_harga_grid)
        val foodImage: ShapeableImageView = itemView.findViewById(R.id.iv_makanan_grid)
    }
}