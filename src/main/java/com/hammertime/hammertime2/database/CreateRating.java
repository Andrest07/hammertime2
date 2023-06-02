package com.hammertime.hammertime2.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateRating {
    List<TempReview> revs = new ArrayList<TempReview>();

    public CreateRating(){
        revs.add(new TempReview("The tradie who completed my job did a terrible job. They didn't seem to have much experience and made a mess of things.", 1));
        revs.add(new TempReview("The tradie who completed my job was friendly, but they weren't very thorough. There were some issues with the job that needed to be addressed.", 2));
        revs.add(new TempReview("The tradie who completed my job was decent, but they weren't very efficient. It took longer than expected to complete the job.", 3));
        revs.add(new TempReview("The tradie who completed my job did a good job overall, but there were some small issues that needed to be fixed.", 3));
        revs.add(new TempReview("The tradie who completed my job did an excellent job! They were professional, efficient, and completed the job to my satisfaction.", 5));
        revs.add(new TempReview("The tradie who completed my job was fantastic! They were knowledgeable, efficient, and completed the job in a timely manner.", 5));
        revs.add(new TempReview("The tradie who completed my job was adequate, but there were some small issues that needed to be addressed.", 3));
        revs.add(new TempReview("The tradie who completed my job was amazing! They were knowledgeable, professional, and completed the job to an exceptional standard.", 5));
        revs.add(new TempReview("The tradie who completed my job did a decent job overall, but there were some areas that could have been improved.", 3));
        revs.add(new TempReview("The tradie who completed my job was exceptional! They were efficient, friendly, and completed the job to an incredibly high standard.", 5));
        revs.add(new TempReview("The tradie who completed my job was terrible! They were unprofessional, unreliable, and didn't complete the job to my satisfaction.", 1));
        revs.add(new TempReview("The tradie who completed my job was friendly enough, but they didn't seem to have the necessary skills for the job.", 2));
        revs.add(new TempReview("The tradie who completed my job was decent, but there were some small issues that needed to be fixed.", 3));
        revs.add(new TempReview("The tradie who completed my job did a good job overall, but there were some minor issues that needed to be addressed.", 4));
        revs.add(new TempReview("The tradie who completed my job was excellent! They were efficient, professional, and completed the job to my satisfaction.", 5));
        revs.add(new TempReview("The tradie who completed my job was fantastic! They were knowledgeable, efficient, and completed the job to a high standard.", 5));
        revs.add(new TempReview("The tradie who completed my job was adequate, but there were some issues that needed to be addressed.", 3));
        revs.add(new TempReview("The tradie who completed my job was amazing! They were professional, efficient, and completed the job to an exceptional standard.", 5));
        revs.add(new TempReview("The tradie who completed my job did a decent job overall, but there were some areas that could have been improved.", 3));
        revs.add(new TempReview("The tradie who completed my job was exceptional! They were efficient, friendly, and completed the job to an incredibly high standard. I couldn't be happier!", 5));
    }

    public TempReview getRandomRev(){
        Random rand = new Random();
        return revs.get(rand.nextInt(revs.size()));
    }
}