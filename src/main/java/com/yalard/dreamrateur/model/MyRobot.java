package com.yalard.dreamrateur.model;

import com.yalard.dreamrateur.com.yalard.dreamrateur.services.CommunicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class MyRobot {
    private static Logger logger = LogManager.getLogger(MyRobot.class);
    private Map map;
    private List <String> lines;
    private CommunicationService service = new CommunicationService();

    //pop randomly on the map
    private void pop(){
        //filter Wall
        //convert input in 2 way dimentional map
    }

    public int countAvailablePopPosition(){
        return lines.stream().mapToInt(l -> l.split(" ").length).sum();
    }
    //scan left right upper & down position
    public void sonar(){

    }
    //stop the process
    public void stop(){

    }

    public void wakeup(){
        service.greetings();
        lines= service.readLines();
        service.displayLines(lines);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
