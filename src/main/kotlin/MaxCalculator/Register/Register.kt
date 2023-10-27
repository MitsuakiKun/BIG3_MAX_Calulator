package MaxCalculator.Register

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import MaxCalculator.helpers.exists
import MaxCalculator.helpers.write
import MaxCalculator.helpers.read
import MaxCalculator.models.GoalData
import java.util.ArrayList

class Register {
    val logger = KotlinLogging.logger {}
    val JSON_FILE = "goaldata.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<ArrayList<GoalData>>() {}.type

    var goaldata = mutableListOf<GoalData>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    fun findAll(): MutableList<GoalData> {
        return goaldata
    }

    fun findOne(username:String, kind: Int) : GoalData? {
        var foundGoal: GoalData? = goaldata.find { p -> p.username == username && p.kind == kind }
        return foundGoal
    }

    fun create(goal: GoalData) {
        var foundgoal = findOne(goal.username,goal.kind)
        if (foundgoal != null){
            foundgoal.goal = goal.goal
            foundgoal.date = goal.date
        }else{
            goaldata.add(goal)
        }
        println("Your registered a goal: "+goal.goal+"kg and "+goal.date)
        serialize()
        findAll()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(goaldata,listType)
        write(JSON_FILE, jsonString)
        goaldata.forEach {logger.info("${it}")}
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        goaldata = Gson().fromJson(jsonString, listType)
    }

    fun reset(username: String){
        goaldata.removeIf { it.username == username }
        serialize()
    }
}