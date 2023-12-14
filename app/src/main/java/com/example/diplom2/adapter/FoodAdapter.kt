package com.example.diplom2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom2.R
import com.example.diplom2.model.ExistFoodModel
import com.example.diplom2.model.FoodModel
import com.example.diplom2.screens.food.AllFood
import kotlinx.android.synthetic.main.card_layout.view.*
import kotlinx.android.synthetic.main.fragment_add_food.view.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.fragment_physical.view.*


class FoodAdapter: RecyclerView.Adapter<FoodAdapter.FoodViewHolder> (){
    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view)

    var listFood = emptyList<FoodModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.itemView.item_title.text = listFood[position].title
        holder.itemView.item_kalorii.text = listFood[position].kalorazh.toString()
        holder.itemView.item_belki.text = listFood[position].belki.toString()
        holder.itemView.item_jiri.text = listFood[position].jiri.toString()
        holder.itemView.item_uglevodi.text = listFood[position].uglevodi.toString()

    }

    override fun getItemCount(): Int {

        return listFood.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodModel>){
        listFood = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: FoodViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
        AllFood.clickFood(listFood[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: FoodViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}