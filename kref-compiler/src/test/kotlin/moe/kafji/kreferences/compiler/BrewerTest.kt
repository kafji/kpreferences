package moe.kafji.kreferences.compiler

import org.junit.Test

class BrewerTest {
  @Test fun brew() {
    val kotlinFile = brewer("test", "MyPrefs", {
      addBooleanPreference("bool")
      addStringPreference("stringer")
      addIntPreference("inter")
      addLongPreference("longer")
      addFloatPreference("floater")
      addStringSetPreference("stringSetter")
    }).brew()
    print(kotlinFile.toString())
  }
}
