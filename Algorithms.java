//The algorithms used for the different parts of spliddit's functionality are stored here
//06.02.18
//Dara Vakili & Laurence Pengelly
import java.util.ArrayList;
import java.util.HashMap;

public class Algorithms {

  //-------------------------------------------------------
  //Method makes sure at least 1 project exists, then it
  //points to the various algorithms
  //-------------------------------------------------------

  public static void sorter(ArrayList<Project> projects) {

    if (projects.size() < 1) {
      System.out.println("\t\tERROR!!! Please either load projects or make a new one");
      InputController.printUnder("\t\tLoading main menu");
      return;
    }

    System.out.println("\t\tSelect the project by number:");
    Project.printProjectList(projects);
    System.out.print("\t\tConfirm choice: ");
    Project p = projects.get((InputController.scanUnderInt(projects.size(), 1)-1));

    if (p.getNoTeamMembers() > 3) {
      fourMoreTeamAlgo(p);
    } else if (p.getNoTeamMembers() == 3) {
      threeTeamAlgo(p);
    } else {
      System.out.println("\t\tERROR!!!! please contact support");
    }
  }

  //-------------------------------------------------------
  //Algorithms to print results
  //-------------------------------------------------------

  public static void threeTeamAlgo(Project p) {

    ArrayList<Integer[]> matrix = new ArrayList<Integer[]>();
    //use the existing algorithm below by making a matrix array list. Populate values with loop below.

    for (TeamMember m : p.members) {
            Integer[] scores = new Integer[3];
            int i = 0;
            for (TeamMember s : m.scores.keySet()) {
                scores[i++] = m.scores.get(s);
            }
            matrix.add(scores);
    }


    float memberOne = 1 /(1 + ((float)matrix.get(1)[1] / (float)matrix.get(1)[0]) + ((float)matrix.get(2)[1] / (float)matrix.get(2)[0]));

    float memberTwo = 1 /(1 + ((float)matrix.get(0)[1] / (float)matrix.get(0)[0]) + ((float)matrix.get(2)[0] / (float)matrix.get(2)[1]));

    float memberThree = 1 /(1 + ((float)matrix.get(0)[0] / (float)matrix.get(0)[1]) + ((float)matrix.get(1)[0] / (float)matrix.get(1)[1]));

    System.out.println("First members score: " + memberOne);
    System.out.println("Second members score: " + memberTwo);
    System.out.println("Third members score: " + memberThree);
    System.out.println((float)matrix.get(1)[1]);
    System.out.println((float)matrix.get(1)[0]);
    System.out.println((float)matrix.get(2)[1]);
    System.out.println((float)matrix.get(2)[0]);

  }


  public static void fourMoreTeamAlgo(Project p) {

    HashMap<TeamMember, Float> results = new HashMap<TeamMember, Float>();
    // I'm going to sum the bottom half of the fraction as I go, the first value is always 1
    for (TeamMember m : p.members) {
            results.put(m, 1.0f);
    }

    for (TeamMember m : p.members) {
            for (TeamMember s : m.scores.keySet()) {
                // I get the total of the bottom half of the fraction so far, for this member s
                Float curVal = results.get(s);
                // Now I add on the proportion of m's vote to the bottom half of the fraction
                results.put(s, curVal + ((float)m.scores.get(s) / (100.0f - (float)m.scores.get(s))));
            }
    }

    for (TeamMember m : p.members) {
            // now I do the inverse to get the proportion
            float proportion = 1.0f / results.get(m);
            System.out.println("Proportion for " + m.name + ": " + proportion);
    }
  }
}
