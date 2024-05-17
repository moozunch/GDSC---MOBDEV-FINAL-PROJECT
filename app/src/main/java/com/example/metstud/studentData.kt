package com.example.metstud

import android.os.Parcel
import android.os.Parcelable

data class studentData(
    var dataImage: String,
    var dataName: String,
    var dataNim: String,
    var dataEmail: String,
    var dataAngkatan: Int,
    var dataFakultas: String,
    var dataProdi: String,
    var dataSemester: Int,
    var dataStatus: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dataImage)
        parcel.writeString(dataName)
        parcel.writeString(dataNim)
        parcel.writeString(dataEmail)
        parcel.writeInt(dataAngkatan)
        parcel.writeString(dataFakultas)
        parcel.writeString(dataProdi)
        parcel.writeInt(dataSemester)
        parcel.writeString(dataStatus)
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
