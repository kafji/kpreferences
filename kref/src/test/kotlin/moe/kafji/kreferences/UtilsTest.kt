package moe.kafji.kreferences

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UtilsTest {
  @Test fun keyFromGetter() {
    assertThat(keyFromGetter("getHello")).isEqualTo("hello")
  }

  @Test
  fun keyFromSetter() {
    assertThat(keyFromSetter("setHello")).isEqualTo("hello")
  }
}
