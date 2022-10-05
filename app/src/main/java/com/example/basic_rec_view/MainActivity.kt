package com.example.basic_rec_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basic_rec_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var foodItemList: MutableList<FoodItem>
    private lateinit var adapter: FoodItemAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        for (i in 1..15) {
            val name = "Food Item $i"
            val price = (100 * i).toFloat()

            val foodItem = FoodItem(name = name, price = price)

            foodItemList.add(foodItem)
        }

        adapter = FoodItemAdapter(this, foodItemList)
        binding.foodItemsRV.adapter = adapter
        binding.foodItemsRV.layoutManager = LinearLayoutManager(this)

        binding.foodItemsRV.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL

            )
        )




    }
}