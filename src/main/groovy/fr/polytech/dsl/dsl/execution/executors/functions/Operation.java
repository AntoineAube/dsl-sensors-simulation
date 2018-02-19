package fr.polytech.dsl.dsl.execution.executors.functions;

public class Operation extends Expression{
    private OperationType type;
    private Expression left, right;

    public Operation(OperationType type, Expression left, Expression right){
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public double getValue(double variable) {
        switch (type){
            case ADD:
                return left.getValue(variable) + right.getValue(variable);
            case SUBSTRACT:
                return left.getValue(variable) - right.getValue(variable);
            case MULTIPLY:
                return left.getValue(variable) * right.getValue(variable);
            case DIVIDE:
                return left.getValue(variable) / right.getValue(variable);
            case POWER:
                return Math.pow(left.getValue(variable), right.getValue(variable));
        }
        return 0;
    }
}
