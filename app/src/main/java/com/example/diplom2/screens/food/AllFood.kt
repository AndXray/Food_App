package com.example.diplom2.screens.food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom2.APP
import com.example.diplom2.R
import com.example.diplom2.adapter.FoodAdapter
import com.example.diplom2.databinding.FragmentAllFoodBinding
import com.example.diplom2.model.FoodModel
import kotlinx.android.synthetic.main.fragment_all_food.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class AllFood : Fragment() {

    lateinit var binding: FragmentAllFoodBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FoodAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllFoodBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        button()
    }

    private fun init() {

        val viewModel = ViewModelProvider(this).get(AllFoodViewModel::class.java)
        viewModel.initDataBase()
        recyclerView = binding.rvFood
        adapter = FoodAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllFoods().observe(viewLifecycleOwner) { listFoods ->
            adapter.setList(listFoods.asReversed())
        }
//        binding.btnAdd.setOnClickListener{
//            APP.navController.navigate(R.id.action_allFood_to_addFood)
//        }
        }
    private fun button(){
        btn_add.setOnClickListener{
            onBtnAddClicked()
        }
        btn_new.setOnClickListener{
            onBtnNewClicked()
        }
        btn_list.setOnClickListener{
            onBtnExistClicked()
        }
    }

    private fun onBtnAddClicked() {
        setClicked()
    }
    private fun setClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun onBtnNewClicked(){
        APP.navController.navigate(R.id.action_allFood_to_addFood)
    }
    private fun onBtnExistClicked(){
        APP.navController.navigate(R.id.action_allFood_to_existFood)
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            btn_new.visibility = View.VISIBLE
            btn_list.visibility = View.VISIBLE
        } else{
            btn_new.visibility = View.INVISIBLE
            btn_list.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked) {
            btn_new.startAnimation(fromBtn)
            btn_list.startAnimation(fromBtn)
            btn_add.startAnimation(rotateOpen)
        } else{
                btn_new.startAnimation(toBtn)
                btn_list.startAnimation(toBtn)
                btn_add.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean){
        if(!clicked){
            btn_new.isClickable = true
            btn_list.isClickable = true
        } else{
            btn_new.isClickable = false
            btn_list.isClickable = false
        }
    }

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_open_animation) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_close_animation) }
    private val fromBtn: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.from_btn_animation) }
    private val toBtn: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.to_btn_animation) }
    private var clicked = false

    companion object{
        fun clickFood(foodModel: FoodModel){
           val bundle = Bundle()
            bundle.putSerializable("food",foodModel)
            APP.navController.navigate(R.id.action_allFood_to_detailsFragment, bundle)
        }
    }

}