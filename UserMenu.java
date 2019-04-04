//This code initiates the splitit application and calls for initial user input
//03.02.18
//Dara Vakili & Laurence Pengelly

import java.util.ArrayList;

public class UserMenu  {

    private ArrayList<Project> projects = new ArrayList<Project>();
    static char menuNavMenu;

    public static void main(String[] args) {

        UserMenu userMenu = new UserMenu();
        userMenu.loadProjects();
        while (true) {
          userMenu.executeMenu();
        }
    }

    //-------------------------------------------------------
    //Print Menu options asking user to select functions
    //-------------------------------------------------------

    public void executeMenu() {

            char[] chars = {'a', 'c', 'v', 'q', 'e', 'd', 's'};

            System.out.println("\nWelcome to Split-it");
            System.out.println("\n\t\tAbout (A)");
            System.out.println("\t\tCreate New Project (C)");
            System.out.println("\t\tSave Projects (V)");
            System.out.println("\t\tEdit member votes (E)");
            System.out.println("\t\tDelete projects (D)");
            System.out.println("\t\tShow Project allocation (S)");
            System.out.println("\t\tQuit (Q)");

            System.out.print("\n\t\tPlease choose an option: ");
            menuNavMenu = InputController.scanUnderChar(chars);
            navMenu(menuNavMenu);

    }

    //-------------------------------------------------------
    //Switch to execute menu items
    //-------------------------------------------------------

    public void navMenu(char menuNavMenu) {

        switch (menuNavMenu) {
            case 'a':
                openAbout();
                break;
            case 'c':
                makeProject();
                break;
            case 'e':
                selectProjectToEdit();
                break;
            case 'd':
                deleteProject();
                break;
            case 'v':
                FileManager.saveProjects(projects);
                break;
            case 's':
                Algorithms.sorter(projects);
                break;
            case 'q':
                exit();
            default:
                System.out.println("Sorry, something went wrong here, please call admin"); //should never happen
                executeMenu();
        }
    }

    //-------------------------------------------------------
    //Execute OpenAbout page
    //-------------------------------------------------------

    public void openAbout() {

      char[] chars = {'m', 'q'};

      System.out.println(FileManager.fileReader("../About.txt"));
      System.out.print("\n\tEnter M to retun to Menu or Q to quit: ");
      menuNavMenu = InputController.scanUnderChar(chars);
      if (menuNavMenu == 'q') {
         navMenu(menuNavMenu);
      } else if (menuNavMenu == 'm') {
          executeMenu();
      }
    }

    //-------------------------------------------------------
    //Execute Create project class
    //-------------------------------------------------------

    public void makeProject() {

      String name = "";

      do {
        System.out.println("\n\n\tCreate a new project by following the prompts below");
        System.out.print("\n\t\tEnter the project name: ");
        InputController.nextLine();
        name = InputController.scanUnderString(70, 2);
        if (projectListContains(name)) {
          System.out.println("\t\tERROR!!!!! Projects must have unique names");
        }
      } while (projectListContains(name));
        projects.add(new Project(name));
    }

    //-------------------------------------------------------
    //Commence load project seq
    //-------------------------------------------------------

    public void loadProjects() {
      projects.addAll(FileManager.loadFile(projects));
    }

    //-------------------------------------------------------
    //Exit program but first check if user = sure
    //-------------------------------------------------------

    public void exit() {

        char[] chars = {'m', 'q'};
        char exitCheck = '0';

        System.out.println("\n\tAre you sure you wish to quit?");
        System.out.println("\n\t\tSelect 'Q' to quit");
        System.out.println("\t\tSelect 'm' to return to main");
        System.out.print("\n\t\tPlease confirm your choice: ");

        exitCheck = InputController.scanUnderChar(chars);

        if (exitCheck == 'q') {
            System.exit(0);
        } else if (exitCheck == 'm') {
            executeMenu();
        }
    }

    //-------------------------------------------------------
    //Launch editor, prints projects and members.
    //Selects a project object and edits votes
    //-------------------------------------------------------

    public void selectProjectToEdit() {

      int selection = 0;

      if (projects.size() > 0) {
        System.out.println("\t\tSelect the project to edit by number:");
        Project.printProjectList(projects);
        System.out.print("\t\tConfirm choice: ");
        selection = (InputController.scanUnderInt(projects.size(), 1)-1);
        Project p = projects.get(selection);
        p.editVotes();
      } else {
        InputController.printUnder("\t\tERROR!!!! \tPlease load projects or create new project");
      }
    }

    //-------------------------------------------------------
    //Delete project objects
    //-------------------------------------------------------

    public void deleteProject() {

      int selection = 0;
      char[] chars = {'y','n'};
      char option = 'y';

      while (projects.size() > 0 && option == 'y') {

        System.out.println("\t\tSelect the project to DELETE by number:\t\t**Use '0' to select none:**"); //is selecting 0 project a good way?
        Project.printProjectList(projects);
        System.out.print("\n\n\t\tPlease choose from 1 to " + projects.size() + ": ");
        System.out.print("\t\tPlease confirm choice: ");
        selection = (InputController.scanUnderInt(projects.size(), 0)-1);
        if (selection == -1)
          break; //is there a better way to do this?
        projects.remove(selection);
        System.out.print("\n\t\tWould you like to delete another project? Y/N: ");
        option = InputController.scanUnderChar(chars);
      }
    executeMenu();
  }

    //-------------------------------------------------------
    //validation making sure projects don't have same name
    //this was placed in this class as the projects listarray
    //belongs here
    //-------------------------------------------------------

    public boolean projectListContains(String name) {
      for (Project p : projects) {
        if (name == p.getName()) {
          return true;
        }
      }
      return false;
    }
}
