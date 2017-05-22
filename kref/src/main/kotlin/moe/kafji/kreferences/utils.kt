package moe.kafji.kreferences

import java.lang.reflect.Method

fun isSetter(method: Method): Boolean {
  if (method.returnType != Void::class.java) return false
  return true
}

fun keyFromGetter(methodName: String): String {
  assert(methodName.substring(0..2) == "get")
  return methodName[3].toLowerCase() + methodName.substring(4)
}

fun keyFromSetter(methodName: String): String {
  assert(methodName.substring(0..2) == "set")
  return methodName[3].toLowerCase() + methodName.substring(4)
}
