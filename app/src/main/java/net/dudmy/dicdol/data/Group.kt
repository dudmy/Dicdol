package net.dudmy.dicdol.data

/**
 * Created by yujin on 2017. 4. 24..
 */

data class Group(val id: String = "",

                 val name: String = "",

                 val agency: String = "",

                 val image: String = "",

                 val type: String = "",

                 val debut: String = "",

                 val debutSong: String = "",

                 val artists: List<Artist>? = null,

                 val albums: List<Album>? = null) {

    fun getTypeStr(): String =
            when (type) {
                "boy" -> "보이그룹"
                "girl" -> "걸그룹"
                "coed" -> "혼성그룹"
                else -> "알수없음"
            }
}