package net.dudmy.dicdol.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by yujin on 2017. 4. 26..
 */

@RealmClass
open class Album(

        @PrimaryKey open var id: Int = 0,

        open var name: String = "",

        open var image: String = ""

) : RealmObject() {

}