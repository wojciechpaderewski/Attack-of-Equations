package Equations;

import java.util.LinkedList;

import ui.PowerLevel;

public class EquationGenerator {
    PowerLevel powerLevel;

    LinkedList<Equation> equations = new LinkedList<Equation>();

    double percentOfAddition = 0.35;
    double percentOfSubtraction = 0.15;
    double percentOfMultiplication = 0.2;
    double percentOfDivision = 0.1;

    double percentOf2Numbers = 0.7;
    double percentOf3Numbers = 0.3;

    double percentOfEquationUnderPowerLevel = 0.25;
    double percentOfEquationOverPowerLevel = 0.75;

    double randomNumberDivider = 0.5;

    public EquationGenerator(PowerLevel powerLevel) {
        this.powerLevel = powerLevel;
    }

    private double getPercentOfEquationUnderPowerLevel() {
        int allEquations = equations.size();
        int underPowerLevel = 0;
        for (Equation equation : equations) {
            if (equation.getResult() < powerLevel.getCurrentPowerLevel()) {
                underPowerLevel++;
            }
        }

        return (double) underPowerLevel / allEquations;
    }
        
    public Equation generateEquation() {
        Equation equation;

        if (getPercentOfEquationUnderPowerLevel() < percentOfEquationUnderPowerLevel) {
            equation = generateEquationUnderPowerLevel();
        } else {
            equation = generateEquationOverPowerLevel();
        }

        equations.add(equation);
        equation.calcEquation();

        return equation;
    }

    private Equation generateEquationUnderPowerLevel() {
        Equation equation = new Equation(powerLevel);

        if (getPercentOf2NumbersEquations() < percentOf2Numbers) {
            equation.firstNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel());
            equation.secondNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel());
            equation.thirdNumber = 0;
        } else {
            equation.firstNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel());
            equation.secondNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel());
            equation.thirdNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel());
        }

        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.firstCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.firstCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.firstCalculation = CalculationType.MULTIPLICATION;
        } else if (getPercentOfCalculationType(CalculationType.DIVISION) < percentOfDivision) {
            equation.firstCalculation = CalculationType.DIVISION;
        }

        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.secondCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.secondCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.secondCalculation = CalculationType.MULTIPLICATION;
        } else if (getPercentOfCalculationType(CalculationType.DIVISION) < percentOfDivision) {
            equation.secondCalculation = CalculationType.DIVISION;
        }

        return equation;
    }

    private Equation generateEquationOverPowerLevel() {
        Equation equation = new Equation(powerLevel);

        if (getPercentOf2NumbersEquations() < percentOf2Numbers) {
            equation.firstNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel() + powerLevel.getCurrentPowerLevel());
            equation.secondNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel() + powerLevel.getCurrentPowerLevel());
            equation.thirdNumber = 0;
        } else {
            equation.firstNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel() + powerLevel.getCurrentPowerLevel());
            equation.secondNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel() + powerLevel.getCurrentPowerLevel());
            equation.thirdNumber = (int) (Math.random() * randomNumberDivider * powerLevel.getCurrentPowerLevel() + powerLevel.getCurrentPowerLevel());
        }

        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.firstCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.firstCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.firstCalculation = CalculationType.MULTIPLICATION;
        } else if (getPercentOfCalculationType(CalculationType.DIVISION) < percentOfDivision) {
            equation.firstCalculation = CalculationType.DIVISION;
        }

        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.secondCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.secondCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.secondCalculation = CalculationType.MULTIPLICATION;
        } else if (getPercentOfCalculationType(CalculationType.DIVISION) < percentOfDivision) {
            equation.secondCalculation = CalculationType.DIVISION;
        }

        return equation;
    }

    private double getPercentOfCalculationType(CalculationType calculationType) {
        int allEquations = equations.size();
        int calculationTypeCount = 0;
        for (Equation equation : equations) {
            if (equation.firstCalculation == calculationType) {
                calculationTypeCount++;
            }
            if (equation.secondCalculation == calculationType) {
                calculationTypeCount++;
            }
        }

        return (double) calculationTypeCount / allEquations;
    }

    private double getPercentOf2NumbersEquations() {
        int allEquations = equations.size();
        int twoNumbers = 0;
        for (Equation equation : equations) {
            if (equation.thirdNumber == 0) {
                twoNumbers++;
            }
        }

        return (double) twoNumbers / allEquations;
    }

}
