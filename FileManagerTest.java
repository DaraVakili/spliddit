//Test class

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FileManagerTest {

//Test to see if the project written and read are the same

	@Test
	public void testProjectListArray() {
		String projectString = "test,4,dara,veda,anton,mikal,dara,mikal,40,veda,30,anton,30,veda,mikal,80,anton,10,dara,10,anton,mikal,40,veda,10,dara,50,mikal,veda,20,anton,60,dara,20\r\n";

		ArrayList<Project> projects = new ArrayList<Project>();
		String objectData[];
		String loadData[] = projectString.split("\n");


		for (int i = 1; i < loadData.length; i++) {
			objectData = loadData[i].split(",");
			Project newProj = new Project(objectData);
			projects.add(newProj);
		}

		FileManager.saveProjects(projects);

		ArrayList<Project> testProject = FileManager.loadFile(new ArrayList<Project>());

		assertEquals(projects, testProject);

	}

}
