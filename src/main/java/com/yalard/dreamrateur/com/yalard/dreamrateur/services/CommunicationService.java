package com.yalard.dreamrateur.com.yalard.dreamrateur.services;

import com.yalard.dreamrateur.com.yalard.dreamrateur.TechException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Scanner;

/**
 * Created by yohanalard on 24/03/2017.
 */

public class CommunicationService {
    private static Logger logger = LogManager.getLogger(CommunicationService.class);
    private MapService mapService = new MapService();

    public void greetings(){
        logger.info("Hello, my name is dream Rateur ! a new kind of vacuum cleaner");
        logger.info("i m gonna ask you to feed me with your input map");
        logger.info("and don't forget ...  only 'M' or ' '  character are allowed");
        logger.info("Let's go : enter your first line");
    }

    public List<String> readLines(){
        Scanner reader = new Scanner(System.in);
        String line ="";
        while(!mapService.isLastLine(line)) {
            try {
                line = reader.nextLine();
                //validate line
                mapService.validateLine(line);
                mapService.addLine(line);
            } catch (TechException e){
                displayError(e.getMessage());
            }
        }
        return mapService.getLines();
    }

    public void displayError(String message){
        logger.error(message,"please retry");
    }

    public void displayLines(List<String> lines){
        logger.info("Good Job Padawan: you enter the following maze :");
        lines.forEach(s -> logger.info(s));
    }

    public void say(String message){
        logger.info(message);
    }
}
