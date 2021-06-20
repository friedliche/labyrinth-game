package creator.maze;

import helper.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Distances {

    Grid.Cell root;
    Map<Grid.Cell, Integer> cells;

    public Distances(Grid.Cell root){
        this.root = root;
        this.cells = new HashMap<>();
        this.cells.put(root, 0); // distance to itself is 0
    }

    public int getDistanceFromRootTo(Grid.Cell cell){
        if (cells.containsKey(cell)) {
            return cells.get(cell);
        }else{
            return -1;
        }
    }

    public void setDistanceFromRootTo(Grid.Cell cell, int distance){
        cells.put(cell, distance);
    }

    /**
     *  Return all keys in cells.
     */
    public Set<Grid.Cell> getCells(){
        return this.cells.keySet();
    }

    public Distances pathTo(Grid.Cell goal){
        Grid.Cell current = goal;

        Distances path = new Distances(this.root);
        path.setDistanceFromRootTo(current, this.cells.get(current));

        while (current != this.root){
            ArrayList<Grid.Cell> linkedCells = current.getLinkedCells();
            for(Grid.Cell linkedCell: linkedCells){
                if (this.cells.get(linkedCell) < this.cells.get(current)){
                    path.setDistanceFromRootTo(linkedCell, this.cells.get(linkedCell));
                    current = linkedCell;
                    break;
                }
            }
        }
        return path;
    }

    /**
     * Identify cell with largest distance from root.
     *
     * @return the cell and distance
     */
    public Tuple<Grid.Cell, Integer> max(){
        int maxDistance = 0;
        Grid.Cell maxCell = this.root;

        for(Grid.Cell cell : this.getCells()){
            int distance = this.cells.get(cell);
            if (distance > maxDistance) {
                maxCell = cell;
                maxDistance = distance;
            }
        }
        return new Tuple<>(maxCell, maxDistance);
    }
}
