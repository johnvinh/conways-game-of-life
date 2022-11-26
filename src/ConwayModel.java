/**
 * @author John
 * @version 1
 * The MVC architecture model for Conway's Game of Life.
 */
public class ConwayModel {
    /**
     * Represents the square dimensions of the simulation
     */
    private int dim;

    /**
     * Class constructor.
     * @param dim   the square dimensions of the simulation
     */
    public ConwayModel(int dim) {
        this.dim = dim;
    }

    /**
     * Gets the square dimensions of the simulation.
     * @return  a number representing the square dimensions
     */
    public int getDim() {
        return dim;
    }

    /**
     * Sets the square dimensions of the simulation.
     * @param dim   a number representing the new square dimensions
     */
    public void setDim(int dim) {
        this.dim = dim;
    }
}
