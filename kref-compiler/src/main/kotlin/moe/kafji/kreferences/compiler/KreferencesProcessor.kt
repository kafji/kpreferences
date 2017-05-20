package moe.kafji.kreferences.compiler

import com.google.auto.service.AutoService
import moe.kafji.kreferences.Preferences
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

@AutoService(Processor::class) class KreferencesProcessor : AbstractProcessor() {

  private lateinit var messager: Messager
  private lateinit var filer: Filer
  private lateinit var elementUtils: Elements
  private lateinit var typeUtils: Types

  override fun init(processingEnv: ProcessingEnvironment) {
    super.init(processingEnv)

    messager = processingEnv.messager
    filer = processingEnv.filer
    elementUtils = processingEnv.elementUtils
    typeUtils = processingEnv.typeUtils

    log("heeeey\n")
  }

  override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

  override fun getSupportedOptions(): MutableSet<String> {
    return mutableSetOf(Preferences::class.java.canonicalName)
  }

  override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
    log("processing\n")

    throw RuntimeException()

    val kotlinGenerated = processingEnv.options["kapt.kotlin.generated"]
    println(kotlinGenerated)
    log(kotlinGenerated!!)

    log(annotations.toString())
    log(roundEnv.toString())

    brewer("test", "MyPrefs", {
      addBooleanPreference("bool")
      addStringPreference("stringer")
      addIntPreference("inter")
      addLongPreference("longer")
      addFloatPreference("floater")
      addStringSetPreference("stringSetter")
    }).brew().writeTo(File(kotlinGenerated, "durr.kt").writer().buffered())

    return true
  }

  fun log(msg: String) = messager.printMessage(Diagnostic.Kind.NOTE, msg)
}
