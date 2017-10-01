package krsoftware.mapotato.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import krsoftware.mapotato.BuildConfig
import krsoftware.mapotato.R
import krsoftware.mapotato.inflate
import krsoftware.mapotato.model.Place
import me.zhanghai.android.materialratingbar.MaterialRatingBar

/**
 * Created by silver_android on 30/09/17.
 */
class RestaurantsAdapter(private val restaurants: List<Place>): RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder = RestaurantViewHolder(parent.inflate(R.layout.item_restaurant))

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        with (restaurant) {
            val imageURL = "https://maps.googleapis.com/maps/api/place/photo?key=${BuildConfig.API_KEY}&photoreference=${photos?.get(0)?.photo_reference}&maxwidth=400"
            holder.bind(name, address, imageURL, rating)
        }
    }

    override fun getItemCount(): Int = restaurants.size

    inner class RestaurantViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        private val name: TextView = containerView.findViewById(R.id.name)
        private val address: TextView = containerView.findViewById(R.id.address)
        private val image: SimpleDraweeView = containerView.findViewById(R.id.image)
        private val ratingText: TextView = containerView.findViewById(R.id.rating_text)
        private val ratingBar: MaterialRatingBar = containerView.findViewById(R.id.rating)

        fun bind(name: String, address: String, imageURL: String?, rating: Float) {
            this.name.text = name
            this.address.text = address
            this.ratingBar.rating = rating
            this.ratingText.text = rating.toString()
            if (imageURL == null) {
                // add picture of potato
            } else {
                this.image.setImageURI(imageURL)
            }
        }
    }
}