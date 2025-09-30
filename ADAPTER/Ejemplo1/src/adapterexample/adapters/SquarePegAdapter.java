package adapterexample.adapters;

import adapterexample.round.RoundPeg;
import adapterexample.square.SquarePeg;

public class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        // radio mínimo del círculo que puede contener al cuadrado
        return Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2);
    }
}
