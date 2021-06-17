package aquarium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parser {
    public int width;
    public int height;
    public int stonesNumber;
    public int herbsNumber;
    public int feedScores;
    public int fishHerbsNumber;
    public int fishPredatorsNumber;
    public int ageMax;
    public int ageBeginMature;
    public int fish_maxHunger;
    public int pregnancyPeriod;
    public int numberOfChildren;
    public int ageBeginOld;
    public int moveSpeed;

    public Parser(String configPath) {
        try (InputStream stream = new FileInputStream(new File(configPath))){
            var props = new Properties();
            props.load(stream);

            width = Integer.parseInt(props.getProperty("width"));
            height = Integer.parseInt(props.getProperty("height"));
            stonesNumber = Integer.parseInt(props.getProperty("stonesNumber"));
            herbsNumber = Integer.parseInt(props.getProperty("herbsNumber"));
            feedScores = Integer.parseInt(props.getProperty("feedScores"));
            fishHerbsNumber = Integer.parseInt(props.getProperty("fishHerbsNumber"));
            fishPredatorsNumber = Integer.parseInt(props.getProperty("fishPredatorsNumber"));
            ageMax = Integer.parseInt(props.getProperty("ageMax"));
            ageBeginMature = Integer.parseInt(props.getProperty("ageBeginMature"));
            fish_maxHunger = Integer.parseInt(props.getProperty("hungerMaxLevel"));
            pregnancyPeriod = Integer.parseInt(props.getProperty("pregnancyPeriod"));
            numberOfChildren = Integer.parseInt(props.getProperty("childrenMaxNumber"));
            ageBeginOld = Integer.parseInt(props.getProperty("ageBeginOld"));
            moveSpeed = Integer.parseInt(props.getProperty("moveSpeed"));
        }
        catch (IOException ex){
            throw new IllegalStateException("Config file is broken!");
        }
    }
}
