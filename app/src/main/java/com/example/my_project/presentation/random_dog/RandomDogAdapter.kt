package com.example.my_project.presentation.random_dog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.my_project.Dog
import com.example.my_project.databinding.ActivityDogDetailBinding
import com.example.my_project.databinding.RandomDogsItemsBinding

class RandomDogAdapter(
    private val onDogClick: (Dog) -> Unit,
): ListAdapter<Dog, RandomDogAdapter.Holder>(
    object : DiffUtil.ItemCallback<Dog>(){
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }

    }
) {


    fun setData(dogs: List<Dog>) {
       submitList(dogs)
    }

    class Holder(val binding: RandomDogsItemsBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RandomDogsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            randomDogsItemName.text = item.name
            randomDogsItemAge.text = item.age.toString()
            root.setOnClickListener { onDogClick(item) }
        }
    }

}