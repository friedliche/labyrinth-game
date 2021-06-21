package creator.maze;

import java.util.ArrayList;
import java.util.Random;

import static helper.Constants.*;

public class BinaryTree{

    public static void on(Grid grid){
        Random ran = new Random();

        for (int row = 0; row < grid.getGridHeight(); row++){
            for ( int col = 0; col < grid.getGridWidth(); col++){

                Grid.Cell curCell = grid.getCellAt(col, row);
                ArrayList<Grid.Cell> neighbors = new ArrayList<>();

                // if not at wall or corner
                if (!(curCell.getNeighbor(N)==null))
                    neighbors.add(curCell.getNeighbor(N));
                if (!(curCell.getNeighbor(E)==null))
                    neighbors.add(curCell.getNeighbor(E));

                // the north-east corner has no linked neighbors
                if (neighbors.size() > 0) {
                    int index = ran.nextInt(neighbors.size());
                    Grid.Cell neighbor = neighbors.get(index);
                    curCell.link(neighbor);
                }
            }
        }
    }
}