package com.sg.studentquizscores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class StudentQuizGrades {
    public static void main(String[] args) {
        boolean programRunning = true;
        int userChoice = 0;
        String name;

        // create HashMap to store students in. student name is key and ArrayList of integers is the value
        HashMap<String, ArrayList> studentQuizScores = new HashMap<>();

        // define set to get keys
        Set<String> keys = studentQuizScores.keySet();

        // statically populate studentQuizScores HashMap
        ArrayList<Integer> quizScoresTaylor = new ArrayList<>();
        quizScoresTaylor.add(90);
        quizScoresTaylor.add(80);
        quizScoresTaylor.add(70);
        quizScoresTaylor.add(100);
        studentQuizScores.put("Taylor", quizScoresTaylor);
        ArrayList<Integer> quizScoresBrett = new ArrayList<>();
        quizScoresTaylor.add(90);
        quizScoresTaylor.add(97);
        quizScoresTaylor.add(95);
        quizScoresTaylor.add(100);
        studentQuizScores.put("Brett", quizScoresTaylor);

        // instantiate object for user input methods of the UserIOConsoleImpl() class
        UserIOConsoleImpl appIO = new UserIOConsoleImpl();

        // do while to keep menu running as long as user doesn't select 6 to exit program
        do {
            // display menu options to user
            userChoice = appIO.readInt("\nSTUDENT SYSTEM MENU OPTIONS\n" +
                    "==============================\n" +
                    "1. View a list of students in the system\n" +
                    "2. Add a student to the system\n" +
                    "3. Remove a student from the system\n" +
                    "4. View a list of quiz scores for a given student\n" +
                    "5. View the average quiz score for a given student\n" +
                    "6. Exit program", 1, 6);

            // if user chooses to exit program, exit loop and program
            if(userChoice == 6){
                programRunning = false;
            } else {

                // switch statement to determine what action to take based on user choice
                switch(userChoice){
                    case 1:
                        appIO.print("\n Students in our system: \n");
                        // get the set of keys from the map

                        for(String k: keys){
                            // output student's name to console
                            appIO.print(k);
                        }
                        break;
                    case 2:
                        ArrayList<Integer> newStudentScores = new ArrayList<>();
                        name = appIO.readString("Provide the name of the new student: ");
                        studentQuizScores.put(name, newStudentScores);
                        break;
                    case 3:
                        name = appIO.readString("Provide the name of the student to remove: ");

                        boolean nameExists = true;
                        for(String k: keys) {
                            if (name.equals(k)) {
                                studentQuizScores.remove(name);
                                appIO.print("Successfully removed " + name + " from the system.");

                            }
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }

        } while(programRunning);

    }
}
