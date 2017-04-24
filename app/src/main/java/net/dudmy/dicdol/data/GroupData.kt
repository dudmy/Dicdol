package net.dudmy.dicdol.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupJson {

    @SerializedName("version")
    var version: String = ""

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("items")
    var items: List<Group>? = null

}

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
}