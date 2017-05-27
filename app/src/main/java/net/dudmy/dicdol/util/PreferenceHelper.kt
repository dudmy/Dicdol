package net.dudmy.dicdol.util
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import kotlin.reflect.KProperty

/**
 * Created by yujin on 2017. 4. 24..
 */

object PreferenceHelper {

    abstract class PrefDelegate<T>(val prefKey: String) {

        companion object {
            private var context: Context? = null

            /**
             * Initialize PrefDelegate with a Context reference.
             * This method needs to be called before any other usage of PrefDelegate.
             */
            fun initialize(context: Context) {
                this.context = context
            }
        }

        protected val sp: SharedPreferences? by lazy {
            if (context == null) {
                Log.e("PrefDelegate", "Context was not initialized. Call PrefDelegate.initialize(context) before using it")
            }
            PreferenceManager.getDefaultSharedPreferences(context)
        }

        abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
        abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
    }

    internal fun stringPref(prefKey: String, defaultValue: String? = null) = StringPrefDelegate(prefKey, defaultValue)

    internal class StringPrefDelegate(prefKey: String, val defaultValue: String?) : PrefDelegate<String?>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = sp?.getString(prefKey, defaultValue)
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
            sp?.edit()?.putString(prefKey, value)?.apply()
        }
    }

    internal fun intPref(prefKey: String, defaultValue: Int = 0) = IntPrefDelegate(prefKey, defaultValue)

    internal class IntPrefDelegate(prefKey: String, val defaultValue: Int) : PrefDelegate<Int>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>) = sp?.getInt(prefKey, defaultValue) ?: defaultValue
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            sp?.edit()?.putInt(prefKey, value)?.apply()
        }
    }
}