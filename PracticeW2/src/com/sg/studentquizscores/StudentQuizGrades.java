package com.sg.studentquizscores;

public class StudentQuizGrades {
    public static void main(String[] args) {
        boolean programRunning = true;
        int userChoice = 0;

        // instantiate object for user input methods of the UserIOConsoleImpl() class
        UserIOConsoleImpl appIO = new UserIOConsoleImpl();

        // do while to keep menu running as long as user doesn't select 6 to exit program
        do {
            // display menu options to user
            userChoice = appIO.readInt("STUDENT SYSTEM MENU OPTIONS\n" +
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

                        break;
                    case 2:
                        break;
                    case 3:
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
