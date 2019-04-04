//This code initialises the file writer. At the moment only used to save projects as a txt file
//15.03.18
//Dara Vakili & Laurence Pengelly

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

final static String DATAFILE = "../Project.txt";
    //-------------------------------------------------------
    //save projects to txt file. Save Verification Placed in
    //this class as writer currently only used for save data.
    //If writer needs to be used in other context, the class
    //will change
    //-------------------------------------------------------

    public static void saveProjects(ArrayList<Project> projects) {

      String allProjects = "";

      for (Project p : projects) {
        allProjects += p.toString() + "\n";
      }
      try {
        FileWriter fw = new FileWriter(DATAFILE, false); // false, want to limit saves
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(allProjects);
        pw.flush();
        pw.close();
        System.out.println("\t\tSuccessfully Saved");
    } catch (Exception E) {System.out.println("Error, please give this report to support: \n" + E);}
    }

    //-------------------------------------------------------
    //File .txt io reader. Var line uses \n for each line
    //-------------------------------------------------------
    public static String fileReader(String path) {

      String line = "", full = "";

      try {
          FileReader OpenAbout = new FileReader(path);
          BufferedReader reader = new BufferedReader(OpenAbout);

          while ((line = reader.readLine()) != null) {
            full += ("\n" + line);
          }
          reader.close();
      } catch (IOException e) {
          InputController.printUnder("\t\tError!!!!!! .txt file does not exist or path is incorrect. \n\t\t\tPlease send this report to support: \n\n");
          e.printStackTrace();
      }
      return full;
    }
    //-------------------------------------------------------
    //Load txt file and add to objects. Print projects loading
    //-------------------------------------------------------

    public static ArrayList<Project> loadFile(ArrayList<Project> existingProjects) {

      ArrayList<Project> projects = new ArrayList<Project>();
      String objectData[];
      String loadData[] = fileReader(DATAFILE).split("\n");

      outer:
      for (int i = 1; i < loadData.length; i++) {
        objectData = loadData[i].split(",");
        for (Project p : existingProjects) {
          if (p.getName().equals(objectData[0])) continue outer;
        }
        Project newProj = new Project(objectData);
        projects.add(newProj);
      }
      System.out.println("Loading the following projects: ");
      Project.printProjectList(projects);
      return projects;
    }
}
