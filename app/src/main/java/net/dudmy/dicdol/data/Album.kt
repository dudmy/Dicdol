package net.dudmy.dicdol.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by yujin on 2017. 4. 26..
 */

@RealmClass
open class Album(

        @PrimaryKey open var id: Int = 0,

        open var groupId: Int = 0,

        open var name: String = "",

        open var day: String = "",

        open var publishing: String = "",

        open var agency: String = "",

        open var genre: String = "",

        open var image: String = "",

        open var songs: String? = null

) : RealmObject() {

    fun needRefresh(): Boolean = day.isEmpty()

    fun getSongs(): List<String>? = songs?.run {
        Gson().fromJson<List<String>>(songs, object : TypeToken<List<String>>() {}.type)
    }
}