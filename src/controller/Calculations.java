package controller;

import model.Events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Calculations {

    //The method calculates the price for the event
    public static double calculatePrice(String numberOfPeople) {

        double finalPrice = 0;

        //Takes the amount of quests from the textField and converts it to an int
        int howManyQuests = Integer.parseInt(numberOfPeople);

        if (howManyQuests >= 40 && howManyQuests <= 50) {
            finalPrice = 4900;
        } else if (howManyQuests >= 50 && howManyQuests <= 60) {
            finalPrice = 5400;
        } else if (howManyQuests >= 60 && howManyQuests <= 70) {
            finalPrice = 5900;
        } else if (howManyQuests >= 70 && howManyQuests <= 80) {
            finalPrice = 6400;
        } else if (howManyQuests >= 80 && howManyQuests <= 90) {
            finalPrice = 6900;
        } else if (howManyQuests >= 90 && howManyQuests <= 100) {
            finalPrice = 7400;
        } else if (howManyQuests >= 100 && howManyQuests <= 399) {
            finalPrice = 3800; //3800 kr + 19 kr per unit sold: This is not shown in the program
        } else if (howManyQuests >= 400) {
            finalPrice = (howManyQuests * 49);
        }

        return finalPrice;
    }

    //Converts String to LocalDate object
    public LocalDate stringToDate(String date){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(date, df);
        return dt;
    }

    //method for handling countDown
    public void countdownForOrder(Events event){

        //First we convert the eventDate and timeOfCreation to days since 1970
        long timeOfCreationInDays = event.getDateOfCreation() / 1000 / 60/60/ 24; //1000 milliseconds to a second, 60 seconds to a minute 60 minutes to an hour, 24 hours to a day
        long now = Calendar.getInstance().getTimeInMillis() / 1000 / 60 / 60 / 24;

        //Subtract them to come up with the difference in days
        long daysUntilDeletion = now - timeOfCreationInDays;

        if(daysUntilDeletion >= 14){
            event.setEventCountdown(0);
        }
        else if(daysUntilDeletion == 13){
            event.setEventCountdown(1);
        }
        else if(daysUntilDeletion == 12){
            event.setEventCountdown(2);
        }
        else if(daysUntilDeletion == 11){
            event.setEventCountdown(3);
        }
        else if(daysUntilDeletion == 10){
            event.setEventCountdown(4);
        }
        else if(daysUntilDeletion == 9){
            event.setEventCountdown(5);
        }
        else if(daysUntilDeletion == 8){
            event.setEventCountdown(6);
        }
        else if(daysUntilDeletion == 7){
            event.setEventCountdown(7);
        }
        else if(daysUntilDeletion == 6){
            event.setEventCountdown(8);
        }
        else if(daysUntilDeletion == 5){
            event.setEventCountdown(9);
        }
        else if(daysUntilDeletion == 4){
            event.setEventCountdown(10);
        }
        else if(daysUntilDeletion == 3){
            event.setEventCountdown(11);
        }
        else if(daysUntilDeletion == 2) {
            event.setEventCountdown(12);
        }
        else if(daysUntilDeletion == 1){
            event.setEventCountdown(13);
        }
        else if(daysUntilDeletion <= 0){
            event.setEventCountdown(14);
        }
    }
}


