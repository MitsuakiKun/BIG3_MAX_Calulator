package MaxCalculator.helpers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import MaxCalculator.models.User
import java.io.File
import java.lang.reflect.Type

class UserDataManager(private val jsonFilePath: String) {
    private val gson = Gson()
    private val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    private val listType: Type = object : TypeToken<List<User>>() {}.type
    private val users: MutableList<User>


    init {
        users = loadUsers()
    }

    private fun loadUsers(): MutableList<User> {
        val file = File(jsonFilePath)
        if (file.exists()) {
            val json = file.readText()
            return gson.fromJson(json, listType)
        }
        return mutableListOf()
    }

    private fun saveUsers() {
        val json = gsonBuilder.toJson(users, listType)
        File(jsonFilePath).writeText(json)
    }

    fun registerUser(tmp_username: String, password: String): Boolean {
        if (users.any { it.username == tmp_username }) {
            return false
        }
        users.add(User(tmp_username, password))
        saveUsers()
        return true
    }

    fun loginUser(tmp_username: String, password: String): Boolean {
        val user = users.find { it.username == tmp_username }
        return user?.password == password
    }

}
