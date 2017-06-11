package net.dudmy.dicdol.data.mapper

import com.google.gson.JsonElement
import net.dudmy.dicdol.data.Artist

/**
 * Created by yujin on 2017. 6. 11..
 */

class ArtistApiMapper {

    fun doMaterialize(from: JsonElement, to: Artist) {
        val json = from.asJsonObject

        to.id = json.get("id").asInt
        to.groupId = json.get("groupId").asInt
        to.name = json.get("name").asString
        to.fullname = json.get("fullname").asString
        to.gender = json.get("gender").asString
        to.birth = json.get("birth").asString
        to.nationality = json.get("nationality").asString
        to.agency = json.get("agency").asString
        to.debut = json.get("debut").asString
        to.image = json.get("image").asString

        val photoJson = json.get("photo").asJsonArray
        to.photo = photoJson.toString()
    }
}