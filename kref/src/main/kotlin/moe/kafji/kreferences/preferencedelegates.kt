package moe.kafji.kreferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


object PreferenceDelegates {

  const val defaultBoolean = false
  const val defaultString = ""
  const val defaultInt = 0
  const val defaultLong = 0L
  const val defaultFloat = 0f
  val defaultStringSet = emptySet<String>()

  fun boolean(p: SharedPreferences, key: String? = null, default: Boolean = defaultBoolean) = BooleanPreferenceProperty(p, key, default)
  fun string(p: SharedPreferences, key: String? = null, default: String = defaultString) = StringPreferenceProperty(p, key, default)
  fun int(p: SharedPreferences, key: String? = null, default: Int = defaultInt) = IntPreferenceProperty(p, key, default)
  fun long(p: SharedPreferences, key: String? = null, default: Long = defaultLong) = LongPreferenceProperty(p, key, default)
  fun float(p: SharedPreferences, key: String? = null, default: Float = defaultFloat) = FloatPreferenceProperaty(p, key, default)
  fun stringSet(p: SharedPreferences, key: String? = null, default: Set<String> = defaultStringSet) = StringSetPreferenceProperty(p, key, default)
}

abstract class PreferenceProperty<in R, T> internal constructor(private val key: String?) : ReadWriteProperty<R, T> {
  fun key(property: KProperty<*>): String = key ?: property.name
}

class BooleanPreferenceProperty internal constructor(val p: SharedPreferences, key: String?, val default: Boolean) : PreferenceProperty<Any, Boolean>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): Boolean = p.getBoolean(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
    p.edit().putBoolean(key(property), value).apply()
  }
}

class StringPreferenceProperty internal constructor(val p: SharedPreferences, key: String?, val default: String) : PreferenceProperty<Any, String>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): String = p.getString(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
    p.edit().putString(key(property), value).apply()
  }
}

class IntPreferenceProperty internal constructor(val p: SharedPreferences, key: String? = null, val default: Int) : PreferenceProperty<Any, Int>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): Int = p.getInt(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
    p.edit().putInt(key(property), value).apply()
  }
}

class LongPreferenceProperty internal constructor(val p: SharedPreferences, key: String? = null, val default: Long) : PreferenceProperty<Any, Long>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): Long = p.getLong(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
    p.edit().putLong(key(property), value).apply()
  }
}

class FloatPreferenceProperty internal constructor(val p: SharedPreferences, key: String? = null, val default: Float) : PreferenceProperty<Any, Float>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): Float = p.getFloat(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
    p.edit().putFloat(key(property), value).apply()
  }
}

class StringSetPreferenceProperty internal constructor(val p: SharedPreferences, key: String? = null, val default: Set<String>) : PreferenceProperty<Any, Set<String>>(key) {
  override fun getValue(thisRef: Any, property: KProperty<*>): Set<String> = p.getStringSet(key(property), default)
  override fun setValue(thisRef: Any, property: KProperty<*>, value: Set<String>) {
    p.edit().putStringSet(key(property), value).apply()
  }
}
