package com.nszalas.myassistant.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class, Subject::class, Grade::class], version = 4)
abstract class MyAssistantDatabase: RoomDatabase() {

    abstract fun MyAssistantDAO(): MyAssistantDAO

    companion object{
        @Volatile
        private var INSTANCE: MyAssistantDatabase? = null

        fun getDatabase(context: Context): MyAssistantDatabase{

            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(lock = this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyAssistantDatabase::class.java,
                    "myassistant_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

}