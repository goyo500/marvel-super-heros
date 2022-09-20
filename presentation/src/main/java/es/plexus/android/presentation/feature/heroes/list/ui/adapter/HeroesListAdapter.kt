package es.plexus.android.presentation.feature.heroes.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.plexus.android.domain.model.SuperHero
import es.plexus.android.presentation.R
import es.plexus.android.presentation.databinding.ItemHeroBinding

class HeroesListAdapter(
    private val data: List<SuperHero>,
    private val onSelectHero: (SuperHero) -> Unit
) : RecyclerView.Adapter<HeroesListAdapter.HeroViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), onSelectHero
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class HeroViewHolder(val view: ItemHeroBinding, private val onSelectHero: (SuperHero) -> Unit) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(hero: SuperHero) {
            with(view) {
                tvHeroNameValue.text = hero.name.trim()
                tvHeroDescriptionValue.text = hero.description.trim()

                Glide.with(root.context)
                    .load(hero.picture)
                    .placeholder(R.drawable.hydra_place_holder)
                    .error(R.drawable.hydra_place_holder)
                    .circleCrop()
                    .into(ivHeroPicture)

                root.setOnClickListener {
                    onSelectHero(hero)
                }
            }
        }
    }
}