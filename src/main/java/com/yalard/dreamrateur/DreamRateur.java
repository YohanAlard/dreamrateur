package com.yalard.dreamrateur;

import com.yalard.dreamrateur.com.yalard.dreamrateur.services.CommunicationService;
import com.yalard.dreamrateur.model.MyRobot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by yohan alard on 24/03/2017
 */
public class DreamRateur {
    private static Logger logger = LogManager.getLogger(DreamRateur.class);
    public static void main(String[] args) throws Exception {
        try {
            MyRobot myRobot = new MyRobot();
            myRobot.wakeup();
        } catch(RuntimeException e){
            logger.error(e);
        }
    }
}
