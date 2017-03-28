import com.yalard.dreamrateur.model.MyRobot;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * integration test
 */
public class MyRobotTest  {
        String inputs = "MMMMMMMMMM" + "\n" +
                        "M        M" + "\n"+
                        "M     MMMM" + "\n"+
                        "MM      MM" + "\n"+
                        "MM MMM  MM" + "\n"+
                        "M  MMM  MM" + "\n"+
                        "MMMMMMMMMM";
        @Test
        public void count(){
            MyRobot myRobot = new MyRobot();
            myRobot.setLines(Arrays.asList(inputs.split("\n")));
            Assert.assertTrue(myRobot.countAvailablePopPosition() == 33);
        }
}
