package net.dudmy.dicdol.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by yujin on 2017. 4. 26..
 */

@RealmClass
open class Artist(

        @PrimaryKey open var id: String = "",

        open var name: String = "",

        open var image: String = ""

) : RealmObject() {

}