package com.example.metstud

import android.os.Parcel
import android.os.Parcelable

data class studentData(
    var dataImage: Int,
    var dataName: String,
    var dataNim: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(dataImage)
        parcel.writeString(dataName)
        parcel.writeString(dataNim)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<studentData> {
        override fun createFromParcel(parcel: Parcel): studentData {
            return studentData(parcel)
        }

        override fun newArray(size: Int): Array<studentData?> {
            return arrayOfNulls(size)
        }
    }
}
