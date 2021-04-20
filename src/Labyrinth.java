import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public final class Labyrinth {
    private Tile[][] tiles;
    private final int rows;
    private final int columns;
    private ArrayList<ArrayList<Tuple>> exitPaths = new ArrayList<ArrayList<Tuple>>();

    /* Standard read from file into 2D-array */
    Labyrinth(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        rows = scanner.nextInt();
        columns = scanner.nextInt();
        tiles = new Tile[rows][columns];
        for(int row = 0; row < rows; row++) {
            char[] charArray = scanner.next().toCharArray();
            for(int column = 0; column < columns; column++) {
                /* Assigns regular tiles */
                switch(charArray[column]) {
                    case '#' -> tiles[row][column] = new Wall(row, column, this);
                    case '.' -> tiles[row][column] = new Path(row, column, this);
                }
                /* Assigns openings */
                if(charArray[column] == '.') if(column == 0 || column == columns - 1 || row == 0 || row == rows - 1) tiles[row][column] = new Opening(row, column, this);
                /* Assigns horizontal neighbours */
                Tile temp = tiles[row][column];
                if(column != 0) {
                    temp.setWest(tiles[row][column - 1]);
                    temp.getWest().setEast(temp);
                }
                /* Assigns vertical neighbours */
                if(row != 0) {
                    temp.setNorth(tiles[row - 1][column]);
                    temp.getNorth().setSouth(temp);
                }
            }
        }
    }

    public void addExitPath(ArrayList<Tuple> path) {exitPaths.add(path);}

    public void findExitFrom(int x, int y) {
        exitPaths = new ArrayList<ArrayList<Tuple>>(); // Clear the paths we had saved
        tiles[y][x].findExit();
        System.out.println("Paths:");
        /* Lambda functions are fun :) */
        exitPaths.forEach((paths) -> {paths.forEach((tuple) -> System.out.println(tuple)); System.out.println();});
    }

    public String toString() {
        String temp = "";
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {temp += tiles[row][column].toChar();}
            if(row != rows - 1) temp += "\n";
        }
        return temp;
    }
}