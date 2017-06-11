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
open class Artist(

        @PrimaryKey open var id: Int = 0,

        open var groupId: Int = 0,

        open var name: String = "",

        open var fullname: String = "",

        open var gender: String = "",

        open var birth: String = "",

        open var nationality: String = "",

        open var agency: String = "",

        open var debut: String = "",

        open var image: String = "",

        open var photo: String? = null

) : RealmObject() {

    fun needRefresh(): Boolean = gender.isEmpty()

    fun getGenderStr(): String = when (gender) {
        "female" -> "여자"
        "male" -> "남자"
        else -> "Unknown"
    }

    fun getPhotoList(): List<String> {
        if (photo == null) {
            return listOf(image)
        } else {
            return Gson().fromJson<List<String>>(photo, object : TypeToken<List<String>>() {}.type)
        }
    }
}