package com.example.basic_rec_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_rec_view.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), DeleteList {

    private lateinit var foodItemList: MutableList<FoodItem>
    private lateinit var adapter: FoodItemAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        foodItemList = mutableListOf<FoodItem>()

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

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val deleteCourse: FoodItem = foodItemList.get(viewHolder.adapterPosition)

                val position = viewHolder.adapterPosition

                foodItemList.removeAt(viewHolder.adapterPosition)

                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                Snackbar.make(
                    binding.foodItemsRV,
                    "Deleted" + deleteCourse.name,
                    Snackbar.LENGTH_LONG
                )
                    .setAction("Undo", View.OnClickListener {
                        foodItemList.add(position,deleteCourse)

                        adapter.notifyItemInserted(position)
                    }).show()

            }
        }).attachToRecyclerView(binding.foodItemsRV)



    }

    override fun onDelete(position: Int) {

        foodItemList.removeAt(position)


        adapter.notifyItemRemoved(position)
    }
}