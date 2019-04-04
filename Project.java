//This code is used to open and new projects and save them for the spliddit application
//06.02.18
//Dara Vakili & Laurence Pengelly

import java.util.Arrays;
import java.util.ArrayList;

public class Project {

//-------------------------------------------------------
//Start Project class - starts here
//-------------------------------------------------------

  public static final int MAXTEAM = 20; //system allows unlimited team size but too cumbersome >= 20
  public static final int MINTEAM = 3; //min no of team members
  public static final int MAXCHAR = 70; //max length of str inputs
  public static final int MINCHAR = 1; //min length of str inputs

  private String name;
  private int noTeamMembers;
  public TeamMember members[];


  public Project(String name) {

    this.name = name;

    //Declare name array with noTeamMembers elements
        System.out.print("\n\t\tHow many members are in your team? ");
        noTeamMembers = InputController.scanUnderInt(MAXTEAM, MINTEAM);
        InputController.nextLine();
        members = new TeamMember[noTeamMembers];

    //For loop that adds names to each element of name array
    for (int i = 0; i < noTeamMembers; i++) {
        String memberName = "";
        Boolean duplicate = true;
        do {
          System.out.print("\n\t\t\tEnter Member " + (i+1) + "'s name : ");
          memberName = InputController.scanUnderString(MAXCHAR, MINCHAR);
          if (duplicate = membersListContains(memberName)) {
            System.out.println("\t\tMembers cannot have the same name");
          }
        } while (duplicate);
        members[i] = new TeamMember(memberName, this);
    }

    //for loop to make each member enter their votes. Added in element i of listarray. See above^
    for (int i = 0; i < noTeamMembers; i++) {
      members[i].enterVotes();
    }
    toString();
  }

  //-------------------------------------------------------
  //adds projects from save data, new constructor overloaded
  //-------------------------------------------------------

  public Project(String[] params) {

    try{
      this.name = params[0];
      this.noTeamMembers = Integer.parseInt(params[1]);
      members = new TeamMember[this.noTeamMembers];
      int memberNum = 0;
      for (int i = 2; i < 2+noTeamMembers; i++) {
        members[memberNum] = new TeamMember(params[i], this);
        memberNum++;
      }
      int j = 0;
      for (int i = 2 + noTeamMembers; i < params.length; i += 2 * (noTeamMembers - 1) + 1) {
        members[j++].enterVotes(Arrays.copyOfRange(params, i + 1, i + 1 + 2 * (noTeamMembers - 1)));
      }
    } catch (Exception e) {
      InputController.printUnder("\t\tError!!!!!! Some projects are corrupted. Trying to recover working projects \n\t\t\tPlease send this report to support: \n\n");
      e.printStackTrace();
    }
  }

  //-------------------------------------------------------
  //Exit program but first check if user = sure
  //-------------------------------------------------------

  public boolean membersListContains(String name) {

    for (TeamMember m : members) {
      if (m != null && name.equals(m.name)) {
        return true;
      }
    }
    return false;
  }

  //-------------------------------------------------------
  //Edit member votes
  //-------------------------------------------------------

  public void editVotes() {

    for (int i = 0; i < noTeamMembers; i++) {
      members[i].enterVotes();
    }
  }

  //-------------------------------------------------------
  //getter for project name
  //-------------------------------------------------------

  public String getName() {
    return name;
  }

  //-------------------------------------------------------
  //Print project list
  //-------------------------------------------------------

  public static void printProjectList(ArrayList<Project> projects) {

    for (int i = 0; i < projects.size(); i++) {

      String info[] = String.valueOf(projects.get(i)).split(",");
      int noMembers = Integer.parseInt(info[1]);

      InputController.printUnder("\n\t\t" + (i+1) + ")");
      System.out.println("\tProject Name: " + info[0]);
      System.out.print("\t\t\tMembers: ");
      for (int j =0; j < noMembers; j++) {
        System.out.print("\t" + info[2+j]);
      }
    }
  }

  //-------------------------------------------------------
  /*** @return the noTeamMembers*/
  //-------------------------------------------------------

  public int getNoTeamMembers() {
  	return noTeamMembers;
  }

  //-------------------------------------------------------
  //Returns object data for parent
  //-------------------------------------------------------

  @Override
  public String toString() {

    String projString = this.name + ",";

    projString += this.noTeamMembers;
    for (TeamMember m : members) {
      projString += "," + m.name;
    }
    for (TeamMember m : members) {
      projString += "," + m.toString();
    }
    return projString;
  }
}
