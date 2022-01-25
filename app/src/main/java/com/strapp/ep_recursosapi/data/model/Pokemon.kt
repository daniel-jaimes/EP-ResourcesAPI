package com.strapp.ep_recursosapi.data.model

class Pokemon(
    val id: Int,
    val name: String,
    val height : Int,
    val is_default: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Any>,
    val forms: List<Any>,
    val game_indices: List<Any>,
    val held_items: List<Any>,
    val location_area_encounters: String,
    val moves: List<Any>,
    val past_types: List<Any>,
    val sprites: Any,
    val species: Any,
    val stats: List<Any>,
    val types: List<Any>
)
{

}