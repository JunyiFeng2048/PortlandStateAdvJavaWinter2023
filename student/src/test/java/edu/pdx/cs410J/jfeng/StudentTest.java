package edu.pdx.cs410J.jfeng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest {

  @Test
  void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  void ThisClassIsToMuchWork() {
    Student student = new Student("Name", new ArrayList<>(), 0.0, "NO!!!");
    assertThat(student.says(), equalTo("This class is to much work!"));
  }

  @Test
  void StudentSaysWhatIsExpected()
  {
    ArrayList<String> classes = new ArrayList<>();

    classes.add("AI");
    classes.add("Web Dev");
    classes.add("Operating Systems");

    Student john = new Student("John", classes, 4.0, "male");

    //String johnString = john.toString();
    //assertThat(johnString, equalTo(johnString));
  }

  @Test
  void NullNameThrowsNullPointerException()
  {
    assertThrows(NullPointerException.class, () -> new Student(null, null, 4.0, null));
  }

  @Test
  void NullClassesThrowsNullPointerException()
  {
    assertThrows(NullPointerException.class, () -> new Student("Name", null, 4.0, null));
  }

  @Test
  void NullGenderThrowsNullPointerException()
  {
    assertThrows(NullPointerException.class, () -> new Student("Name", new ArrayList<>(), 4.0, null));
  }

  @Test
  void studentCanTakeOneClass()
  {
    ArrayList<String> classes = new ArrayList<>();

    classes.add("AI");
    classes.add("Web Dev");
    classes.add("Operating Systems");

    Student john = new Student("John", classes, 4.0, "male");
    String johnString = john.toString();
    assertThat(johnString, containsString("is taking 1 class"));
  }


}
