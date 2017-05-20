package moe.kafji.kreferences.example

import android.app.Activity
import android.os.Bundle

class KreferencesActivity : Activity() {

  lateinit var preferences: IPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    preferences.init = true
    preferences.text = "hello"
    preferences.number = Int.MAX_VALUE
    preferences.longNumber = Long.MAX_VALUE
    preferences.decimal = Float.MAX_VALUE
    preferences.manyString = setOf("hello", "world")
  }
}
