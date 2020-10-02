package calculator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UtilsClass extends Calculator {
    final static String RESULT_FILE = "D:\\IdeaProjects\\src\\calculator\\Results.txt";

    public static void showPreviousResults() {
        try {
            File outPutFile = new File(RESULT_FILE); //create a new file object
            if (!outPutFile.exists()){
                outPutFile.createNewFile(); //if file doesn't exist, create one
            }
            FileReader fileReader = new FileReader(outPutFile);   //read the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  //creates a buffering input stream to read line by line
            StringBuffer stringBuffer =new StringBuffer();    //constructs a string buffer with no characters

            String line;
            while((line=bufferedReader.readLine())!=null) //till end of file
            {
                stringBuffer.append(line);      //appends line to string buffer
                stringBuffer.append("\n");     //line feed
            }
            System.out.println(stringBuffer);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

    }

    public static void printHeader() {
        clrscr(); //clears the screen in the CMD console, not in the intelliJ
        System.out.println("======================================================================");
        System.out.println("======welcome to calculator, -1 as first num will exit program========");
        System.out.println("======================================================================");
    }

    private static void clrscr() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static void printResult(double answer) {
        try {
            File outPutFile = new File(RESULT_FILE);
            FileWriter fileWriter = new FileWriter(outPutFile, true);//true means append
            PrintWriter printWriter = new PrintWriter(fileWriter);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());


            Result result = new Result(String.valueOf(answer));
            printWriter.println(result.getValue());
            printWriter.close();

            System.out.println("Calculated time: " + formatter.format(date));
            System.out.println("==========================");
            System.out.println("The answer is: " + answer);
            System.out.println("==========================");

            //these 3 lines to wait for any key from user to continue to clear the screen
            Scanner input = new Scanner(System.in);
            System.out.println("Any key to continue...");
            input.nextLine();

        } catch (IOException ex) {
            System.out.println("File cannot be opened");
        }
    }


}
