package Equations;

import java.util.LinkedList;

import ui.Score;

public class EquationGenerator {
    Score score;

    LinkedList<Equation> equations = new LinkedList<Equation>();

    double percentOfAddition = 0.50;
    double percentOfSubtraction = 0.20;
    double percentOfMultiplication = 0.30;

    double percentOf2Numbers = 0.7;
    double percentOf3Numbers = 0.3;

    double percentOfEquationUnderPowerLevel = 0.35;
    double percentOfEquationOverPowerLevel = 0.65;


    public EquationGenerator(Score score) {
        this.score = score;
    }

    private double getPercentOfEquationUnderPowerLevel() {
        int allEquations = 0;
        for (Equation equation : equations) {
            if (equation == null) {
                continue;
            }
            allEquations++;
        }
        int underPowerLevel = 0;
        for (Equation equation : equations) {
            if (equation.getResult() < score.getCurrentScore()) {
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
        int maxIterations = 35;
        int expetedResult = getRandomNumber(1, score.getCurrentScore());
        Equation nearestEquation = null;
        
        for (int i = 0; i < maxIterations; i++) {
            Equation equation = new Equation(score);

            if (getPercentOf2NumbersEquations() < percentOf2Numbers) {
                int divider = getRandomNumber(1, (int) score.getCurrentScore() / 2);
                equation.firstNumber = getRandomNumber(1, (int) score.getCurrentScore() / divider);
                equation.secondNumber = getRandomNumber(1, (int) score.getCurrentScore() / divider);
                equation.thirdNumber = 0;
            } else {
                int divider = getRandomNumber(1, (int) score.getCurrentScore() / 3);
                equation.firstNumber = getRandomNumber(1, (int) score.getCurrentScore() / divider);
                equation.secondNumber = getRandomNumber(1, (int) score.getCurrentScore() / divider);
                equation.thirdNumber = getRandomNumber(1, (int) score.getCurrentScore() / divider);
            }

            setCalulationType(equation);
            equation.calcEquation();

            if (equation.getResult() == expetedResult) {
                return equation;
            }

            if (equation.getResult() < 0) {
                continue;
            }

            if (equation.getResult() < score.getCurrentScore()) {
                if (nearestEquation == null) {
                    nearestEquation = equation;
                } else if (Math.abs(equation.getResult() - expetedResult) < Math.abs(nearestEquation.getResult() - expetedResult)) {
                    nearestEquation = equation;
                }
            }

            if (i == maxIterations - 1 && nearestEquation == null) {
                maxIterations++;
            }
        }

        if (nearestEquation == null) {
            nearestEquation = getDefultEquation(nearestEquation);
        }

        return nearestEquation;
    }

    private Equation generateEquationOverPowerLevel() {
        int maxIterations = 15;
        int expetedResult = getRandomNumber(score.getCurrentScore() + 1, 2 * score.getCurrentScore());
        Equation nearestEquation = null;
        
        for (int i = 0; i < maxIterations; i++) {
            Equation equation = new Equation(score);

            if (getPercentOf2NumbersEquations() < percentOf2Numbers) {
                int divider = getRandomNumber(1, 2);
                equation.firstNumber = getRandomNumber((int) score.getCurrentScore() / divider, 2 * (int) score.getCurrentScore() / divider);
                equation.secondNumber = getRandomNumber((int) score.getCurrentScore() / divider, 2 * (int) score.getCurrentScore() / divider);
                equation.thirdNumber = 0;
            } else {
                int divider = getRandomNumber(1, 3);
                equation.firstNumber = getRandomNumber((int) score.getCurrentScore() / divider, 2 * (int) score.getCurrentScore() / divider);
                equation.secondNumber = getRandomNumber((int) score.getCurrentScore() / divider, 2 * (int) score.getCurrentScore() / divider);
                equation.thirdNumber = getRandomNumber((int) score.getCurrentScore() / divider, 2 * (int) score.getCurrentScore() / divider);
            }

            setCalulationType(equation);
            equation.calcEquation();

            if (equation.getResult() == expetedResult) {
                return equation;
            }

            if (equation.getResult() < 0) {
                continue;
            }

            if (equation.getResult() > score.getCurrentScore()) {
                if (nearestEquation == null) {
                    nearestEquation = equation;
                } else if (Math.abs(equation.getResult() - expetedResult) < Math.abs(nearestEquation.getResult() - expetedResult)) {
                    nearestEquation = equation;
                }
            }

        }
        
        if (nearestEquation == null) {
            nearestEquation = getDefultEquation(nearestEquation);
        }

        return nearestEquation;
    }

    private Equation getDefultEquation(Equation equation) {
        Equation defultEquation = new Equation(score);
        defultEquation.firstNumber = 1;
        defultEquation.secondNumber = 1;
        defultEquation.thirdNumber = 0;
        defultEquation.firstCalculation = CalculationType.SUBTRACTION;
        return defultEquation;
    }

    private double getPercentOfCalculationType(CalculationType calculationType) {
        int calculationTypeCount = 0;
        int allEquations = 0;
        for (Equation equation : equations) {
            if (equation == null) {
                continue;
            }
            allEquations++;
        }
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
        int allEquations = 0;
        for (Equation equation : equations) {
            if (equation == null) {
                continue;
            }
            allEquations++;
        }
        int twoNumbers = 0;
        for (Equation equation : equations) {
            if (equation.thirdNumber == 0) {
                twoNumbers++;
            }
        }

        return (double) twoNumbers / allEquations;
    }

    private void setCalulationType(Equation equation) {
        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.firstCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.firstCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.firstCalculation = CalculationType.MULTIPLICATION;
        }

        if (getPercentOfCalculationType(CalculationType.ADDITION) < percentOfAddition) {
            equation.secondCalculation = CalculationType.ADDITION;
        } else if (getPercentOfCalculationType(CalculationType.SUBTRACTION) < percentOfSubtraction) {
            equation.secondCalculation = CalculationType.SUBTRACTION;
        } else if (getPercentOfCalculationType(CalculationType.MULTIPLICATION) < percentOfMultiplication) {
            equation.secondCalculation = CalculationType.MULTIPLICATION;
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void removeEquation(Equation equation) {
        equations.remove(equation);
    }
}
