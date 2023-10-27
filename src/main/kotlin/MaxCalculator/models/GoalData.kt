package MaxCalculator.models

import java.time.LocalDate


data class GoalData(
    var username: String="",
    var kind: Int = 0,
    var goal: Double = 0.0,
    var date: String = "2023-01-01",
 )