package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.settings

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "isLogged") val isLogged: Boolean = false,
)

@Dao
interface SettingsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(settings: Settings)

    @Query("SELECT * FROM settings ORDER BY id DESC")
    fun getAll(): LiveData<List<Settings>>

    @Delete
    fun delete(settings: Settings)
}