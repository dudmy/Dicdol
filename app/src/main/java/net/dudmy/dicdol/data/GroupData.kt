package net.dudmy.dicdol.data

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
                 val type: String = "")