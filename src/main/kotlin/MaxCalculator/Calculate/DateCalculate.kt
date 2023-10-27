package MaxCalculator.Calculate

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import MaxCalculator.helpers.exists
import MaxCalculator.models.GoalData
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.time.temporal.ChronoUnit

class DateCalculate {
    val logger = KotlinLogging.logger {}
    var goaldata = mutableListOf<GoalData>()
    val JSON_FILE = "goaldata.json"
    val listType = object : TypeToken<ArrayList<GoalData>>() {}.type


    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    fun calculate(username: String,kind: Int, MAX: Double):Long{
        var foundgoal = findOne(username,kind)
        if (foundgoal != null) {
            var goalDate = foundgoal.date
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(goalDate, formatter)
            val now = LocalDate.now()

            val daysUntilGoal = ChronoUnit.DAYS.between(now, date)

            val remain= foundgoal.goal-MAX
            if(remain < 0)
                println("Your goal was completed!")
            else
                println("Your lifting weight remain "+remain+" kg.")

            return daysUntilGoal
        } else {
            return -1
        }
    }

    fun read(path: String): String {
        val file = File(path)
        try {
            val lines =
                mutableListOf<String>()
            file.
            forEachLine { lines.add(it) }
            return lines.
            joinToString(separator = "\n")
        } catch (e: FileNotFoundException) {
            logger.error { "Cannot Find file: " + e.toString() }
        } catch (e: IOException) {
            logger.error { "Cannot Read file: " + e.toString() }
        }
        return "{}"
    }


    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        goaldata = Gson().fromJson(jsonString, listType)
    }

    fun findOne(username: String, kind: Int) : GoalData? {
        var foundGoal: GoalData? = goaldata.find { p -> p.username == username && p.kind == kind }
        return foundGoal
    }
}