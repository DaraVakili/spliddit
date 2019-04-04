//This class holds the vote information for each TeamMember

import java.util.HashMap;

public class TeamMember {

  public static final int VOTELIM = 99;
  public static final int MINVOTE = 1;

  String name;
  HashMap<TeamMember, Integer> scores = new HashMap<>();
  Project project; //link to project class

  public TeamMember(String name, Project project) {
      this.name = name;
      this.project = project;
  }
  //--------------------------------------------------------------
  //Initiate the second loop, within that loop, to add scores for
  //each person via hashmap
  //--------------------------------------------------------------
  public void enterVotes() {

      int memberTotal = 0;

      System.out.println("\n\t\tEnter " + name + "'s votes. Must add to 100: ");
      do {
          for (TeamMember member : project.members) {
              if (member.name.equals(name))
                continue;
              System.out.print("\n\n\t\t\tEnter " + member.name + "'s score: ");
              int score = InputController.scanUnderInt(VOTELIM, MINVOTE);
              memberTotal += score;
              scores.put(member, score);
          }
          if (memberTotal != 100) {
              InputController.printUnder("\n\tERROR!!! please make sure your numbers add up to 100. ");
              InputController.printUnder("\n\tYour scores were ");
              memberTotal = 0;

            for (TeamMember member : scores.keySet()) {
                int score = scores.get(member);
                InputController.printUnder("\n\t\t" + member.name + "'s score was: " + score);
            }
         }
        } while (memberTotal != 100);
  }

  //--------------------------------------------------------------
  //make object from the save data
  //--------------------------------------------------------------

  public void enterVotes(String[] params) {

    for (int i = 0; i < params.length; i += 2) {
      for (TeamMember m : project.members) {
        if (m.name.equals(params[i])) {
          scores.put(m, Integer.parseInt(params[i+1]));
          break;
        }
      }
    }
  }

  //-------------------------------------------------------
  //Passes object info to parent
  //-------------------------------------------------------

  @Override
  public String toString() {

    String memberString = name;

    for (TeamMember m : scores.keySet()) {
      memberString += "," + m.name + "," + scores.get(m);
    }
    return memberString;
  }
}
