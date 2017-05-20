package moe.kafji.kreferences.example

import android.content.SharedPreferences
import moe.kafji.kreferences.PreferenceDelegates
import moe.kafji.kreferences.Preferences

@Preferences interface IPreferences {
  var init: Boolean
  var text: String
  var number: Int
  var longNumber: Long
  var decimal: Float
  var manyString: Set<String>
}

class HandmadePreferences(p: SharedPreferences) : IPreferences {
  override var init: Boolean by PreferenceDelegates.boolean(p)
  override var text: String by PreferenceDelegates.string(p)
  override var number: Int by PreferenceDelegates.int(p)
  override var longNumber: Long by PreferenceDelegates.long(p)
  override var decimal: Float by PreferenceDelegates.float(p)
  override var manyString: Set<String> by PreferenceDelegates.stringSet(p)
}
