package Equations;

import Interface.PowerLevel;

public class EquationGenerator {
    PowerLevel powerLevel;

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
