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

#Preserve Classes and Methods for AES Encryption
# Preserve Cipher and other cryptography-related classes
-keep class javax.crypto.** { *; }
-keep class javax.crypto.spec.** { *; }
-keep class javax.crypto.KeyGenerator { *; }

# Preserve methods in Cipher and other encryption-related classes
-keepclassmembers class javax.crypto.Cipher {
    #public static * getInstance(...);
    #public * init(...);
    #public * do33Final(...);
}

# Preserve SecretKey and related classes
-keep class javax.crypto.SecretKey { *; }
-keep class javax.crypto.spec.IvParameterSpec { *; }

# Preserve all generated methods for encryption and decryption
-keepclassmembers class * {
    public <methods>;
}

# Keep encryption and decryption method signatures (e.g., encrypt, decrypt) from obfuscation
-keepclassmembers class * {
    public static ** encrypt(...);
    public static ** decrypt(...);
}

# If you are using KeyGenerator for generating AES keys, make sure it is not obfuscated
-keepclassmembers class javax.crypto.KeyGenerator {
    public static <methods>;
}

#Keep Classes Involved in Encryption Algorithms
# Keep classes involved in AES encryption algorithms and any related transformations
-keep class javax.crypto.Cipher { *; }
#-keep class javax.crypto.KeyFactory { *; }
#-keep class javax.crypto.KeyPairGenerator { *; }

# Preserve KeyStore if Used
# Keep Android Keystore classes
-keep class android.security.keystore.** { *; }
-keep class java.security.KeyStore { *; }
-keep class java.security.KeyStore$Entry { *; }
-keep class java.security.Key { *; }

#Keep Classes for Custom Encryption Methods
# Keep custom encryption classes/methods
-keep class com.chandra.practice.pointofsaleapp.util.EncryptionUtils { *; }
-keepclassmembers class com.chandra.practice.pointofsaleapp.util.EncryptionUtils {
     #public static com.chandra.practice.pointofsaleapp.util.EncryptionUtils.encrypt(java.lang.String, javax.crypto.SecretKey);//error
     #public static java.lang.String decrypt(java.lang.String, javax.crypto.SecretKey);//error

}
#Avoid Obfuscating String-based Encryption Keys
# Keep all string values, especially if they are used as keys
-keepclassmembers class * {
    public static final java.lang.String *;
}
#Keep Methods for Key Generation (if using KeyGenerator)
# Keep KeyGenerator methods from obfuscation
-keepclassmembers class javax.crypto.KeyGenerator {
#    public static javax.crypto.SecretKey generateKey(...);//error
}

# Keep Methods Used for Base64 Encoding/Decoding
# Keep Base64 encoding/decoding methods
-keep class java.util.Base64 { *; }

# Keep Standard Security Libraries
# Keep Java security-related classes
-keep class java.security.MessageDigest { *; }
-keep class java.security.SecureRandom { *; }

# Keep string-based encryption keys intact
-keepclassmembers class * {
    public static final java.lang.String *;
}
