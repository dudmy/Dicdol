package net.dudmy.dicdol.data.mapper

import com.google.gson.JsonElement
import net.dudmy.dicdol.data.Album

/**
 * Created by yujin on 2017. 8. 1..
 */

class AlbumApiMapper {

    fun doMaterialize(from: JsonElement, to: Album) {
        val json = from.asJsonObject

        to.id = json.get("id").asInt
        to.groupId = json.get("groupId").asInt
        to.name = json.get("name").asString
        to.day = json.get("day").asString
        to.publishing = json.get("publishing").asString
        to.agency = json.get("agency").asString
        to.genre = json.get("genre").asString
        to.image = json.get("image").asString

        val songsJson = json.get("songs").asJsonArray
        if (songsJson.size() > 0) {
            to.songs = songsJson.toString()
        }
    }
}