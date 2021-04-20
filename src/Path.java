import java.util.ArrayList;

public class Path extends Tile {
    Path(int row, int column, Labyrinth labyrinth) {super(row, column, labyrinth);}

    public char toChar() {return '.';}

    public void walk(Tile excludedTile, ArrayList<Tuple> path) {
        ArrayList<Tuple> newPath = new ArrayList<Tuple>(path);
        newPath.add(new Tuple(column, row));
        if(!north.equals(excludedTile)) {north.walk(this, newPath);}
        if(!east.equals(excludedTile)) {east.walk(this, newPath);}
        if(!south.equals(excludedTile)) {south.walk(this, newPath);}
        if(!west.equals(excludedTile)) {west.walk(this, newPath);}
    }
}