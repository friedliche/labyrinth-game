package creator.maze;

import helper.Tuple;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static helper.Constants.*;


public class Grid extends JComponent {

    private int width, height;
    private Cell[][] grid;
    private Random random;

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        prepareGrid();

        configureCells();
        random = new Random();
    }

    public void prepareGrid(){
        grid = new Cell[width][height];
        for (int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                grid[col][row] = new Cell(col, row);
            }
        }
    }

    public void configureCells(){
        for (int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                if (row - 1 >= 0)
                    grid[col][row].setNeighbor(grid[col][row-1], N);
                if (row + 1 < height)
                    grid[col][row].setNeighbor(grid[col][row+1], S);
                if (col - 1 >= 0)
                    grid[col][row].setNeighbor(grid[col-1][row], W);
                if (col + 1 < width)
                    grid[col][row].setNeighbor(grid[col+1][row], E);
            }
        }
    }

    public int getGridHeight(){
        return this.height;
    }

    public int getGridWidth(){
        return this.width;
    }

    public Cell getCellAt(int col, int row){
        return this.grid[col][row];
    }


    public Cell getRandomCell(){
        int rndRow = random.nextInt(this.height);
        int rndCol = random.nextInt(this.width);
        return grid[rndCol][rndRow];
    }

    public String contentsOf(Cell cell){
        return " ";
    }

    @Override
    public String toString(){

        StringBuilder out = new StringBuilder("+").append("---+".repeat(width)).append("\n");

        for(int row = 0; row < height; row++){
            StringBuilder top = new StringBuilder("|");
            StringBuilder bottom = new StringBuilder("+");

            for(int col = 0; col < width; col++){
                Cell curCell = getCellAt(col, row);
                if (curCell == null) {
                    System.err.println("This cell is not initialized");
                    System.exit(1);
                }

                String body = " " + contentsOf(curCell) + " ";
                String eastBoundary = ((curCell.isLinked(curCell.getNeighbor(E))) ? " " : "|");
                top.append(body).append(eastBoundary);

                String southBoundary = ((curCell.isLinked(curCell.getNeighbor(S))) ? "   " : "---");
                String corner = "+";
                bottom.append(southBoundary).append(corner);
            }
            out.append(top).append("\n");
            out.append(bottom).append("\n");
        }
        return out.toString();
    }

    public void toPNG(){
        int cellSize = 10;
        int widthPNG = cellSize * this.getGridWidth();
        int heightPNG = cellSize * this.getGridHeight();

        BufferedImage bufferedImage = new BufferedImage(widthPNG + 1, heightPNG + 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill background white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, widthPNG + 1, heightPNG + 1);

        // draw lines in black
        g2d.setColor(Color.black);
        for(int row = 0; row < height; row++){
            int y1 = row * cellSize;
            int y2 = (row + 1) * cellSize;

            for(int col = 0; col < width; col++){
                int x1 = col * cellSize;
                int x2 = (col + 1) * cellSize;

                Cell currCell = getCellAt(col, row);

                if (!(currCell.isLinked(currCell.getNeighbor(W))))
                    g2d.drawLine(x1, y1, x1, y2);

                if (!(currCell.isLinked(currCell.getNeighbor(N))))
                    g2d.drawLine(x1, y1, x2, y1);

                if (!(currCell.isLinked(currCell.getNeighbor(S))))
                    g2d.drawLine(x1, y2, x2, y2);

                if (!(currCell.isLinked(currCell.getNeighbor(E))))
                    g2d.drawLine(x2, y1, x2, y2);
            }
        }

        g2d.dispose();

        File file = new File("figs/maze.png");
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isLinkIn(Tuple<Integer, Integer> currentPlayerPosition, int direction){
        int x = currentPlayerPosition.getX();
        int y = currentPlayerPosition.getY();

        Cell currentCell = this.getCellAt(x, y);

        return currentCell.isLinked(currentCell.getNeighbor(direction));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int upShift = 0;

        int cellSize = 60; // size of wall lift/above & right/bottom included: 30
        int halfWallSize = 15;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        for(int row = 0; row < this.height; row++){
            int y1 = row * cellSize;
            int y2 = (row + 1) * cellSize;

            int leftShift = 0;

            for(int col = 0; col < this.width; col++){
                int x1 = col * cellSize;
                int x2 = (col + 1) * cellSize;

                Grid.Cell currCell = this.getCellAt(col, row);

                if (!(currCell.isLinked(currCell.getNeighbor(W))))
                    g2d.fill(new Rectangle2D.Double(x1 - leftShift, y1 - upShift, halfWallSize, cellSize));

                if (!(currCell.isLinked(currCell.getNeighbor(N))))
                    g2d.fill(new Rectangle2D.Double(x1 - leftShift, y1 - upShift, cellSize, halfWallSize));

                if (!(currCell.isLinked(currCell.getNeighbor(S))))
                    g2d.fill(new Rectangle2D.Double(x1  - leftShift, y2 - upShift - 15, cellSize, halfWallSize));

                if (!(currCell.isLinked(currCell.getNeighbor(E))))
                    g2d.fill(new Rectangle2D.Double(x2 - leftShift - 15, y1 - upShift, halfWallSize, cellSize));

                leftShift += 15;
            }
            upShift += 15;
        }
        g2d.dispose();
    }

    public Dimension getPreferredSize(){
        return new Dimension(1000, 500);
    }

    public class Cell {

        private Cell[] neighbors; // N, S, E, W
        private ArrayList<Cell> linkedCells; // cells, to which passage exists
        private int row, col;

        public Cell(int col, int row) {
            neighbors = new Cell[4];
            linkedCells = new ArrayList<>();
            this.row = row;
            this.col = col;
        }

        public Tuple<Integer, Integer> getPosition(){
            return new Tuple<>(col, row);
        }

        public ArrayList<Cell> getLinkedCells() {
            return linkedCells;
        }

        public void setNeighbor(Cell cell, int dir) {
            if (neighbors[dir] == null)
            neighbors[dir] = cell;
        }

        public Cell getNeighbor(int dir) {
            return neighbors[dir];
        }

        public Cell link(Cell cell) {
            return link(cell, true);
        }

        public Cell link(Cell cell, boolean bidi) {
            linkedCells.add(cell);
            if (bidi) {
                cell.link(this, false);
            }
            return this;
        }

        public Cell unlink(Cell cell) {
            return unlink(cell, true);
        }

        public Cell unlink(Cell cell, boolean bidi) {
            linkedCells.remove(cell);
            if (bidi) {
                cell.unlink(this, false);
            }
            return this;
        }

        public boolean isLinked(Cell cell) {
            return linkedCells.contains(cell);
        }

        /**
         *
         * @return Instance of Distances describing distances to all cells relative from this cell.
         */
        public Distances getDistances(){
            Distances distances = new Distances(this);

            ArrayList<Cell> frontier = new ArrayList<>();
            frontier.add(this);

            while(!frontier.isEmpty()) {
                ArrayList<Cell> newFrontier = new ArrayList<>();

                for (Cell cell: frontier) {
                    for (Cell linked: cell.linkedCells){
                        // if not already visited
                        if (distances.getDistanceFromRootTo(linked) < 0) {
                            distances.setDistanceFromRootTo(linked, distances.getDistanceFromRootTo(cell) + 1);
                            newFrontier.add(linked);
                        }
                    }
                }
                frontier = newFrontier;
            }

            return distances;
        }
    }
}