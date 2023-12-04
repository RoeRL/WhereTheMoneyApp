package com.example.wheremymoneyappkotlin

import android.os.Parcel
import android.os.Parcelable

data class HistoryTransaction(
    val item: String?,
    val itemValue: Double,
    val itemId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(item)
        parcel.writeDouble(itemValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryTransaction> {
        override fun createFromParcel(parcel: Parcel): HistoryTransaction {
            return HistoryTransaction(parcel)
        }

        override fun newArray(size: Int): Array<HistoryTransaction?> {
            return arrayOfNulls(size)
        }
    }
}