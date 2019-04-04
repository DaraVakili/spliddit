//This code controls all inputs in the application
//03.02.18
//Dara Vakili & Laurence Pengelly
import java.util.Scanner;
import java.lang.Character;
public abstract class InputController {

final static String DATAFILE = "../Project.txt";

    private static Scanner scan = new Scanner(System.in);

    //-------------------------------------------------------
    //Underline scanned Ints and verify value
    //-------------------------------------------------------

    public static int scanUnderInt(int intLimit, int minimumInt) {

        int val = 0;

        do {
            System.out.print("\033[0;4m");
            if (scan.hasNextInt()) {
                val = scan.nextInt();
                if (val >= minimumInt && val <= intLimit) {
                    System.out.println("\033[0m");
                    break;
                }
            } else {
                scan.nextLine();
            }
            printUnder("\n\t\tERROR!!!!!!! Please enter whole number in the following range:  \t" + minimumInt + " - " + intLimit);
            System.out.print("\t\tTry again: ");
        } while (true);
        return val;
    }

    //-------------------------------------------------------
    //Underline scanned String and verify value
    //-------------------------------------------------------

    public static String scanUnderString(int maxString, int minString) {

        String val = "";

        do{
        System.out.print("\033[0;4m");
        val = scan.nextLine();
        System.out.println("\033[0m");
        if (val.length() > maxString || val.length() < minString) {
          printUnder("\n\t\tERROR!!!!!!! Enter text between " + minString + " and " + maxString + " in length: ");
        }
      } while (val.length() > maxString || val.length() < minString);
        return val;
    }

    //-------------------------------------------------------
    //Underline scanned char and verify value
    //-------------------------------------------------------

    public static char scanUnderChar(char[] a) {

        char val = '0';

        do {
        System.out.print("\033[0;4m");
        val = Character.toLowerCase(scan.next().charAt(0));
        System.out.println("\033[0m");
        if (!(new String(a).contains(Character.toString(val)))) {
          printUnder("\n\t\tERROR!!!!!!! Please enter correct option: ");
        }
      } while (!(new String(a).contains(Character.toString(val))));
        return val;
    }


    //-------------------------------------------------------
    //Print strings underlined
    //-------------------------------------------------------

    public static void printUnder(String line) {

      System.out.print("\033[0;4m");
      System.out.print(line);
      System.out.print("\033[0m");
    }

    //-------------------------------------------------------
    //****scanner wrappers so only 1 scan obj efficientcy****
    //-------------------------------------------------------

    public static boolean hasNextInt() {
      return scan.hasNextInt();
    }

    public static String nextLine() {
      return scan.nextLine();
    }
}
