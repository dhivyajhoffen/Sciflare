package com.example.sciflare

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class UserModel(
    @PrimaryKey(autoGenerate = true) val serial_id: Int = 0,
    val _id: String,
    val name: String,
    val email: String,
    val mobile: String,
    val gender: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM UserModel")
    fun getAll(): List<UserModel>

    @Insert
    fun insert(user: UserModel)
}
