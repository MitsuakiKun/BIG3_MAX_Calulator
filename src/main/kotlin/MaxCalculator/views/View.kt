package MaxCalculator.views

import MaxCalculator.Calculate.MaxCalculate
import MaxCalculator.Calculate.DateCalculate
import MaxCalculator.models.GoalData


class View {
    val calculate = MaxCalculate()
    val dateCalculate = DateCalculate()

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Calculate BenchPress Max")
        println(" 2. Calculate Squat/DedLift Max")
        println(" 3. Display Bench RM Chart")
        println(" 4. Display Squat/DeadLift RM Chart")
        println(" 5. Register your goal")
        println(" 6. Reset Data")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readln()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun calcBench(username: String) {
        val weight: Double?
        val number: Double?

        println("Input your bench press weight you usually lift(kg). ")
        weight = readln().toDouble()

        println("Input your bench press number you lift. ")
        number = readln().toDouble()

        val MAX: Double = calculate.calcBench(weight,number)
        println("Your predicted bench press MAX weight is "+MAX+" kg.")
        val remainDate = dateCalculate.calculate(username,1,MAX)
        if(remainDate!= (-1).toLong()){
            println("You have "+ remainDate +" days to reach your goal.")
        }
    }

    fun calcSquatDead(username: String) {
        val weight: Double?
        val number: Double?
        val selected: Int

        println("1.Squat 2.Dead Lift")
        selected = readln().toInt()

        println("Input your squat/dead lift weight you usually lift(kg). ")
        weight = readln().toDouble()

        println("Input your squat/dead lift number you lift. ")
        number = readln().toDouble()

        val MAX: Double = calculate.calcSquatDead(weight,number).toInt().toDouble()

        println("Your predicted squat/dead lift MAX weight is "+ MAX +" kg.")
        val remainDate = dateCalculate.calculate(username,selected+1,MAX)
        if(remainDate!= (-1).toLong()){
            println("You have "+ remainDate +" days to reach your goal.")
        }

    }

    fun displayBenchChart(){
        println("weight RM1\t RM2\t RM3\t RM4\t RM5\t RM6\t RM7\t RM8\t RM9\t RM10")
        println("--------------------------------------------------------------------------------------")

        for (weight in 40..100 step 5) {
            print("$weight\t")
            for (number in 1..10) {
                val MAX = calculate.calcBench(weight.toDouble(),number.toDouble())
                print("%.2f\t".format(MAX))
            }
            println()
        }
    }

    fun displaySquatDeadChart(){
        println("weight RM1\t RM2\t RM3\t RM4\t RM5\t RM6\t RM7\t RM8\t RM9\t RM10")
        println("--------------------------------------------------------------------------------------")

        for (weight in 40..100 step 5) {
            print("$weight\t")
            for (number in 1..10) {
                val MAX = calculate.calcSquatDead(weight.toDouble(),number.toDouble())
                print("%.2f\t".format(MAX))
            }
            println()
        }
    }

    fun createData(data: GoalData,username: String):Boolean{
        data.username = username
        println()
        println("1.Bench Press 2.Squat 3.Dead Lift")
        data.kind = readln().toInt()!!
        if(data.kind != 1 && data.kind != 2 && data.kind != 3) {
            println("Error")
            return false
        }
        println("Enter a goal weight(kg)")
        data.goal = readln().toDouble()!!
        println("Enter the date you want to achieve(Year-Month-Day) ")
        data.date = readln()!!

        return true
    }

    fun reset() :Boolean{
        println("Are you sure to reset All data?\n1.Yes 2.No")
        var select = readln()!!
        if (select =="1"){
            return true
        }
        return false
    }

}