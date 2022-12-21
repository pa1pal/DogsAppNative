package `in`.pawan.dogsapp.ui.main.list

import `in`.pawan.dogsapp.databinding.ItemBreedBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView

class BreedAdapter(
    private val onBreedClickListener: onBreedClick
) : ListAdapter<String, BreedViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        return BreedViewHolder(
            ItemBreedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        getItem(position)?.let { breedName -> holder.bind(breedName, onBreedClickListener) }
    }

    object Comparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return String == String
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class BreedViewHolder(private val binding: ItemBreedBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(breedName: String, onBreedClickListener: onBreedClick) {
        binding.breedTv.text = breedName
        binding.breedLayout.setOnClickListener {
            onBreedClickListener.invoke(breedName)
        }
    }
}

typealias onBreedClick = (breedName: String) -> Unit