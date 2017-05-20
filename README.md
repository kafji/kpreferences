# Kpreferences

_This is work in progress._

## Concept

Generate a class wrapper for SharedPreferences in Kotlin so we can use its properties to retrieve and set value in SharedPreferences.

```kotlin
interface MyPreferences {
  var userOnboardingDone: Boolean
}
```

By proxy class (reflection) like Retrofit.

```kotlin
val myPrefs = Kreferences(sharedPreferences).create(MyPreferences::class)
if (myPrefs.userOnboadingDone) {
  showUserOnboarding()
}
```

By annotation processing and codegen like Dagger and ButterKnife.

```kotlin
@Preferences interface MyPreferences {
  var userOnboardingDone: Boolean
}

val myPrefs = KreferencesMyPreferences(sharedPreferences)
if (myPrefs.userOnboadingDone) {
  showUserOnboarding()
}
```
