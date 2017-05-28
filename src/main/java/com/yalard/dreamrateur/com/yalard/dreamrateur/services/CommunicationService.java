package com.yalard.dreamrateur.com.yalard.dreamrateur.services;

import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.exception.TechException;
import com.yalard.dreamrateur.validator.MazeValidator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class CommunicationService {
    private static Logger logger = LogManager.getLogger(CommunicationService.class);

    /**
     * display greeting message
     */
    public void greetings() {
        logger.info("Hello, my name is dream Rateur ! a new kind of vacuum cleaner");
        logger.info("i m gonna ask you to feed me with your input map");
        logger.info("and don't forget ...  only 'M' or ' '  character are allowed");
        logger.info("Let's go : enter your first line");
    }

    /**
     * read line by line the maze
     *
     * @return user input maze
     */
    public List<String> readLines() {
        Scanner reader = new Scanner(System.in);
        String line = "";
        while (!MazeValidator.INSTANCE.isLastLine(line)) {
            try {
                line = reader.nextLine();
                //validate line
                MazeValidator.INSTANCE.validateLine(line);
                MazeState.INSTANCE.addLine(line);
            } catch (TechException e) {
                displayError(e.getMessage());
            }
        }
        return MazeValidator.INSTANCE.getLines();
    }

    /**
     * display an error message
     *
     * @param message
     */
    public void displayError(String message) {
        logger.error(message, "please retry");
    }

    /**
     * display lines
     * @param lines to display
     */
    public void displayLines(List<String> lines) {
        logger.info("Good Job Padawan: you enter the following maze :");
        lines.forEach(s -> logger.info(s));
    }

    /**
     * generic message
     * @param message message
     */
    public void say(String message) {
        logger.info(message);
    }
}
