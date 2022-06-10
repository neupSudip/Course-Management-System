package SudipNeupane_2050436;

import java.util.*;

public class CourseManagementSystem {
    Scanner scan = new Scanner(System.in);

    public void student(){
        Students students = new Students();
        System.out.println(".........................................................................");
        System.out.println("Press 1. for taking enrollment in our college.");
        System.out.println("Press 2. for seeing your module instructor.");
        System.out.println(".........................................................................");
        int select = 0;
        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                select = scan.nextInt();
                while((select > 2) || (select < 1)){
                    System.out.println(".........................................................................");
                    System.out.println("Please enter a valid selection for services");
                    select = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println(".........................................................................");
                System.out.println("Please enter a valid selection for services");
                invalidInput = true;
                scan.nextLine();
            }
        } while (invalidInput);

        if(select == 1){
            students.takeEnrollment();
        }else{
            students.seeMyInstructorDetails();
        }

    }

    public void administrator(){
        CourseAdministrator admin = new CourseAdministrator();
        System.out.println(".........................................................................");
        System.out.println("Press 1. to add new Course in course table.");
        System.out.println("Press 2. to rename existing course name.");
        System.out.println("Press 3. to remove existing course from course table.");
        System.out.println("Press 4. to add new modules to course.");
        System.out.println("Press 5. to rename existing module name.");
        System.out.println("Press 6. to remove instructor and add new one.");
        System.out.println("Press 7. to generate students report card.");
        System.out.println(".........................................................................");
        int select = 0;
        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                select = scan.nextInt();
                while ((select > 7) || (select < 1)){
                    System.out.println(".........................................................................");
                    System.out.println("Please enter a valid selection for services");
                    select = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println(".........................................................................");
                System.out.println("Please enter a valid selection for services");
                invalidInput = true;
                scan.nextLine();
            }
        } while (invalidInput);

      if(select == 1){
          admin.addNewCourse();
      }else if (select == 2){
          admin.renameCourse();
      }else if (select == 3){
          admin.removeCourse();
      }else if (select == 4){
          admin.addModulesToCourse();
      }else if (select == 5){
          admin.renameModule();
      }else if (select == 6){
          admin.removeInstructor();
      }else {
          admin.generateReportSlip();
      }

    }

    public  void instructor(){
        Instructor instructor = new Instructor();
        System.out.println(".........................................................................");
        System.out.println("Press 1. to see students on my modules");
        System.out.println("Press 2. for giving marks to students.");
        System.out.println(".........................................................................");
        int select = 0;
        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                select = scan.nextInt();
                while ((select > 2) || (select < 1)){
                    System.out.println(".........................................................................");
                    System.out.println("Please enter a valid selection for services");
                    select = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println(".........................................................................");
                System.out.println("Please enter a valid selection for services");
                invalidInput = true;
                scan.nextLine();
            }
        } while (invalidInput);

        if(select== 1){
            instructor.studentsOnMyModules();
        }else{
            instructor.giveMarksToStudents();
        }

    }


    public static void main(String[] args) {
        CourseManagementSystem system = new CourseManagementSystem();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our Herald College. Our management system provides different services to different department !!");
        System.out.println(".........................................................................");
        System.out.println("Press 1. for STUDENT department services");
        System.out.println("Press 2. for INSTRUCTOR department services.");
        System.out.println("Press 3. for College Administrator services");
        System.out.println(".........................................................................");
        int select = 0;
        boolean invalidInput;
        do {
            invalidInput = false;
            try {
                select = scanner.nextInt();
                while ((select > 3) || (select < 1)){
                    System.out.println(".........................................................................");
                    System.out.println("Please enter a valid selection for services !!");
                    select = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println(".........................................................................");
                System.out.println("Please enter a valid selection for services !!");
                invalidInput = true;
                scanner.nextLine();
            }
        } while (invalidInput);

        if(select == 1){
            system.student();
        }else if(select == 2){
            system.instructor();
        }else{
            system.administrator();
        }

    }
}