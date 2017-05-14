package net.dudmy.dicdol.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by yujin on 2017. 4. 24..
 */

@RealmClass
open class Group(

        @PrimaryKey open var id: Int = 0,

        open var name: String = "",

        open var agency: String = "",

        open var image: String = "",

        open var type: String = "",

        open var debut: String = "",

        open var debutSong: String = "",

        open var artists: RealmList<Artist>? = null,

        open var albums: RealmList<Album>? = null,

        open var favorite: Boolean = false

) : RealmObject() {

    fun getTypeStr(): String = when (type) {
        "boy" -> "보이그룹"
        "girl" -> "걸그룹"
        "coed" -> "혼성그룹"
        else -> "알수없음"
    }

    fun needRefresh(): Boolean = type.isEmpty() or debut.isEmpty()
}