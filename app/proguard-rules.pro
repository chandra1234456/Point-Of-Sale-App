# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep your app's entry point (MainActivity, or any other class you want to keep)
-keep public class com.chandra.practice.pointofsaleapp.MainActivity { *; }

# Keep the methods of any sensitive class
-keepclassmembers class * {
    public <methods>;
}

# Allow some third-party libraries to be kept
-keep class com.google.** { *; }

#ProGuard rules for Room Database:
# Keep Room annotations (Entity, Dao, PrimaryKey, etc.)
-keep class androidx.room.** { *; }

# Keep Room database classes
-keep class com.yourpackage.room.** { *; }

# Keep the Room generated database class and DAO interfaces
-keep class androidx.room.RoomDatabase { *; }

# Keep the Room-generated classes (for data class, DAO, and database)
-keepnames class com.yourpackage.room.** { *; }

# Don't remove default constructor for Room
-keepclassmembers class * {
    public <init>(...);
}

# Avoid stripping generic types used in Room
-keep class androidx.room.** implements androidx.lifecycle.LiveData
-keep class androidx.room.** implements androidx.lifecycle.MutableLiveData

#ProGuard rules for ViewModel:
# Keep ViewModels
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.ViewModelProvider$* { *; }
#keep class androidx.lifecycle.viewmodel.savedstate.SavedStateViewModelFactory { *; }

# Keep ViewModel factory classes
-keep class com.yourpackage.viewmodel.** { *; }

# Don't remove LiveData
-keep class androidx.lifecycle.LiveData { *; }

#ProGuard rules for Coroutines:
# Keep coroutine-related classes from being obfuscated or stripped
-keep class kotlinx.coroutines.** { *; }

# Keep kotlinx.coroutines.* classes and functions for non-obfuscation
-keepclassmembers class kotlinx.coroutines.** { *; }

# Keep suspend functions, coroutines may internally use them
#-keepclassmembers class * {
#    public suspend <methods>;
#}

# Keep other coroutine-related internals
-keep class kotlin.coroutines.** { *; }



#ProGuard rules for SharedPreferences:
# Keep SharedPreferences usage intact
-keep class android.content.SharedPreferences { *; }
-keep class com.yourpackage.sharedprefs.** { *; }

#General Rules e.g., Annotations, Parcelable, etc.
# Keep Parcelable classes
-keep class * implements android.os.Parcelable { *; }

# Keep Serializable classes
-keep class * implements java.io.Serializable { *; }

# Keep classes and methods marked with annotations (e.g., @Entity, @Dao, @SerializedName)
-keepclassmembers class * {
    @androidx.room.Entity <fields>;
    @androidx.room.Dao <methods>;
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep classes with "public" and "default" constructors
-keepclassmembers class * {
    public <init>(...);
}

#Keep Resources and Native Libraries
# Keep JNI (native) libraries
-keepclasseswithmembers class * {
    native <methods>;
}
#Keep Logging and Debugging Methods
# Keep your logging classes (e.g., Log.d, Log.e, etc.)
-keep class android.util.Log { *; }

#Keep Retrofit & Gson (if used)
# Keep Retrofit classes and methods
-keep class retrofit2.** { *; }
-keep class com.yourpackage.network.** { *; }

# Keep Gson classes and methods
-keep class com.google.gson.** { *; }
-keep class com.yourpackage.model.** { *; }

# Keep the Gson TypeAdapterFactory
-keep class com.google.gson.TypeAdapterFactory { *; }

#Keep Kotlin Synthetic Accessors  Kotlin synthetic properties (import kotlinx.android.synthetic.main.*)
# Keep Kotlin synthetic accessors
-keep class kotlinx.android.synthetic.** { *; }
