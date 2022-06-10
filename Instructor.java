package SudipNeupane_2050436;

import java.io.*;
import java.util.*;

public class Instructor {

    private int instructorID;
    private String instructorName;
    private float studentMark;

    public void setInstructorID(int teacherID) {
        this.instructorID = teacherID;
    }
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    public void setStudentMark(float studentMark) {
        this.studentMark = studentMark;
    }

    public int getInstructorID() {
        return instructorID;
    }
    public String getInstructorName() {
        return instructorName;
    }
    public float getStudentMark() {
        return studentMark;
    }

    Scanner scan = new Scanner(System.in);
    ArrayList<ArrayList> marksDetail = new ArrayList<ArrayList>();

    public void studentsOnMyModules(){
        int teacherID;
        ArrayList array = new ArrayList();
        boolean invalid;
        do{
            System.out.println(".........................................................................");
            System.out.println("Sir, Please enter your university instructor ID");
            System.out.println(".........................................................................");
            invalid = false;

            try{
                teacherID = scan.nextInt();
                setInstructorID(teacherID);
                FileReader readTeacher = new FileReader("instructor.txt");
                Scanner scanTeacher = new Scanner(readTeacher);
                boolean h = true;
                int a = 0;
                while(scanTeacher.hasNextLine()){
                    String teacher = scanTeacher.nextLine();
                    String[] sir = teacher.split("-->>");
                    int sirID = Integer.parseInt(sir[0]);
                    int length = sir.length;
                    setInstructorName(sir[1]);
                    if(teacherID == sirID){
                        System.out.println(".........................................................................");
                        System.out.println("Welcome back "+getInstructorName().toUpperCase()+". Your modules are: ");
                        System.out.println(".........................................................................");
                        for(int i = 2; i < length; i++){
                            System.out.println("\t"+(i-1)+". "+sir[i]);
                        }
                        System.out.println(".........................................................................");

                        FileReader readStudent = new FileReader("students.txt");
                        Scanner scanStudent = new Scanner(readStudent);

                        while(scanStudent.hasNextLine()){
                            String record = scanStudent.nextLine();
                            String[] student = record.split("-->>");

                            int i = 2;    //sir
                            while (i < length){
                                for (int j = 4; j < 8; j++){
                                    Object tt =  sir[i];
                                    Object ss = student[j];
                                    h = tt.equals(ss);
                                    try{
                                        if (h) {
                                            array.add(student[0]);
                                            System.out.println(student[0]+"  ->  "+sir[i]);
                                            h = false;
                                            a++;
                                        }
                                    }catch(ArrayIndexOutOfBoundsException ex){

                                    }

                                } // for loop
                                i++;
                            } //while length loop
                        }
                    } // if id = sir
                }

                if(a == 0){
                    System.out.println("NO students are assigned on your modules.");
                    System.out.println(".........................................................................");

                }
            }catch(InputMismatchException | FileNotFoundException ex){
                System.out.println(".........................................................................");
                System.out.println("Sorry Sir, You might have entered wrong ID. Please try again ");
                invalid = true;
                scan.nextLine();
            }

        }while (invalid);

    }

    public void giveMarksToStudents(){
        int teacherID;
        boolean invalid;
        do{
            System.out.println(".........................................................................");
            System.out.println("Please enter your university instructor ID");
            System.out.println(".........................................................................");
            invalid = false;

            try{
                teacherID = scan.nextInt();
                setInstructorID(teacherID);
                FileReader readTeacher = new FileReader("instructor.txt");
                Scanner scanTeacher = new Scanner(readTeacher);
                boolean h;
                int b = 0;
                int a = 0;
                while(scanTeacher.hasNextLine()){
                    String teacher = scanTeacher.nextLine();
                    String[] sir = teacher.split("-->>");
                    int sirID = Integer.parseInt(sir[0]);
                    setInstructorName(sir[1]);
                    int length = sir.length;
                    if(teacherID == sirID){
                        System.out.println(".........................................................................");
                        System.out.println("Welcome back instructor, "+getInstructorName().toUpperCase()+". Your modules are: ");
                        System.out.println(".........................................................................");
                        for(int i = 2; i < length; i++){
                            System.out.println("\t"+(i-1)+". "+sir[i]);
                        }
                        System.out.println(".........................................................................");

                        // /////////////////////////

                        try{
                            FileReader readMark = new FileReader("marks.txt");
                            Scanner scanMarks = new Scanner(readMark);

                            while(scanMarks.hasNextLine()){

                                String line = scanMarks.nextLine();
                                String[] mak = line.split("-->>");
                                //cd[ab] = mak.length;
                                long id = Long.parseLong(mak[0]);
                                if(id == teacherID){
                                    a++;
                                }else{
                                    ArrayList mark1 = new ArrayList();
                                    for(int i = 0; i < mak.length; i++){
                                        mark1.add(mak[i]);

                                    }
                                    marksDetail.add(mark1);
                                }


                            }if (a > 0){
                                System.out.println("YOU HAVE ALREADY PROVIDED MARKS FOR YOUR STUDENT BUT YOU WILL BE REDIRECTED AGAIN FOR UPDATING");
                                System.out.println(".........................................................................");
                            }

                        }catch (FileNotFoundException ex){

                        }


                        ////////////////////////////////

                        FileReader readStudent = new FileReader("students.txt");
                        Scanner scanStudent = new Scanner(readStudent);

                        while(scanStudent.hasNextLine()){
                            String record = scanStudent.nextLine();
                            String[] student = record.split("-->>");

                            int i = 2;    //sir
                            while (i < length){
                                for (int j = 4; j < 8; j++){
                                    Object tt =  sir[i];
                                    Object ss = student[j];
                                    h = tt.equals(ss);
                                    try{
                                        //here
                                        if (h) {
                                            ArrayList newMark = new ArrayList();
                                            System.out.println("Mark for "+student[0].toUpperCase()+" in module "+sir[i].toUpperCase());
                                            System.out.println(".........................................................................");

                                            studentMark = scan.nextFloat();
                                            System.out.println(".........................................................................");
                                            newMark.add(getInstructorID());
                                            newMark.add(student[0]);
                                            newMark.add(student[1]);
                                            newMark.add(sir[i]);
                                            setStudentMark(studentMark);
                                            newMark.add(getStudentMark());
                                            marksDetail.add(newMark);
                                            b++;
                                        }
                                        //here
                                   }catch(ArrayIndexOutOfBoundsException ex){
                                        System.out.println();
                                    }

                                } // for loop
                                i++;
                            } //while length loop

                        }

                        try {
                            FileWriter writeSir = new FileWriter("marks.txt");
                            for (int i = 0; i < marksDetail.size(); i++){
                                for(int j = 0 ; j < 5; j++){
                                    writeSir.write(marksDetail.get(i).get(j)+"-->>");
                                }
                                writeSir.write("\n");
                            }
                            writeSir.close();
                            System.out.println("Successfully provided marks to student");
                            System.out.println(".........................................................................");

                        }catch (FileNotFoundException ex){

                        }

                    } // if id = sir
                }

                if(b == 0 && a == 0){
                    System.out.println("NO students are assigned on your modules.");
                    System.out.println(".........................................................................");
                }

            }catch(IOException ex){
                System.out.println(".........................................................................");
                System.out.println("Sorry Sir, You might have entered wrong ID. Please try again ");
                invalid = true;
                scan.nextLine();
            }

        }while (invalid);



    }

}

