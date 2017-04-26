package net.dudmy.dicdol.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by yujin on 2017. 4. 24..
 */

data class Group(val id: String = "",
                 val name: String = "",
                 val agency: String = "",
                 val image: String = "",
                 val type: String = "") : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Group> = object : Parcelable.Creator<Group> {
            override fun createFromParcel(source: Parcel): Group = Group(source)
            override fun newArray(size: Int): Array<Group?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(name)
        dest?.writeString(agency)
        dest?.writeString(image)
        dest?.writeString(type)
    }

    fun getTypeStr(): String =
            when (type) {
                "boy" -> "보이그룹"
                "girl" -> "걸그룹"
                "coed" -> "혼성그룹"
                else -> "알수없음"
            }
}