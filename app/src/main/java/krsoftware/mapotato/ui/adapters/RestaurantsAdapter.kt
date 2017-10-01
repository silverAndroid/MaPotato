package krsoftware.mapotato

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import krsoftware.mapotato.model.Place

/**
 * Created by silver_android on 30/09/17.
 */
class RestaurantsAdapter(private val restaurants: List<Place>): RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder = RestaurantViewHolder(parent.inflate(R.layout.item_restaurant))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        with (restaurant) {
            holder.bind(name, address)
        }
    }

    override fun getItemCount(): Int = restaurants.size

    inner class RestaurantViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val name: TextView = containerView.findViewById(R.id.name)
        private val address: TextView = containerView.findViewById(R.id.address)

        fun bind(name: String, address: String) {
            this.name.text = name
            this.address.text = address
        }
    }
}