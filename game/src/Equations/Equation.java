package Equations;

import ui.PowerLevel;

public class Equation {
    PowerLevel powerLevel;
    String equationToRender;
    
    public int firstNumber = 0;
    public int secondNumber = 0;
    public int thirdNumber = 0;
    public CalculationType firstCalculation = CalculationType.ADDITION;
    public CalculationType secondCalculation = CalculationType.ADDITION;

    private int result = 0;

    public Equation(PowerLevel powerLevel){
        this.powerLevel = powerLevel;
    }

    public void calcEquation() {
        int result = 0;

        if (thirdNumber == 0) {
            result = calcWhen2NumbersAreGiven();
        } else {
            result = calcWhen3NumbersAreGiven();
        }

        this.result = result;
    }


    private int calcWhen2NumbersAreGiven() {
        int result = 0;

        if (firstCalculation == CalculationType.ADDITION) {
            result = firstNumber + secondNumber;
        } else if (firstCalculation == CalculationType.SUBTRACTION) {
            result = firstNumber - secondNumber;
        } else if (firstCalculation == CalculationType.MULTIPLICATION) {
            result = firstNumber * secondNumber;
        } else if (firstCalculation == CalculationType.DIVISION) {
            result = firstNumber / secondNumber;
        }

        return result;
    }

    private int calcWhen3NumbersAreGiven() {
        int result = 0;

        if (firstCalculation == CalculationType.ADDITION) {
            result = firstNumber + secondNumber;
        } else if (firstCalculation == CalculationType.SUBTRACTION) {
            result = firstNumber - secondNumber;
        } else if (firstCalculation == CalculationType.MULTIPLICATION) {
            result = firstNumber * secondNumber;
        } else if (firstCalculation == CalculationType.DIVISION) {
            result = firstNumber / secondNumber;
        }

        if (secondCalculation == CalculationType.ADDITION) {
            result += thirdNumber;
        } else if (secondCalculation == CalculationType.SUBTRACTION) {
            result -= thirdNumber;
        } else if (secondCalculation == CalculationType.MULTIPLICATION) {
            result *= thirdNumber;
        } else if (secondCalculation == CalculationType.DIVISION) {
            result /= thirdNumber;
        }

        return result;
    }

    private String generateEquationToRenderWhen2 () {
        String equationToRender = "";

        if (firstCalculation == CalculationType.ADDITION) {
            equationToRender = firstNumber + " + " + secondNumber;
        } else if (firstCalculation == CalculationType.SUBTRACTION) {
            equationToRender = firstNumber + " - " + secondNumber;
        } else if (firstCalculation == CalculationType.MULTIPLICATION) {
            equationToRender = firstNumber + " * " + secondNumber;
        } else if (firstCalculation == CalculationType.DIVISION) {
            equationToRender = firstNumber + " / " + secondNumber;
        }

        return equationToRender;
    }

    private String generateEquationToRenderWhen3 () {
        String equationToRender = "";

        if (firstCalculation == CalculationType.ADDITION) {
            equationToRender = firstNumber + " + " + secondNumber;
        } else if (firstCalculation == CalculationType.SUBTRACTION) {
            equationToRender = firstNumber + " - " + secondNumber;
        } else if (firstCalculation == CalculationType.MULTIPLICATION) {
            equationToRender = firstNumber + " * " + secondNumber;
        } else if (firstCalculation == CalculationType.DIVISION) {
            equationToRender = firstNumber + " / " + secondNumber;
        }

        if (secondCalculation == CalculationType.ADDITION) {
            equationToRender += " + " + thirdNumber;
        } else if (secondCalculation == CalculationType.SUBTRACTION) {
            equationToRender += " - " + thirdNumber;
        } else if (secondCalculation == CalculationType.MULTIPLICATION) {
            equationToRender += " * " + thirdNumber;
        } else if (secondCalculation == CalculationType.DIVISION) {
            equationToRender += " / " + thirdNumber;
        }

        return equationToRender;
    }

    public String getEquationToRender() {
        if (thirdNumber == 0) {
            equationToRender = generateEquationToRenderWhen2();
        } else {
            equationToRender = generateEquationToRenderWhen3();
        }

        return equationToRender;
    }

    public boolean isResultLesserOrEqualThanPowerLevel() {
        // debug print
        System.out.println("firstNumber: " + firstNumber + " secondNumber: " + secondNumber + " thirdNumber: " + thirdNumber);
        System.out.println("firstCalculation: " + firstCalculation + " secondCalculation: " + secondCalculation);
        System.out.println("result: " + result + " powerLevel: " + powerLevel.getCurrentPowerLevel());

        return result <= powerLevel.getCurrentPowerLevel();
    }

    public int getResult() {
        return result;
    }
}
