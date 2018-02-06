package EnumExercise.MathOperators;

public class IntMath {

    public int calculate(MathOperator operator, int operand1, int operand2){
        switch(operator){
            case ADD:
                return operand1 + operand2;
            case SUBTRACT:
                return operand1 - operand2;
            case DIVIDE:
                return operand1 / operand2;
            case MULTIPLY:
                return operand1 * operand2;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
