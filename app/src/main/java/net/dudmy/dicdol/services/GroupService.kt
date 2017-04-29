package net.dudmy.dicdol.services

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.util.assetsPrefix
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by yujin on 2017. 4. 26..
 */

interface GroupService {

    companion object {
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                    .baseUrl(assetsPrefix)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    }

    @GET("group/group.json")
    fun getGroupList(): Call<List<Group>>

    @GET("group/{id}.json")
    fun getGroup(@Path("id") groupId: String): Call<Group>
}