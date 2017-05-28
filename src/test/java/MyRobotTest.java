import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.model.MyRobot;
import com.yalard.dreamrateur.validator.MazeValidator;
import org.junit.Test;

import java.util.Arrays;

/**
 * integration test
 */
public class MyRobotTest  {
        /*
             0123456789
            0MMMMMMMMMM" + "\n" +
            1M        M" + "\n"+
            2M X   MMMM" + "\n"+
            3MM      MM" + "\n"+
            4MM MMM  MM" + "\n"+
            5M  MMM  MM" + "\n"+
            6MMMMMMMMMM
            */
        String inputs = "MMMMMMMMMM" + "\n" +
                        "M        M" + "\n"+
                        "M     MMMM" + "\n"+
                        "MM      MM" + "\n"+
                        "MM MMM  MM" + "\n"+
                        "M  MMM  MM" + "\n"+
                        "MMMMMMMMMM";
        @Test
        public void launch(){
            MazeValidator.INSTANCE.setLines(Arrays.asList(inputs.split("\n")));
            MazeState.INSTANCE.setRobotPosition(2,2);
            MyRobot myRobot = new MyRobot();
            myRobot.start();

        }
}
