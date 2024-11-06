package com.chandra.practice.pointofsaleapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chandra.practice.pointofsaleapp.data.CreateAccountDetails
import com.chandra.practice.pointofsaleapp.data.NewGenerateBillCustomerDetails
import com.chandra.practice.pointofsaleapp.util.Converters

@Database(entities = [CreateAccountDetails::class,NewGenerateBillCustomerDetails::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)  // Register your converters
abstract class AppDatabase : RoomDatabase() {

    abstract fun createAccountDao(): CreateAccountDao
    abstract fun newGenerateBillDao(): NewGenerateBillDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // This would be the SQL to migrate your database schema
                database.execSQL("CREATE TABLE IF NOT EXISTS `userGenerateBillTable` (...)")
            }
        }
    }

}
