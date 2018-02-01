package com.sg.studentquizscores;

public class StudentQuizGrades {
    public static void main(String[] args) {
        UserIOConsoleImpl appIO = new UserIOConsoleImpl();

        // display menu options to user
        appIO.readInt("STUDENT SYSTEM MENU OPTIONS\n" +
                        "==============================\n" +
                        "1. View a list of students in the system\n" +
                        "2. Add a student to the system\n" +
                        "3. Remove a student from the system\n" +
                        "4. View a list of quiz scores for a given student\n" +
                        "5. View the average quiz score for a given student\n", 1, 6);



    }
}
