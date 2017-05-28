package com.yalard.dreamrateur;

import com.yalard.dreamrateur.com.yalard.dreamrateur.services.CommunicationService;
import com.yalard.dreamrateur.constants.CellType;
import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.model.MyRobot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * Entry Point
 */
public class DreamRateur {
    //logger is mainly used to display message to  user
    private static Logger logger = LogManager.getLogger(DreamRateur.class);

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            CommunicationService service = new CommunicationService();
            //give instruction to user
            service.greetings();
            //read user inputs
            List<String> mazeLines= service.readLines();
            //show maze to user
            service.displayLines(mazeLines);

            //our best friend
            MyRobot myRobot = new MyRobot();

            //Randomise position avoid first and last line
            int lineRandomIndex = new Random().nextInt(mazeLines.size()-2) +1;
            int freeSpaceOnLineCount = countNumberOfFreeSpacePerLine(mazeLines.get(lineRandomIndex));
            int free = new Random().nextInt(freeSpaceOnLineCount);
            int columnRandomIndex = 0;
            for (int i=0;i<mazeLines.get(0).length() && free >0;i++){
                if (mazeLines.get(0).charAt(i) == CellType.WALL.getCharacter()){
                    free--;
                }
                columnRandomIndex++;
            }
            MazeState.INSTANCE.setRobotPosition(lineRandomIndex, columnRandomIndex);
            myRobot.start();
        } catch(RuntimeException e){
            logger.error(e);
        }
    }



    /**
     * count number of free space for one line
     *
     * @param line line to count
     * @return number
     */
    private static int countNumberOfFreeSpacePerLine(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isWhitespace(line.charAt(i))) count++;
        }
        return count;
    }
}
