package creator.maze;

import static java.lang.Character.MAX_RADIX;

public class DistanceGrid extends Grid{

    Distances distances;

    public DistanceGrid(int width, int height) {
        super(width, height);
    }

    public void setDistances(Distances distances){
        this.distances = distances;
    }

    @Override
    public String contentsOf(Cell cell){
        if (distances != null && distances.getDistanceFromRootTo(cell) >= 0){
            return Integer.toString(distances.getDistanceFromRootTo(cell), MAX_RADIX);
        }else{
            return super.contentsOf(cell);
        }
    }
}
