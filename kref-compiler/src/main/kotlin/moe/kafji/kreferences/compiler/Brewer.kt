package moe.kafji.kreferences.compiler

import android.content.SharedPreferences
import com.squareup.kotlinpoet.*
import moe.kafji.kreferences.PreferenceDelegates

internal class Brewer internal constructor(val packageName: String, val superName: String) {

  val className = prefix + superName

  private val ctorParamSharedPreferences = ParameterSpec.builder(TypeName.Companion.get(SharedPreferences::class), "p").build()

  private val props = mutableListOf<PropertySpec>()

  fun brew(): KotlinFile {
    val primaryCtor = FunSpec.constructorBuilder()
        .addParameter(ctorParamSharedPreferences)
        .build()

    val clazz = TypeSpec.classBuilder(className)
        .addSuperinterface(ClassName.get(packageName, superName))
        .primaryConstructor(primaryCtor)
        .addProperties(props)
        .build()

    return KotlinFile.builder(packageName, className)
        .addType(clazz)
        .build()
  }

  fun addBooleanPreference(name: String, key: String? = null, defaultValue: Boolean = PreferenceDelegates.defaultBoolean) {
    val builder = PropertySpec.varBuilder(BOOLEAN, name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.boolean(%N, %L, %L)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  fun addStringPreference(name: String, key: String? = null, defaultValue: String = PreferenceDelegates.defaultString) {
    val builder = PropertySpec.varBuilder(String::class, name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.string(%N, %L, %S)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  fun addIntPreference(name: String, key: String? = null, defaultValue: Int = PreferenceDelegates.defaultInt) {
    val builder = PropertySpec.varBuilder(INT, name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.int(%N, %L, %L)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  fun addLongPreference(name: String, key: String? = null, defaultValue: Long = PreferenceDelegates.defaultLong) {
    val builder = PropertySpec.varBuilder(LONG, name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.long(%N, %L, %L)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  fun addFloatPreference(name: String, key: String? = null, defaultValue: Float = PreferenceDelegates.defaultFloat) {
    val builder = PropertySpec.varBuilder(FLOAT, name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.float(%N, %L, %L)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  fun addStringSetPreference(name: String, key: String? = null, defaultValue: Set<String> = PreferenceDelegates.defaultStringSet) {
    val builder = PropertySpec.varBuilder(ParameterizedTypeName.get(Set::class, String::class), name)
        .addModifiers(KModifier.OVERRIDE)
        .delegated(true)
    builder.initializer("%T.stringSet(%N, %L, %L)", PreferenceDelegates::class, ctorParamSharedPreferences, key, defaultValue)
    props += builder.build()
  }

  companion object {
    val prefix = "Kreferences"
  }
}

internal fun brewer(packageName: String, superName: String, fn: Brewer.() -> Unit) = Brewer(packageName, superName).apply(fn)
