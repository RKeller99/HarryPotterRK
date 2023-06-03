package com.rkeller.harrypotterrk.model

import com.google.gson.annotations.SerializedName

data class Personaje(
    @SerializedName("id") // En caso de que no lo pueda leer Kotlin
    var id: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("actor")
    var actor: String?,

)
