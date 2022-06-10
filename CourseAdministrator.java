package SudipNeupane_2050436;

import java.io.*;
import java.util.*;

public class CourseAdministrator extends Course implements Modules {

    private String addNewCourse;
    private String changeCourseName;
    private String newModuleName;
    private  String newInstructor;

    public void setAddNewCourse(String addNewCourse) {
        this.addNewCourse = addNewCourse;
    }
    public void setChangeCourseName(String changeCourseName) {
        this.changeCourseName = changeCourseName;
    }
    public void setNewModuleName(String newModuleName) {
        this.newModuleName = newModuleName;
    }
    public void setNewInstructor(String newInstructor) {
        this.newInstructor = newInstructor;
    }
    public String getAddNewCourse() {
        return addNewCourse;
    }
    public String getChangeCourseName() {
        return changeCourseName;
    }
    public String getNewModuleName() {
        return newModuleName;
    }
    public String getNewInstructor() {
        return newInstructor;
    }

    Scanner scan = new Scanner(System.in);
    ArrayList<String> modules = new ArrayList<String>();
    ArrayList<String> modules6 = new ArrayList<String>();
    ArrayList course = new ArrayList();
    ArrayList<ArrayList> allInstructor = new ArrayList<ArrayList>();
    ArrayList<ArrayList> allModule = new ArrayList<ArrayList>();
    

    @Override
    public void addNewCourse() {
        String newCourse;

        try {
            System.out.println(".........................................................................");
            System.out.println("Enter name of a course that you wants to add");
            System.out.println(".........................................................................");

            newCourse = scan.nextLine();
            setAddNewCourse(newCourse);

            FileReader readCourse = new FileReader("course.txt");
            Scanner scanner = new Scanner(readCourse);
            String line = scanner.nextLine();
            String[] courses = line.split("-->>");
            int length = courses.length;
            for(int i = 0; i < length; i++){
                course.add(courses[i]);
            }

            course.add(getAddNewCourse());

            FileWriter writeCourse = new FileWriter("course.txt");
            Iterator iterator = course.iterator();
            while(iterator.hasNext()){
                writeCourse.write(iterator.next()+"-->>");
            }
            writeCourse.close();

            System.out.println(".........................................................................");
            System.out.println("New course has been successfully added to course table.");
            System.out.println(".........................................................................");


        } catch (IOException e) {
            System.out.println("Error on file");
        }

    }

    @Override
    public void renameCourse() {
        String newName;

        try {
            System.out.println("Select a course that you want to rename");
            int select;
            FileReader readCourse = new FileReader("course.txt");
            Scanner scanner = new Scanner(readCourse);
            String line = scanner.nextLine();
            String[] courses = line.split("-->>");
            int length = courses.length;
            System.out.println(".........................................................................");
            for(int i = 0; i < length; i++){
                System.out.println("Press "+(i+1)+". to rename "+courses[i]);
                course.add(courses[i]);
            }
            //System.out.println(course);
            System.out.println(".........................................................................");
            select = scan.nextInt();
            while((select > length) || (select < 1)){
                System.out.println("Please select valid option");
                select = scan.nextInt();
            }
            scan.nextLine();
            int index = select-1;
            System.out.println(".........................................................................");
            System.out.println("Enter a new name for changing course "+course.get(index)+".");
            System.out.println(".........................................................................");
            newName = scan.nextLine();
            setChangeCourseName(newName);

            Object same = course.get(index);
            Object obj = newName;
            course.set(index,obj);

            FileWriter writeCourse = new FileWriter("course.txt");
            Iterator iterator = course.iterator();
            while(iterator.hasNext()){
                writeCourse.write(iterator.next()+"-->>");
            }
            writeCourse.close();

            try{
                FileReader readStudent = new FileReader("students.txt");
                Scanner scanStudent = new Scanner(readStudent);

                while(scanStudent.hasNextLine()){
                    ArrayList student = new ArrayList();
                    String sLine = scanStudent.nextLine();
                    String[] sCourse = sLine.split("-->>");
                    int len = sCourse.length;
                    Object sObj = sCourse[2];

                    if(same.equals(sObj)){
                        for(int i = 0; i < len; i++){
                            student.add(sCourse[i]);
                        }
                        student.set(2,getChangeCourseName());
                    }else{
                        for(int i = 0; i < len; i++){
                            student.add(sCourse[i]);
                        }
                    }
                    allInstructor.add(student);
                }
                File newFile = new File("students.txt");
                FileWriter writeStudent = new FileWriter(newFile);

                for(int i = 0; i < allInstructor.size(); i++){
                    for(int j = 0; j < 8; j++){
                        writeStudent.write(allInstructor.get(i).get(j)+"-->>");
                    }
                    writeStudent.write("\n");
                }
                writeStudent.close();

                byte[] array = new byte[520];
                try {
                    String old = same+"modules.txt";
                    FileInputStream sourceFile = new FileInputStream(old);
                    String file = newName+"modules.txt";
                    FileOutputStream destFile = new FileOutputStream(file);
                    sourceFile.read(array);
                    destFile.write(array);

                    sourceFile.close();
                    destFile.close();

                    try {
                        String oldFile = same+"modules.txt";
                        File f = new File(oldFile);
                        if(f.delete()) {
                            System.out.println(".........................................................................");
                            System.out.println("Course has been successfully renamed in course table.");
                            System.out.println(".........................................................................");
                        }
                    } catch(Exception e) {

                    }
                }catch (Exception e) {

                }
            }catch(FileNotFoundException ex){

            }
        } catch (IOException | InputMismatchException e) {
            System.out.println("Please provide valid course option");
        }
    }

    @Override
    public void removeCourse() {

        try {
            System.out.println("Select a course that you want to remove");
            int select;
            FileReader readCourse = new FileReader("course.txt");
            Scanner scanner = new Scanner(readCourse);
            String line = scanner.nextLine();
            String[] courses = line.split("-->>");
            int length = courses.length;
            System.out.println(".........................................................................");
            for (int i = 0; i < length; i++) {
                System.out.println("Press " + (i + 1) + ". for removing course " + courses[i]);
                course.add(courses[i]);
            }

            System.out.println(".........................................................................");
            select = scan.nextInt();
            while ((select > length) || (select < 1)) {
                System.out.println("Please select valid option");
                select = scan.nextInt();
            }
            scan.nextLine();
            int index = select -1;
            Object same = course.get(index);

            course.remove(index);

            FileWriter writeCourse = new FileWriter("course.txt");
            Iterator iterator = course.iterator();
            while(iterator.hasNext()){
                writeCourse.write(iterator.next()+"-->>");
            }
            writeCourse.close();

            try {
                String oldFile = same+"modules.txt";
                File f = new File(oldFile);
                if(f.delete()) {
                    System.out.println(".........................................................................");
                    System.out.println("Course has been successfully renamed in course table.");
                    System.out.println(".........................................................................");
                } else {

                }
            } catch(Exception e) {

            }
            System.out.println("The course has been successfully removed.");
        } catch (IOException | InputMismatchException ex) {
            System.out.println(".........................................................................");
            System.out.println("Please provide valid information");
        }
    }

    @Override
    public void addModulesToCourse() {
        try{
            System.out.println("Select a course that you want to add modules");
            int select;
            FileReader readCourse = new FileReader("course.txt");
            Scanner scanner = new Scanner(readCourse);
            String line = scanner.nextLine();
            String[] courses = line.split("-->>");
            int length = courses.length;
            System.out.println(".........................................................................");
            for (int i = 0; i < length; i++) {
                System.out.println("Press " + (i + 1) + ". for removing course " + courses[i]);
                course.add(courses[i]);
            }

            System.out.println(".........................................................................");
            select = scan.nextInt();
            while ((select > length) || (select < 1)) {
                System.out.println(".........................................................................");
                System.out.println("Please select valid option");
                select = scan.nextInt();
            }
            scan.nextLine();
            int index = select-1;
            Object same = course.get(index);
            int delete = 0;
            String fileName = same+"modules.txt";
            File newFile = new File(fileName);
            if(newFile.exists()){
                System.out.println(".........................................................................");
                System.out.println(same+" course already have full modules in it. You can only remove or rename the module." +
                        "\nAs we provide 4 modules across all level, with 2 extra elective on level 6.");
                System.out.println(".........................................................................");
            }else{
                FileWriter writerNew = new FileWriter(fileName);
                int a = 4;
                while(a < 7){
                    if(a == 6){
                        System.out.println("Add 6 modules for level "+a+" students.");
                        System.out.println(".........................................................................");
                        for(int i = 0 ; i < 6; i++){
                            System.out.println("Module "+(i+1)+" for level "+a+" students.");
                            modules6.add(scan.nextLine());
                            delete++;
                            System.out.println(".........................................................................");
                        }
                    }else{
                        System.out.println("Add 4 modules for level "+a+" students.");
                        System.out.println(".........................................................................");

                        for(int i = 0 ; i < 4; i++){
                            System.out.println("Module "+(i+1)+" for level "+a+" students.");
                            modules.add(scan.nextLine());
                            delete++;
                            System.out.println(".........................................................................");
                        }
                    }
                   a++;
                }

                Iterator iterator = modules.iterator();
                for (int p = 0; p < 2; p++){
                    int b = 1;
                    while (iterator.hasNext() && b < 5){
                        writerNew.write(iterator.next()+"-->>");
                        b++;
                    }
                    writerNew.write("\n");
                }

                Iterator iterator1 = modules6.iterator();
                while (iterator1.hasNext()){
                    writerNew.write(iterator1.next()+"-->>");
                }
                writerNew.close();

            }
            if(delete != 14){
                try {
                    String file = same+"modules.txt";
                    File f = new File(file);
                    if(f.delete()) {

                    } else {
                        System.out.println("failed");
                    }
                } catch(Exception e) {

                }

            }

        }catch(IOException | InputMismatchException ex){
            System.out.println("Please provide valid information");
        }

    }

    @Override
    public void renameModule() {
        System.out.println(".........................................................................");
        System.out.println("Which course module you want to rename");
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
                    String same = "";
                    newModuleName = "";

                    for (int p = 0; p < cn; p++){

                        if (chooseCourse == (p+1)){
                            try{
                                String fileName = course[p]+"modules.txt";
                                FileReader readModules = new FileReader(fileName);
                                Scanner scannerModule = new Scanner(readModules);

                                int chooseModule;
                                boolean invalidModule;
                                do {
                                    System.out.println(".........................................................................");
                                    System.out.println("Please enter valid course level for module rename[4/5/6]");
                                    System.out.println(".........................................................................");

                                    invalidModule = false;
                                    try {
                                        chooseModule = scan.nextInt();
                                        while ((chooseModule < 4) || (chooseModule > 6)) {
                                            System.out.println(".........................................................................");
                                            System.out.println("Please select valid course level option for module rename[4/5/6]");
                                            System.out.println(".........................................................................");
                                            chooseModule = scan.nextInt();
                                        }
                                        int mn = 4;
                                        while(scannerModule.hasNext()) {
                                            ArrayList newModule = new ArrayList();
                                            String s1 = scannerModule.nextLine();
                                            String[] line = s1.split("-->>");
                                            int ml = line.length;
                                            if(chooseModule == mn){
                                                for(int i = 0; i < ml; i++){
                                                    System.out.println("Press "+(i+1)+". for changing "+line[i].toUpperCase());
                                                    newModule.add(line[i]);
                                                }
                                                System.out.println(".........................................................................");

                                                int choose=0;
                                                boolean invalidSelect;
                                                do {
                                                    System.out.println("Please select valid module option for rename");
                                                    System.out.println(".........................................................................");
                                                    invalidSelect = false;
                                                    try {
                                                        choose = scan.nextInt();
                                                        while ((choose < 1) || (choose > ml)) {
                                                            System.out.println("Please select valid module option.");
                                                            System.out.println(".........................................................................");
                                                            choose = scan.nextInt();
                                                        }
                                                    } catch(InputMismatchException ex){
                                                        System.out.println(".........................................................................");
                                                        System.out.println("Please select valid module option. ");
                                                        invalidSelect = true;
                                                        scan.nextLine();
                                                    }
                                                } while (invalidSelect);
                                                scan.nextLine();
                                                int index = choose-1;
                                                same = line[index];
                                                System.out.println(".........................................................................");
                                                System.out.println("Enter new name for module "+line[index]);
                                                System.out.println(".........................................................................");

                                                String newName = scan.nextLine();
                                                setNewModuleName(newName);
                                                newModule.set(index,getNewModuleName());
                                            }else{
                                                for (int q = 0; q < ml; q++){
                                                    newModule.add(line[q]);
                                                }
                                            }
                                            allModule.add(newModule);
                                            mn++;
                                        }
                                        ///////write for module
                                        FileWriter writeModule = new FileWriter(fileName);

                                        for(int i = 0; i < allModule.size(); i++){
                                            if(i == 2){
                                                for(int j = 0; j < 6; j++){
                                                    writeModule.write(allModule.get(i).get(j)+"-->>");
                                                }
                                            }else{
                                                for(int j = 0; j < 4; j++){
                                                    writeModule.write(allModule.get(i).get(j)+"-->>");
                                                }
                                            }
                                            writeModule.write("\n");
                                        }
                                        writeModule.close();
                                        //////////write for module

                                        try {
                                            String s = scannerModule.nextLine();
                                        }catch(NoSuchElementException ex){

                                        }
                                    } catch (InputMismatchException e) {
                                        invalidModule = true;
                                        scan.nextLine();
                                    }
                                } while (invalidModule);
                                System.out.println(".........................................................................");

                                System.out.println("Module has been successfully rename in all field ");

                            }catch (FileNotFoundException ex){
                                System.out.println(".........................................................................");
                                System.out.println("This is a new course. Modules has not assigned yet by the administrator");
                                System.exit(0);
                            }
                        }
                    }


                    //////////in student
                    Object same1 = same;
                    try{
                        ArrayList<ArrayList> listStd = new ArrayList<ArrayList>();
                        FileReader readStudent = new FileReader("students.txt");
                        Scanner scanStd = new Scanner(readStudent);
                        while(scanStd.hasNextLine()){
                            ArrayList std = new ArrayList();
                            String line = scanStd.nextLine();
                            String[] lstd = line.split("-->>");
                            std.add(lstd[0]);
                            std.add(lstd[1]);
                            std.add(lstd[2]);
                            std.add(lstd[3]);
                            for(int i = 4; i < lstd.length; i++){
                                Object ostd = lstd[i];
                                if(same1.equals(ostd)){
                                    std.add(newModuleName);
                                }else{
                                    std.add(lstd[i]);
                                }
                            }
                            listStd.add(std);
                        }

                        File newFile = new File("students.txt");
                        FileWriter writeStudent = new FileWriter(newFile);

                        for(int i = 0; i < listStd.size(); i++){
                            for(int j = 0; j < 8; j++){
                                writeStudent.write(listStd.get(i).get(j)+"-->>");
                            }
                            writeStudent.write("\n");
                        }
                        writeStudent.close();
                    }catch(IOException ex){

                    }
                    /////in student

                    //////in marks
                    try{
                        ArrayList<ArrayList> listmrk = new ArrayList<ArrayList>();
                        FileReader readStudent = new FileReader("marks.txt");
                        Scanner scanStd = new Scanner(readStudent);
                        while(scanStd.hasNextLine()){
                            ArrayList mrk = new ArrayList();
                            String line = scanStd.nextLine();
                            String[] lstd = line.split("-->>");
                            mrk.add(lstd[0]);
                            mrk.add(lstd[1]);
                            mrk.add(lstd[2]);
                            Object ostd = lstd[3];
                            if(same1.equals(ostd)){
                                mrk.add(newModuleName);
                            }else{
                                mrk.add(lstd[3]);
                            }
                            mrk.add(lstd[4]);

                            listmrk.add(mrk);
                        }

                        File newFile = new File("marks.txt");
                        FileWriter writeStudent = new FileWriter(newFile);

                        for(int i = 0; i < listmrk.size(); i++){
                            for(int j = 0; j < 5; j++){
                                writeStudent.write(listmrk.get(i).get(j)+"-->>");
                            }
                            writeStudent.write("\n");
                        }
                        writeStudent.close();
                    }catch(IOException ex){

                    }
                    ////in marks

                    ///in instructor
                    try{
                        int[] ins = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                        ArrayList<ArrayList> listStd = new ArrayList<ArrayList>();
                        FileReader readStudent = new FileReader("instructor.txt");
                        Scanner scanStd = new Scanner(readStudent);
                        int ab = 0;
                        while(scanStd.hasNextLine()){
                            ArrayList std = new ArrayList();
                            String line = scanStd.nextLine();
                            String[] lstd = line.split("-->>");
                            std.add(lstd[0]);
                            std.add(lstd[1]);
                            ins[ab] = lstd.length;
                            for(int i = 2; i < lstd.length; i++){
                                Object ostd = lstd[i];
                                if(same1.equals(ostd)){
                                    std.add(newModuleName);
                                }else{
                                    std.add(lstd[i]);
                                }
                            }
                            listStd.add(std);
                            ab++;
                        }

                        File newFile = new File("instructor.txt");
                        FileWriter writeStudent = new FileWriter(newFile);

                        for(int i = 0; i < listStd.size(); i++){
                            for(int j = 0; j < ins[i]; j++){
                                writeStudent.write(listStd.get(i).get(j)+"-->>");
                            }
                            writeStudent.write("\n");
                        }
                        writeStudent.close();
                    }catch(IOException ex){

                    }
                    ///in instructor
                } catch (InputMismatchException e) {
                    System.out.println(".........................................................................");

                    System.out.println("Please enter a valid course number ");
                    System.out.println(".........................................................................");

                    invalidCourse = true;
                    scan.nextLine();
                }
            } while (invalidCourse);
        } catch (IOException ex) {
            System.out.println(".........................................................................");

            System.out.println("Please help us by entering valid information !!!");
        }
    }

    public void removeInstructor(){
        int remove;
        System.out.println(".........................................................................");
        System.out.println("Which instructor you want to remove from modules");
        System.out.println(".........................................................................");
        try{
            FileReader readSir = new FileReader("instructor.txt");
            Scanner scanSir = new Scanner(readSir);
            int ab= 0;
            int[] cd = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            while(scanSir.hasNextLine()){
                ArrayList ins = new ArrayList();
                String line = scanSir.nextLine();
                String[] sir = line.split("-->>");
                cd[ab] = sir.length;
                System.out.println("Press "+(ab+1)+". for removing instructor "+sir[1].toUpperCase());
                for(int i = 0; i < sir.length; i++){
                    ins.add(sir[i]);
                }
                allInstructor.add(ins);
                ab++;
            }
            System.out.println(".........................................................................");
            remove = scan.nextInt();
            System.out.println(".........................................................................");

            int index = remove - 1;
            scan.nextLine();
            System.out.println("Enter new instructor name for modules");
            System.out.println(".........................................................................");
            String newSir = scan.nextLine();
            setNewInstructor(newSir);
            System.out.println(".........................................................................");

            ArrayList newList = new ArrayList();
            for (int i = 0; i < allInstructor.size(); i++){
                if (index == i){
                    newList = allInstructor.get(index);
                }
            }
            newList.set(1,getNewInstructor());
            allInstructor.set(index, newList);

            try{
                FileWriter writeSir = new FileWriter("instructor.txt");
                for (int i = 0; i< allInstructor.size(); i++){
                    for(int j = 0 ; j < cd[i]; j++){
                        writeSir.write(allInstructor.get(i).get(j)+"-->>");
                    }
                    writeSir.write("\n");
                }
                writeSir.close();
                System.out.println(".........................................................................");
                System.out.println("Successfully removed replaced new instructor");
                System.out.println(".........................................................................");

            }catch (IOException ex){

            }

        }catch(FileNotFoundException ex){
            System.out.println("could not found instructor file");
        }
    }

    public void generateReportSlip(){
        long contact = 0;
        String name = "";
        String course = "";
        int level = 0;
        String[] moduless = {"","","",""};
        float[] marks = {0,0,0,0};
        float percentage = 0;
        float sum = 0;
        System.out.println(".........................................................................");
        System.out.println("Enter the student ID for creating result slip");
        System.out.println(".........................................................................");
        try{
            contact = scan.nextLong();

        }catch(InputMismatchException ex ){
            System.out.println(".........................................................................");
            System.out.println("Please enter valid digital format of student ID");
            System.exit(0);
        }
        try{
            FileReader readMark = new FileReader("marks.txt");
            Scanner scanMark = new Scanner(readMark);
            int ab = 0;
            while(scanMark.hasNextLine()){
                String line = scanMark.nextLine();
                String[] split = line.split("-->>");
                long id = Long.parseLong(split[2]);
                if(contact == id){
                    name = split[1];
                    moduless[ab] = split[3];
                    marks[ab] = Float.parseFloat(split[4]);
                    sum = sum + marks[ab];
                    ab++;
                }
            }

            FileReader readStudent = new FileReader("students.txt");
            Scanner scanStd = new Scanner(readStudent);
            int cd = 0;
            while (scanStd.hasNextLine()){
                String line = scanStd.nextLine();
                String[] split = line.split("-->>");
                long id = Long.parseLong(split[1]);
                if(contact == id){
                    course = split[2];
                    level = Integer.parseInt(split[3]);
                    cd++;
                }
            }

            for (int j = 0; j < ab; j++){
                if(marks[j] < 40){
                    System.out.println(".........................................................................");
                    System.out.println(name.toUpperCase()+" failed in module "+moduless[j]+". The student received only "+marks[j]);
                    System.out.println("\tStudent who failed in any of the module will not get result slip");
                    System.exit(0);
                }
            }

            if(cd == 0){
                System.out.println(".........................................................................");
                System.out.println("The provided student id does not match with any of our student as per records.");
                System.exit(0);
            }
            scan.nextLine();
            String either = "Y";
            String input = "";
            percentage = sum/ab;
            if(ab == 0){
                System.out.println(".........................................................................");
                System.out.println("The student has not yet received marks in any modules. Please contact to his module instructor.");
            } else if(ab < 4){
                System.out.println(".........................................................................");
                System.out.println("The student has not got marks in his all module. Please contact to his module instructor.");
                System.out.println("--------- DOU YOU STILL WISH TO PUBLISH HIS RESULT SLIP?[y/n] ----------");
                input = scan.nextLine();
                if(either.equalsIgnoreCase(input)){
                    System.out.println("\n+-----------------------------------------------------------------------------");
                    System.out.println("| ---- Student Report Card ----------------- Herald College, Kathmandu  -----");
                    System.out.println("| -------------------------- Name: "+name.toUpperCase()+" ---------------------------");
                    System.out.println("|                          Student ID: "+contact);
                    System.out.println("|                          Course    : "+course.toUpperCase());
                    System.out.println("|                          Level     : "+level);
                    System.out.println("| ===========================================================================");
                    System.out.println("| \t\tMODULES");
                    for(int j = 0; j < ab; j++){
                        System.out.println("| \t"+(j+1)+". "+moduless[j]+"           "+marks[j]);
                    }
                    System.out.println("| \t\tpercentage:                       "+percentage);
                    System.out.println("| ---------------------------------------------------------------------------");
                    System.out.println("| ");
                    if(percentage < 40){
                        System.out.println("| \tThe Student scored "+percentage+"% in overall modules. The student who\n| \t" +
                                "receive less than 40% in overall module can not be upgrade to next level.");
                    }else if(percentage < 60){
                        System.out.println("| \tThe Student scored "+percentage+"% in overall modules. It is an average\n| \t" +
                                "score but the student can upgrade to next level.");
                    }else if (percentage < 80){
                        System.out.println("| \tThe Student scored "+percentage+"% in overall modules. That was an good\n| \t" +
                                "performance by the student and can upgrade to next level.");
                    }else{
                        System.out.println("| \tThe Student scored "+percentage+"% in overall modules.That was an excellent\n| \t" +
                                "performance buy the student and will receive a gift hamper from university with upgrade to\n| \tnext level.");
                    }
                    System.out.println("| ");
                    System.out.println("|                                                          Regards,");
                    System.out.println("|                                                   University Administrator,");
                    System.out.println("|                                                       Hemanga Gautam");
                    System.out.println("+----------------------------------------------------------------------------");
                }
            }else{
                System.out.println("\n+-----------------------------------------------------------------------------");
                System.out.println("| ---- Student Report Card ----------------- Herald College, Kathmandu  -----");
                System.out.println("| -------------------------- Name: "+name.toUpperCase()+" ---------------------------");
                System.out.println("|                          Student ID: "+contact);
                System.out.println("|                          Course    : "+course.toUpperCase());
                System.out.println("|                          Level     : "+level);
                System.out.println("| ===========================================================================");
                System.out.println("| \t\tMODULES");
                for(int j = 0; j < ab; j++){
                    System.out.println("| \t"+(j+1)+". "+moduless[j]+"           "+marks[j]);
                }
                System.out.println("| \t\t   percentage:                      "+percentage);
                System.out.println("| ---------------------------------------------------------------------------");
                System.out.println("| ");
                if(percentage < 40){
                    System.out.println("| \tThe Student scored "+percentage+"% in overall modules. The student who\n| \t" +
                            "receive less than 40% in overall module can not be upgrade to next level.");
                }else if(percentage < 60){
                    System.out.println("| \tThe Student scored "+percentage+"% in overall modules. It is an average\n| \t" +
                            "score but the student can upgrade to next level.");
                }else if (percentage < 80){
                    System.out.println("| \tThe Student scored "+percentage+"% in overall modules. That was an good\n| \t" +
                            "performance by the student and can upgrade to next level.");
                }else{
                    System.out.println("| \tThe Student scored "+percentage+"% in overall modules.That was an excellent\n| \t" +
                            "performance buy the student and will receive a gift hamper from university with upgrade to\n| \tnext level.");
                }
                System.out.println("| ");
                System.out.println("|                                                          Regards,");
                System.out.println("|                                                   University Administrator,");
                System.out.println("|                                                       Hemanga Gautam");
                System.out.println("+----------------------------------------------------------------------------");

            }

        }catch(IOException ex){
            System.out.println(".........................................................................");
            System.out.println("Students marks record doesnot found");
        }
    }

}
