package Calc.MementoPattern;

public class Originator {

    private double state;

    public void setState(double state) {
        this.state = state;
    }

    public double getState() {
        return state;
    }

    public Memento saveState() {
        return new Memento(state);
    }

    public void restoreState(Memento memento) {
        this.state = memento.getState();
    }
}
