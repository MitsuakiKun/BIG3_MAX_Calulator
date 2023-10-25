package org.setu.placemark.console.controllers

import mu.KotlinLogging
import org.setu.placemark.console.Register.Register
import org.setu.placemark.console.models.GoalData
import org.setu.placemark.console.views.View

class Controller {

    val View = View()
    val register = Register()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("This a BIG3 calculator.\nYou can use this tool to upgrade your weight training.")
    }

    fun menu() :Int { return View.menu() }

    fun split(input:Int) {
            when(input) {
                1 -> View.calcBench()
                2 -> View.calcSquatDead()
                3 -> View.displayBenchChart()
                4 -> View.displaySquatDeadChart()
                5 -> create()
                6 -> reset()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        if(input == -1)
            logger.info { "Shutting Down Placemark Console App" }
    }


    fun start(){
        do {
            val input: Int = menu()
            split(input)
        }while (input != -1)
    }

    fun create(){
        var data = GoalData()

        if (View.createData(data))
            register.create(data)
        else
            logger.info("Yor Goal Not Added")
    }

    fun reset() {
        if(View.reset()) {
            register.reset()
            logger.info("Your JSON FILE was reset.")
        }
    }
}