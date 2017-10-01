package krsoftware.mapotato.model

/**
 * Created by silver_android on 30/09/17.
 */
class PlacesPhoto

data class Place(val photos: List<PhotoDTO>, val rating: Double, val name: String, val placeID: String, val address: String) {
    constructor(dto: PlaceDTO) : this(dto.photos, dto.rating, dto.name, dto.place_id, dto.vicinity)

    fun toDTO(): PlaceDTO = PlaceDTO(photos, rating, name, placeID, address)
}