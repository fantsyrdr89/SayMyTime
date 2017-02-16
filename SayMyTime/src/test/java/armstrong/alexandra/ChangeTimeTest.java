package armstrong.alexandra;

import org.junit.Test;

import static armstrong.alexandra.ChangeTime.*;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ChangeTimeTest {
    @Test
    public void splitInputTest() throws NotATime {
        String[] expected = {"3", "30", "am"};
        String[] actual = splitInput("3:30 am");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void splitInputTest1() throws NotATime {
        String[] expected = {"13", "30"};
        String[] actual = splitInput("13:30");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void validTimeTest1(){
        String[] test = {"20", "200", "am"};
        assertFalse(validStandardTime(test));
    }

    @Test
    public void validTimeTest2(){
        String[] test = {"20", "58", "am"};
        assertFalse(validStandardTime(test));
    }

    @Test
    public void validMilitaryTimeTest3(){
        String test = "2058";
        assertTrue(validMilitaryTime(test));
    }

    @Test
    public void validMilitaryTimeTest4(){
        String test = "wsd";
        assertFalse(validMilitaryTime(test));
    }

    @Test
    public void militaryTest1(){
        String test = "2301";
        military(test);
        String actual = sb.toString();
        String expected = "twenty three zero one";
        assertEquals(expected, actual);
    }
}
