package tn.esprit.nascar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.esprit.nascar.models.Event

@Database(entities = [Event::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun eventsDao() : EventsDao


    companion object {

        //Singleton
        private var instance : MyDatabase? = null
        fun getDatabase(context: Context) : MyDatabase{
            if(instance != null)
                return instance!!

            instance =  Room.databaseBuilder(context, MyDatabase::class.java, "favoris")
                .allowMainThreadQueries().build()
            return instance!!
        }
    }
}