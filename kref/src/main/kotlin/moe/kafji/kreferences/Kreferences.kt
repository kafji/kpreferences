package moe.kafji.kreferences

import android.content.SharedPreferences
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

class Kreferences(val p: SharedPreferences) {

  fun <T : Any> create(clazz: KClass<T>): T {
    @Suppress("UNCHECKED_CAST")
    return Proxy.newProxyInstance(clazz.java.classLoader, arrayOf(clazz.java), InvocationHandler { proxy, method, args ->
      println("method: $method")
      val methodName = method.name
      println("method name: $methodName")
      val returnType = method.returnType
      println("method return type: $returnType")
      if (returnType != Void::class.java) {
        return@InvocationHandler p.getBoolean(keyFromGetter(method.name), PreferenceDelegates.defaultBoolean)
      } else {
      }
    }) as T
  }


}
