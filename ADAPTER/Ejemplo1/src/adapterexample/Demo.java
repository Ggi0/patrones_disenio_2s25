package adapterexample;

import adapterexample.adapters.SquarePegAdapter;
import adapterexample.round.RoundHole;
import adapterexample.round.RoundPeg;
import adapterexample.square.SquarePeg;

public class Demo {
    public static void main(String[] args) {
        // Agujero redondo de radio 5
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);

        // Usamos adaptadores
        SquarePegAdapter smallAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeAdapter = new SquarePegAdapter(largeSqPeg);

        if (hole.fits(smallAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
