package Stars;

import Planets.Planet;

import java.awt.*;

public class Sun extends Planet {
    public Sun(double x, int y) {
        super(x, y, 1000, 50, Color.YELLOW);
    }
}
