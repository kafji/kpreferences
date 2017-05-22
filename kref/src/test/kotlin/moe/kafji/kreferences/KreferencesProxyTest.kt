package moe.kafji.kreferences

import android.app.Activity
import android.preference.PreferenceManager
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class) @Config(constants = BuildConfig::class) class KreferencesProxyTest {

  private lateinit var kreferences: Kreferences

  @Before
  fun setUp() {
    val controller = Robolectric.buildActivity(Activity::class.java)
    val activity = controller.get()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    kreferences = Kreferences(sharedPreferences)
  }

  interface Preferences {
    var init: Boolean
  }

  @Test fun boolean() {
    val preferences = kreferences.create(Preferences::class)
    assertThat(preferences.init).isEqualTo(false)
    preferences.init = true
    assertThat(preferences.init).isEqualTo(true)
  }
}
