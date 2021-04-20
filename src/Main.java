import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/* Friendly reminder below: */
/* Rows: Vertical */
/* Columns: Horizontal */
/* Array: [row][column] */
/* Maths: (y, x) */ // IMPORTANT: This is the confusing part and requires getting used to
/* Labyrinth.findExitFrom(x, y) */
/* Displayed: (x, y) */ // Holy shit this is getting out of hand, 20.04.21 13:21

public final class Main {
    public static void main(String[] args) {
        /* Setup labyrinth from commandline argument */
        String filePath = null;
        if(args.length > 0) filePath = args[0];
        else {
            System.out.println("Missing filepath argument, correct use of program: java Main [filepath]");
            return;
        }
        Labyrinth labyrinth = null;
        try {labyrinth = new Labyrinth(new File(filePath));}
        catch(FileNotFoundException e) {e.printStackTrace();}
        /* User input section */
        Scanner input = new Scanner(System.in);
        System.out.println("Type in coordinates [x] [y] ('q' to quit)");
        String[] parsedInput = input.nextLine().split(" ");
        while (!parsedInput[0].equals("q")) {
            try {
                int x = Integer.parseInt(parsedInput[0]);
                int y = Integer.parseInt(parsedInput[1]);
                labyrinth.findExitFrom(x, y);
            } catch (NumberFormatException e) {System.out.println("Invalid input!");}         
            System.out.println("Type in coordinates [x] [y] ('q' to quit)");
            parsedInput = input.nextLine().split(" ");
        }
    }
}