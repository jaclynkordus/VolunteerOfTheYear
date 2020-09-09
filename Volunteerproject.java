/**
 * @author Jaclyn Kordus CSCI-180-01 at 9am MWF 
 * This program reads in from a file and organizes the data into arrays.
 * It then calculates points based off of the number of hours volunteered by each person.
 * The person with the most points is the volunteer of the year.
 */
package volunteerproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Volunteerproject {

    public static final int volunteers = 25;
    public static final int weeks = 52;
    public static int points[] = new int[volunteers];
    public static String names[] = new String[volunteers];
    public static int hours[][] = new int[volunteers][weeks];
    public static File vfile = new File("volunteerFile.txt");
    public static double averageHours[] = new double[weeks];


    public static void main(String[] args) throws FileNotFoundException {

        System.out.print("The purpose of this program is to find the volunteer of the year.  A data\n"
                + "file is read in which has each volunteer's last name followed by the number \n"
                + "of hours that volunteer worked each week for the data collection perid.  The\n"
                + "average number of hours is computed for each week and if the volunteer \n"
                + "exceeds that average they are awarded an addition one hour for that week.\n"
                + "The volunteer for the year is the person with the greatest number of hours.\n\n");
        loadDataIntoArray();
        calculateAverageHoursWorkedForWeek();
        calculatePointsForHours();
        calculateAdditionalPointsForAboveAverage();
        outputWinner();

    } //end main

    public static void loadDataIntoArray() throws FileNotFoundException {
        Scanner inputFile = new Scanner(vfile);
        int row = 0;
        while (inputFile.hasNext() && row < names.length) {
            String line = inputFile.nextLine();
            String[] lineParts = line.split("  ");
            names[row] = lineParts[0];
            lineParts = lineParts[1].split("\t"); // /t is tab
            for (int column = 0; column < lineParts.length; column++) {
                hours[row][column] = Integer.parseInt(lineParts[column]);
            }//end for loop
            row++;
        } //end while
    }//end loadDataIntoArray

    public static void calculateAverageHoursWorkedForWeek() {
        int sum = 0;
        for (int column = 0; column < hours[0].length; column++) {
            for (int row = 0; row < hours.length; row++) {
                sum = sum + hours[row][column];
            } //end for loop
            averageHours[column] = (double) sum / volunteers;
            sum = 0;
        }//end for loop
    } //end calculateAverageHoursWorkedForWeek

    public static void calculatePointsForHours() {
        int sum = 0;
        for (int row = 0; row < hours.length; row++) {
            for (int column = 0; column < hours[row].length; column++) {
                sum = sum + hours[row][column];
            }//end for loop
            points[row] = sum;
            sum = 0;
        } //end for loop
    } //end calculatePointsForHours

    public static void calculateAdditionalPointsForAboveAverage() {
        for (int row = 0; row < hours.length; row++) {
            for (int column = 0; column < hours[row].length; column++) {
                if (hours[row][column] > averageHours[column]) {
                    points[row] = points[row] + 1;
                }
            }
        }
    } //end calculateAdditionalPointsForAboveAverage

    public static void outputWinner() {
        
        int maxIndex = 0;
        for (int row = 0; row < hours.length; row++) {
            if(points[maxIndex]< points[row]){
                maxIndex = row;
            }
        }
        String name = names[maxIndex];
        int mostPoints = points[maxIndex];
        System.out.println("The volunteer of the year is: "+ name + " who had " + mostPoints + " volunteer points for the year.");      
    } //end outputWinner()
}//end class

