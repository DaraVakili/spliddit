import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlgorithmsTest.class, FileManagerTest.class, InputControllerTest.class, ProjectTest.class,
		TeamMemberTest.class, UserMenuTest.class })
public class AllTests {

}
