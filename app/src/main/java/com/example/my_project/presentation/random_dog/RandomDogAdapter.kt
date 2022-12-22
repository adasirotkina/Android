package com.example.my_project.presentation.random_dog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.my_project.entity.Dog
import com.example.my_project.databinding.RandomDogsItemsBinding
import com.example.my_project.presentation.common.setImageUrl

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

    fun ageToText(age: String): String {
        if (age.toInt() == 0) return "меньше года"
        else if (age.toInt() == 1) return "$age год"
        else if (age.toInt() in 2..4) return "$age года"
        else return "${age} лет"
    }

    fun genderToText(gender: String?): String {
        if (gender == "male") return "мальчик"
        else return "девочка"
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            randomDogsItemName.text = item.name
            randomDogsItemAge.text = ageToText(item.age.filter { it.isDigit() })
            randomDogPosters.setImageUrl(item.posterUrl)
            randomDogsItemGender.text = genderToText(item.gender)
            root.setOnClickListener { onDogClick(item) }
        }
    }

}