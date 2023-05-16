package com.example.classdemo3.ui.adapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.classdemo3.R
import com.example.classdemo3.databinding.HarrypotterCardViewBinding
import com.example.classdemo3.model_.HarryPotterCharactersItem
class HarryPotterAdapter (
    private val onItemClick: (harryPotter: HarryPotterCharactersItem, adapterPosition: Int) -> Unit
            ) :
    RecyclerView.Adapter<HarryPotterAdapter.HarryPotterViewHolder>(){

    init{
        setHasStableIds(true)
    }
        private val harryPotterCharacters = mutableListOf<HarryPotterCharactersItem>()
    @SuppressLint("NotifyDataSetChanges")
    fun refreshData(newCharacters: List<HarryPotterCharactersItem>){
        harryPotterCharacters.clear()
        harryPotterCharacters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarryPotterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HarrypotterCardViewBinding.inflate(layoutInflater,parent,false)
        return HarryPotterViewHolder(binding) { position ->
            onItemClick(harryPotterCharacters[position], position)
        }
    }
    override fun getItemCount() = harryPotterCharacters.size

    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(holder: HarryPotterViewHolder, position: Int) {
        val harryPotter = harryPotterCharacters[position]
        holder.bind(harryPotter)

    }
        inner class HarryPotterViewHolder(
            private val binding: HarrypotterCardViewBinding,
            private val onItemClick: (adapterPosition: Int) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {

            init {
                itemView.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }
            fun bind(harryPotter: HarryPotterCharactersItem){
                Glide
                    .with(binding.root)
                    .load(harryPotter.image)
                    .error(R.drawable.baseline_error_24)
                    .into(binding.harryPotterImage)
                binding.harryPotterName.text = harryPotter.name
                binding.harryPotterSpecies.text = harryPotter.species
                binding.harryPotterHouse.text = harryPotter.house
            }
        }
}