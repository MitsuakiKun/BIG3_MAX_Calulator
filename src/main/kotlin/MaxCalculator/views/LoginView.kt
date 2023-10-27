package MaxCalculator.views

import MaxCalculator.helpers.UserDataManager

class LoginView {
    fun firstView() :String{
        val userManager = UserDataManager("users.json")

        while (true) {
            println("1. Register")
            println("2. Login")
            println("3. Exit")
            print("Select an option: ")

            when (readLine()) {
                "1" -> {
                    print("Enter username: ")
                    val username = readLine() ?: ""
                    print("Enter password: ")
                    val password = readLine() ?: ""
                    if (userManager.registerUser(username, password)) {
                        println("User registered successfully.")
                        return username
                    } else {
                        println("Username already exists. Please choose another username.")
                        return "false"
                    }
                }
                "2" -> {
                    print("Enter username: ")
                    val username = readLine() ?: ""
                    print("Enter password: ")
                    val password = readLine() ?: ""
                    if (userManager.loginUser(username, password)) {
                        println("Login successful.")
                        return username
                    } else {
                        println("Invalid username or password. Please try again.")
                        return "false"
                    }
                }
                "3" -> {
                    println("Goodbye!")
                    return "false"
                }
                else -> {
                    println("Invalid option. Please try again.")
                    return "false"
                }
            }
        }
    }
}