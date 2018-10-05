package Calc.MementoPattern;

public class Memento {

    private final double state;

    public Memento(double state) {
        this.state = state;
    }

    public double getState() {
        return state;
    }


}
