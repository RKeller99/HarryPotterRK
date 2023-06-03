package com.rkeller.harrypotterrk.model

import com.google.gson.annotations.SerializedName

data class DetallesPersonaje(
    @SerializedName("id") // En caso de que no lo pueda leer Kotlin
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("actor")
    var actor: String?,

    @SerializedName("species")
    var species: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("house")
    var house: String?,
    @SerializedName("ancestry")
    var ancestry: String?,
    @SerializedName("patronus")
    var patronus: String?,
    @SerializedName("alive")
    var alive: String?,

    @SerializedName("hogwartsStudent")
    var hogwartsStudent: String?,
    @SerializedName("hogwartsStaff")
    var hogwartsStaff: String?,
)
