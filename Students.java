package SudipNeupane_2050436;

import java.io.*;
import java.util.*;

public class Students {

    private String studentName;
    private long studentContact;
    private int studentLevel;
    private String studentCourse;

    ArrayList<String> studentModule = new ArrayList<String>();

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setStudentContact(long contact){
        this.studentContact = contact;
    }
    public void setStudentLevel(int studentLevel) {
        this.studentLevel = studentLevel;
    }
    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentName() {
        return studentName;
    }
    public long getStudentContact() {
        return studentContact;
    }
    public int getStudentLevel() {
        return studentLevel;
    }
    public String getStudentCourse(){
        return studentCourse;
    }

    Scanner scan = new Scanner(System.in);

    public void takeEnrollment(){
        ArrayList student = new ArrayList();
        System.out.println(".........................................................................");
        System.out.println("Enter Your full Name: ");
        System.out.println(".........................................................................");
        String name = scan.nextLine();
        setStudentName(name);
        student.add(getStudentName());


        boolean invalidInput;
        do {
            System.out.println(".........................................................................");
            System.out.println("Enter your contact number: ");
            System.out.println(".........................................................................");
            invalidInput = false;
            try {
                long contact = scan.nextLong();
                setStudentContact(contact);
                //String has = Long.toString(contact);
                try{
                    FileReader read = new FileReader("students.txt");
                    Scanner number = new Scanner(read);

                    while(number.hasNextLine()){
                        String sc = number.nextLine();
                        //System.out.println(sc);
                        String[] num = sc.split("-->>");
                        long has = Long.parseLong(num[1]);
                        if(has == contact){
                            System.out.println("You already have record in our university ...");
                            System.exit(0);
                        }
                    }
                    read.close();
                }catch(IOException e){

                }

            } catch (InputMismatchException e) {
                System.out.println(".........................................................................");
                System.out.println("Please enter a valid digital number ");
                invalidInput = true;
                scan.nextLine();
            }
        } while (invalidInput);

        student.add(getStudentContact());

        System.out.println(".........................................................................");
        System.out.println("Which course you want to take?");
        System.out.println(".........................................................................");
        try {
            int chooseCourse;
            int cn;
            FileReader readCourse = new FileReader("course.txt");
            Scanner scannerCourse = new Scanner(readCourse);
            String sc = scannerCourse.nextLine();
            String[] course = sc.split("-->>");
            cn = course.length;


            boolean invalidCourse;
            do {
                for (int i = 0; i < cn; i++) {
                    System.out.println("Press " + (i + 1) + " for " + course[i]);
                }
                System.out.println(".........................................................................");

                invalidCourse = false;
                try {
                    chooseCourse = scan.nextInt();
                    while ((chooseCourse < 1) || (chooseCourse > cn)) {
                        System.out.println(".........................................................................");
                        System.out.println("Please select valid course option.");
                        System.out.println(".........................................................................");

                        chooseCourse = scan.nextInt();
                    }
                    for (int i = 1; i <= cn; i++) {
                        if (chooseCourse == i) {
                            setStudentCourse(course[i - 1]);
                            student.add(getStudentCourse());
                        }
                    }
                    for (int p = 0; p < cn; p++){
                        int mn;
                        if (chooseCourse == (p+1)){
                            //////////////////////////////
                            try{
                                String fileName = course[p]+"modules.txt";
                                FileReader readModules = new FileReader(fileName);
                                Scanner scannerModule = new Scanner(readModules);
                                mn = 4;

                                //for selecting module

                                int chooseModule;
                                boolean invalidModule;
                                do {
                                    System.out.println(".........................................................................");
                                    System.out.println("Please enter valid level for enrollment[4/5/6]");
                                    System.out.println(".........................................................................");

                                    invalidModule = false;
                                    try {
                                        chooseModule = scan.nextInt();
                                        while ((chooseModule < 4) || (chooseModule > 6)) {
                                            System.out.println(".........................................................................");
                                            System.out.println("Please select valid level option[4/5/6]");
                                            System.out.println(".........................................................................");
                                            chooseModule = scan.nextInt();
                                        }

                                        while(scannerModule.hasNext()) {
                                            if(chooseModule == mn){
                                                String s1 = scannerModule.nextLine();
                                                String[] modules = s1.split("-->>");
                                                int ml = modules.length;
                                                setStudentLevel(mn);
                                                student.add(getStudentLevel());
                                                if(mn == 6){
                                                    System.out.println(".........................................................................");
                                                    System.out.println("You have tow mandatory modules. You can only choose from elective");

                                                    for(int i = 0; i < 2; i++){
                                                        System.out.println((i+1)+". "+modules[i]);
                                                    }
                                                    studentModule.add(modules[0]);
                                                    student.add(modules[0]);
                                                    studentModule.add(modules[1]);
                                                    student.add(modules[1]);

                                                    // for elective modules
                                                    boolean invalidSelect;
                                                    do {
                                                        System.out.println(".........................................................................");
                                                        System.out.println("You have four elective modules. Please select two valid modules");
                                                        System.out.println(".........................................................................");

                                                        for(int i = 2; i < ml; i++){
                                                            System.out.println((i+1)+". "+modules[i]);
                                                        }
                                                        System.out.println(".........................................................................");

                                                        int choose, choose1;
                                                        invalidSelect = false;
                                                        try {
                                                            choose = scan.nextInt();
                                                            choose1 = scan.nextInt();
                                                            while (choose == choose1){
                                                                System.out.println(".........................................................................");
                                                                System.out.println("Please select different modules...");
                                                                System.out.println(".........................................................................");

                                                                choose = scan.nextInt();
                                                                choose1 = scan.nextInt();
                                                            }
                                                            while ((choose < 3) || (choose > ml) || (choose1 < 3) || (choose1 > ml)) {
                                                                System.out.println(".........................................................................");
                                                                System.out.println("Please select valid module option.");
                                                                System.out.println(".........................................................................");

                                                                choose = scan.nextInt();
                                                                choose1 = scan.nextInt();
                                                            }

                                                            for(int i = 3; i < (ml+1); i++){
                                                                if (choose == i){
                                                                    studentModule.add(modules[i-1]);
                                                                    student.add(modules[i-1]);
                                                                }
                                                                if (choose1 == i){
                                                                    studentModule.add(modules[i-1]);
                                                                    student.add(modules[i-1]);
                                                                }
                                                            }
                                                        } catch(InputMismatchException ex){
                                                            System.out.println(".........................................................................");
                                                            System.out.println("Please select valid elective modules.....");
                                                            invalidSelect = true;
                                                            scan.nextLine();
                                                            scan.nextLine();
                                                        }
                                                    } while (invalidSelect);

                                                } else{
                                                    System.out.println(".........................................................................");
                                                    System.out.println("You have four mandatory modules for level "+chooseModule);
                                                    System.out.println(".........................................................................");

                                                    for(int i = 0; i < ml; i++){
                                                        System.out.println(modules[i]);
                                                    }
                                                    studentModule.add(modules[0]);
                                                    student.add(modules[0]);
                                                    studentModule.add(modules[1]);
                                                    student.add(modules[1]);
                                                    studentModule.add(modules[2]);
                                                    student.add(modules[2]);
                                                    studentModule.add(modules[3]);
                                                    student.add(modules[3]);
                                                    System.out.println(".........................................................................");
                                                }

                                            }
                                            try {
                                                String s = scannerModule.nextLine();
                                            }catch(NoSuchElementException ex){

                                            }
                                            mn++;
                                        }

                                    } catch (InputMismatchException e) {
                                        invalidModule = true;
                                        scan.nextLine();
                                    }
                                } while (invalidModule);

                            }catch (FileNotFoundException ex){
                                System.out.println("This is a new course. Modules has not assigned yet by the administrator" +
                                        "\nFor enrollment you have to select other course");
                                System.exit(0);

                            }
                        }
                        ///////////////////////////////
                    }

                } catch (InputMismatchException e) {
                    System.out.println(".........................................................................");
                    System.out.println("Please enter a valid course number ");
                    invalidCourse = true;
                    scan.nextLine();
                }
            } while (invalidCourse);
            try{
                File file = new File("students.txt");
                FileWriter toStudent = new FileWriter(file,true);
                Iterator iterator = student.iterator();
                while(iterator.hasNext()){
                    toStudent.write(iterator.next()+"-->>");
                }
                toStudent.write("\n");
                toStudent.close();
                System.out.println(".........................................................................");
                System.out.println("Thank you for taking enrollment in our college. " +
                        "\nYour student Id is you contact number ("+getStudentContact()+"). " +
                        "\nYou need to enter your id whenever you ask for your records.");
            }catch(IOException ex){
                System.out.println(".........................................................................");
                System.out.println("Error creating students file");
                System.out.println(".........................................................................");

            }

            readCourse.close();
        } catch (IOException ex) {
            System.out.println(".........................................................................");
            System.out.println("Please help us by entering valid information !!!");
            System.out.println(".........................................................................");

        }

        //System.out.println(student);

    }

    public void seeMyInstructorDetails(){

        long checkId;
        boolean invalid;
        do{
            System.out.println(".........................................................................");
            System.out.println("Please enter your valid university student ID");
            System.out.println(".........................................................................");
            invalid = false;
            try {
                checkId = scan.nextLong();
                boolean flag = true;
                try{
                    FileReader readS = new FileReader("students.txt");
                    Scanner scanId = new Scanner(readS);
                    while(scanId.hasNextLine()){
                        String sr = scanId.nextLine();
                        String[] rs = sr.split("-->>");
                        long num = Long.parseLong(rs[1]);

                        if(checkId == num){
                            flag = false;
                            String[] module = {rs[4],rs[5],rs[6],rs[7]};
                            FileReader readI = new FileReader("instructor.txt");
                            Scanner scanI = new Scanner(readI);

                            while (scanI.hasNextLine()){
                                String I = scanI.nextLine();
                                String[] sir = I.split("-->>");
                                int length = sir.length;

                                int i = 2;
                                while (i < length){
                                    for (int j = 0; j < 4; j++){
                                        Object tt =  sir[i];
                                        Object ss = module[j];
                                        boolean h = tt.equals(ss);
                                        try{
                                            if (h) {
                                                System.out.println("Instructor " + sir[1].toUpperCase() + " is having your module " + module[j].toUpperCase()+".");
                                            }
                                        }catch(ArrayIndexOutOfBoundsException ex){

                                        }

                                    } // for loop
                                    i++;
                                } //while length loop

                            }

                        }

                    }

                }catch(IOException ex){
                    System.out.println(".........................................................................");
                    System.out.println("Could not found student record");
                    System.out.println(".........................................................................");
                }
                if(flag){
                    System.out.println(".........................................................................");
                    System.out.println("Sorry !!!\nCould found the records on your student ID");
                    System.out.println(".........................................................................");
                }

            }catch(InputMismatchException ex){
                System.out.println(".........................................................................");
                System.out.println("Please enter valid format of your student ID");
                invalid = true;
                scan.nextLine();
            }
        }while(invalid);


    }

}