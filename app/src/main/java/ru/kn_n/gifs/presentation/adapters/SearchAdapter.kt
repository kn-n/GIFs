package ru.kn_n.gifs.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.gifs.databinding.ItemGifBinding
import ru.kn_n.gifs.domain.entities.DataEntity
import ru.kn_n.gifs.utils.loadImage

class SearchAdapter(
    private val onItemClick: (id: String) -> Unit
) : PagingDataAdapter<DataEntity, SearchAdapter.ViewHolder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGifBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataEntity?) {
            data?.let {
                loadImage(
                    view = binding.root,
                    url = data.url,
                    place = binding.gif,
                    error = binding.error,
                    loading = binding.loading
                )

                binding.gif.setOnClickListener {
                    onItemClick(data.id)
                }
            }
        }
    }

    object Comparator : DiffUtil.ItemCallback<DataEntity>() {
        override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity) = oldItem == newItem
    }
}