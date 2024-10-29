package tn.esprit.nascar.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import tn.esprit.nascar.models.Event

@Dao
interface EventsDao {

    @Insert
    fun insert(event: Event)

    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE title = :title")
    fun getByTitle(title: String): Event

}