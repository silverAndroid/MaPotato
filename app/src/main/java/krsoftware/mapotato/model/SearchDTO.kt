package krsoftware.mapotato.model

/**
 * Created by silver_android on 30/09/17.
 */
data class SearchResults(val results: List<PlaceDTO>, val status: String, val error_message: String?, val next_page_token: String?)

data class PlaceDTO(val photos: List<PhotoDTO>?, val rating: Double, val name: String, val place_id: String, val vicinity: String) {
    constructor(place: Place) : this(place.photos, place.rating, place.name, place.placeID, place.address)

    fun toModel(): Place = Place(photos, rating, name, place_id, vicinity)
}

data class PhotoDTO(val photo_reference: String, val height: Int, val width: Int)