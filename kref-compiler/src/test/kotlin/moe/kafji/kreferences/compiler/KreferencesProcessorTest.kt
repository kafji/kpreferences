package moe.kafji.kreferences.compiler

import com.google.testing.compile.JavaFileObjects
import org.junit.Test

class KreferencesProcessorTest {

  @Test fun trivial() {
    val source = JavaFileObjects.forSourceString("test.Prefs",
        arrayOf(
            "package test;",
            "",
            "import moe.kafji.kref.Preferences;",
            "",
            "@Preferences",
            "interface Prefs {",
            //            "  val init: Boolean",
            "}"
        ).joinToString(separator = "\n"))

    val expectedSource = JavaFileObjects.forSourceString("test.Preferences",
        arrayOf(
            "package test",
            "",
            "import android.content.SharedPreferences",
            "",
            "class KreferencesAppPreferences(private val p : SharedPreferences) : AppPreferences {",
            "",
            "  override var init: Boolean",
            "    get() {",
            "      return p.getBoolean(\"init\", false)",
            "    }",
            "    set(value) {",
            "      p.edit()",
            "          .putBoolean(\"init\", value)",
            "          .apply()",
            "    }",
            "}"
        ).joinToString(separator = "\n"))

//    assertAbout(javaSource())
//        .that(source)
//        .processedWith(KreferencesProcessor())
//        .compilesWithoutError()
//        .and()
//        .generatesSources(expectedSource)
  }
}

