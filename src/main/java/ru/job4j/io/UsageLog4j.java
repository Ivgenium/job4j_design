package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        byte age = 30;
        short height = 180;
        int numberOfBricks = 1_000_000;
        float a = 3.5F;
        double threshold = 0.0001D;
        long distanceToSpaceObjects = 149_600_000L;
        char gender = 'm';
        boolean workPermit = false;
        LOG.debug("Age : {}, height : {}, number of bricks : {}, a : {}, threshold : {}"
                + ", distance to space objects : {}, gender : {}, work permit : {}",
                age, height, numberOfBricks, a, threshold, distanceToSpaceObjects, gender, workPermit);
    }
}