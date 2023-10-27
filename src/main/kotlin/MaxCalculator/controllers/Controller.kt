package MaxCalculator.controllers

import mu.KotlinLogging
import MaxCalculator.Register.Register
import MaxCalculator.models.GoalData
import MaxCalculator.views.View
import MaxCalculator.views.LoginView

class Controller {

    val View = View()
    val register = Register()
    val logger = KotlinLogging.logger {}
    val LoginView = LoginView()
    lateinit var username:String


    init {
        logger.info { "Launching BIG3 Calculator App" }
        println("This a BIG3 calculator.\nYou can use this tool to upgrade your weight training.")
        println()
        println("Please select Resistor or Login your account.")
    }

    fun menu() :Int { return View.menu() }

    fun split(input:Int, username:String) {
            when(input) {
                1 -> View.calcBench(username)
                2 -> View.calcSquatDead(username)
                3 -> View.displayBenchChart()
                4 -> View.displaySquatDeadChart()
                5 -> create(username)
                6 -> reset(username)
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        if(input == -1)
            logger.info { "Shutting Down BIG3 Calculator App" }
    }


    fun start(){
        var result:String
        do{
            result = LoginView.firstView()
            username=result
            println("Welcome $username!")
        }while(result=="false")
        do {
            println()
            val input: Int = menu()
            split(input, username)
        }while (input != -1)
    }

    fun create(username: String){
        var data = GoalData()
        if (View.createData(data,username))
            register.create(data)
        else
            logger.info("Yor Goal does NOT Added")
    }

    fun reset(username: String) {
        if(View.reset()) {
            register.reset(username)
            logger.info("Your JSON FILE was reset.")
        }
    }
}