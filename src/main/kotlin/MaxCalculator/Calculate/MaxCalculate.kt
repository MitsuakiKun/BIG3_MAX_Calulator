package MaxCalculator.Calculate

class MaxCalculate {
    fun calcBench(weight:Double,number:Double):Double {

        val MAX: Double = (weight * number)/40+weight

        return MAX
    }

    fun calcSquatDead(weight:Double,number:Double):Double{

        val MAX: Double = (weight * number)/33.3+weight

        return MAX
    }
}