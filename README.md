### source material 
https://developer.android.com/training/dependency-injection/dagger-android
https://github.com/android/architecture-samples/tree/dev-dagger
https://developer.android.com/codelabs/android-dagger#10
 

### Topics and Points to remember

1. Google introduces API for faster Kotlin builds - Recherche! 
https://koliasa.com/google-introduces-api-for-faster-kotlin-builds/

....

11. Scoping Subcomponents
We created a subcomponent because we needed to share the same instance of RegistrationViewModel 
between the Activity and Fragments. <b>As we did before, if we annotate the Component and classes 
with the same scope annotation, that'll make that typehave a unique instance in the Component.</b>

However, we cannot use @Singleton because it's already been used by AppComponent. 
We need to create a different one.

Scoping rules:

- <b>When a type is marked with a scope annotation, it can only be used by Components that are 
annotated with the same scope.</b>
- When a Component is marked with a scope annotation, it can only provide types with that 
annotation or types that have no annotation.
-A subcomponent cannot use a scope annotation used by one of its parent Components.

Components also involve subcomponents in this context.

