package com.rkeller.harrypotterrk.model

import android.os.Parcel
import android.os.Parcelable
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
    @SerializedName("house")
    var house: String?,
    @SerializedName("dateOfBirth")
    var dateOfBirth: String?,

    @SerializedName("species")
    var species: String?,
    @SerializedName("gender")
    var gender: String?,
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) // https://guides.codepath.com/android/using-parcelable
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(actor)
        parcel.writeString(house)
        parcel.writeString(dateOfBirth)
        parcel.writeString(species)
        parcel.writeString(gender)
        parcel.writeString(ancestry)
        parcel.writeString(patronus)
        parcel.writeString(alive)
        parcel.writeString(hogwartsStudent)
        parcel.writeString(hogwartsStaff)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }


        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }
}