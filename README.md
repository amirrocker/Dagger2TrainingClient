### Description

#### 1. What is this project?

A dagger 2 based application used as a playground to learn dagger 2 with it. 
The idea and inspiration for this came after watching lucia's excellent talk about her reactive
store. The Application uses the concept she described using an in-memory store and an in-memory 
cache implementations. 
It would be nice to extend these concepts to a more persistent layer.

Another aspect of the project was to implement a clean-architecture in android as well as 
adhering to common principles such as Interactors, Aggregates, BoundedContexts, VO's, ACL's 
and DTO's. (Isnt't it cool to be able to do some name dropping :))) 

### 2. How far along is it?

Well, a first running version was ready with the initial commit. But there are many, many things
still either not ready, not good or not yet implemented. This project is currently WIP and 
will be brought along depending on my leisure time.

### 3. What is the Vision of the App?

that is still yet to be determined. The overall goal is to parse, process, receive and send JSON
to and from a backend. 

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

