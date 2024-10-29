package tn.esprit.nascar.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(@PrimaryKey(autoGenerate = true) val id: Int = 0, val title: String, val date: String, val image: Int) {
}