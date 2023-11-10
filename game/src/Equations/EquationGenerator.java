package Equations;

import java.util.LinkedList;

import Interface.PowerLevel;

public class EquationGenerator {
    PowerLevel powerLevel;

    LinkedList<Equation> equations = new LinkedList<Equation>();

    double percentOfAddition = 0.25;
    double percentOfSubtraction = 0.25;
    double percentOfMultiplication = 0.25;
    double percentOfDivision = 0.25;

    double percentOf2Numbers = 0.5;
    double percentOf3Numbers = 0.5;

    double percentOfEquationUnderPowerLevel = 0.5;
    double percentOfEquationOverPowerLevel = 0.5;

    public EquationGenerator(PowerLevel powerLevel) {
        this.powerLevel = powerLevel;
    }

    public Equation generateEquation() {
        Equation equation = new Equation(powerLevel);
        int firstNumber = getRandomNumberInPowerLevelRange();
        int secondNumber = getRandomNumberInPowerLevelRange();
        int thirdNumber = getRandomNumberInPowerLevelRange();

        equation.firstNumber = firstNumber;
        equation.secondNumber = secondNumber;
        equation.thirdNumber = thirdNumber;

        equation.firstCalculation = CalculationType.values()[(int) getRandomNumber(0, 4)];
        equation.secondCalculation = CalculationType.values()[(int) getRandomNumber(0, 4)];

        equation.calcEquation();
        return equation;
    }
    
    private int getRandomNumberInPowerLevelRange() {
        int powerLevel = this.powerLevel.getCurrentPowerLevel();
        int min = 1;
        int max = (int) powerLevel / 10;
        return (int) getRandomNumber(min, max);
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
