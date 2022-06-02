package ru.wb.treveltime.activity.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Flight(
    @SerializedName("startCity")
    @Expose
    val startCity: String,

    @SerializedName("startCityCode")
    @Expose
    val startCityCode: String,

    @SerializedName("endCity")
    @Expose
    val endCity: String,

    @SerializedName("endCityCode")
    @Expose
    val endCityCode: String,

    @SerializedName("startDate")
    @Expose
    val startDate: String,

    @SerializedName("endDate")
    @Expose
    val endDate: String,

    @SerializedName("price")
    @Expose
    val price: Int,

    @SerializedName("searchToken")
    @Expose
    val searchToken: String,

    var liked: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(startCity)
        parcel.writeString(startCityCode)
        parcel.writeString(endCity)
        parcel.writeString(endCityCode)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeInt(price)
        parcel.writeString(searchToken)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flight> {
        override fun createFromParcel(parcel: Parcel): Flight {
            return Flight(parcel)
        }

        override fun newArray(size: Int): Array<Flight?> {
            return arrayOfNulls(size)
        }

    }

}