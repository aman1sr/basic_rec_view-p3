package com.example.basic_rec_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_rec_view.databinding.FoodItemLayoutBinding

class FoodItemAdapter(private val context: Context, private val foodItemList: MutableList<FoodItem>)
    : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {


/* This method is for creating viewHolder */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {

    val binding = FoodItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)  // directly connecting layout via ViewBiding
    return FoodItemViewHolder(binding)
    }

/* This method is for binding our data to views */
    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
       val foodItem = foodItemList[position]
    holder.bind(foodItem)
    }

/* how many items we have to display */
    override fun getItemCount(): Int {
       return foodItemList.size
    }


    class FoodItemViewHolder(foodItemLayoutBinding: FoodItemLayoutBinding):
        RecyclerView.ViewHolder(foodItemLayoutBinding.root){

        private val binding = foodItemLayoutBinding

        fun bind(foodItem: FoodItem) {
            binding.foodItemNameTV.text = foodItem.name
            binding.foodItemPriceTV.text = "Rs. ${foodItem.price}"

        }

        }



}