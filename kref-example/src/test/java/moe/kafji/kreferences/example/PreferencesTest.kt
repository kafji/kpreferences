package moe.kafji.kreferences.example

import android.preference.PreferenceManager
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

@RunWith(RobolectricTestRunner::class) @Config(constants = BuildConfig::class) class PreferencesTest {

  private val preferencesClazzes = listOf(HandmadePreferences::class)

  private fun testWith(testFn: (controller: ActivityController<KreferencesActivity>, preferences: IPreferences) -> Unit) {
    preferencesClazzes.forEach {
      it.memberProperties.first()

      val controller = Robolectric.buildActivity(KreferencesActivity::class.java)
      val activity = controller.get()
      val preferences = it.primaryConstructor!!.call(PreferenceManager.getDefaultSharedPreferences(activity))
      activity.preferences = preferences
      testFn(controller, preferences)
    }
  }

  @Test fun initPropDefault_shouldBeFalse() {
    testWith({ _, preferences ->
      assertThat(preferences.init).isFalse()
    })
  }

  @Test fun initProp_afterActivityCreated_shouldBeTrue() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.init).isTrue()
    })
  }

  @Test fun textPropDefault_shouldBeEmptyString() {
    testWith({ _, preferences ->
      assertThat(preferences.text).isEmpty()
    })
  }

  @Test fun textProp_afterActivityCreated_shouldBeHello() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.text).isEqualTo("hello")
    })
  }

  @Test fun numberPropDefault_shouldBeZeroInt() {
    testWith({ _, preferences ->
      assertThat(preferences.number).isEqualTo(0)
    })
  }

  @Test fun numberProp_afterActivityCreated_shouldBeMaxInt() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.number).isEqualTo(Int.MAX_VALUE)
    })
  }

  @Test fun longNumberPropDefault_shouldBeZeroLong() {
    testWith({ _, preferences ->
      assertThat(preferences.longNumber).isEqualTo(0L)
    })
  }

  @Test fun longNumberProp_afterActivityCreated_shouldBeMaxLong() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.longNumber).isEqualTo(Long.MAX_VALUE)
    })
  }

  @Test fun decimalPropDefault_shouldBeZeroFloat() {
    testWith({ _, preferences ->
      assertThat(preferences.decimal).isEqualTo(0f)
    })
  }

  @Test fun decimalProp_afterActivityCreated_shouldBeMaxFloat() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.decimal).isEqualTo(Float.MAX_VALUE)
    })
  }

  @Test fun manyStringPropDefault_shouldBeEmptySet() {
    testWith({ _, preferences ->
      assertThat(preferences.manyString).isEqualTo(emptySet<String>())
    })
  }

  @Test fun manyStringProp_afterActivityCreated_shouldBeHelloAndWorld() {
    testWith({ controller, preferences ->
      controller.create()
      assertThat(preferences.manyString).isEqualTo(setOf("hello", "world"))
    })
  }
}
