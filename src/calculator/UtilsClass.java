package calculator;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class UtilsClass {
    // todo change from hard coded path to dynamic path (so it can run on any system)
    final static String RESULT_FILE = "D:\\IdeaProjects\\src\\calculator\\Results.txt";

    // todo the three variables under are only used in printResult() --> make it local
    final static String RESULT_COLUMN = "results";
    final static String DB_NAME = "calculator_db";
    private static final String PREP_INSERT_STATEMENT = "INSERT INTO " + DB_NAME + "  (" + RESULT_COLUMN + ") VALUES " + " (?);";

    public static void showPreviousResultsFromDB() {
        Connection con = getDbConnection();
        try {
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM `calculator_db`");
            while (result.next()) {
                double resFromDb = result.getDouble("results");
                System.out.println(resFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getDbConnection() {
        Connection conn = null;

        // todo remove all unused code --> connectionProps, line 44 etc
        Properties connectionProps = new Properties();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/mCAjmMzagg", "mCAjmMzagg", "nJkeBH4czo");
            //conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.107:5432/dvdrental","postgres","cbr900rr!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    // todo if this method is unused delete it
    public static void showPreviousResults() {
        try {
            File outPutFile = new File(RESULT_FILE); //create a new file object
            if (!outPutFile.exists()) {
                outPutFile.createNewFile(); //if file doesn't exist, create one
            }
            FileReader fileReader = new FileReader(outPutFile);   //read the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);  //creates a buffering input stream to read line by line
            StringBuffer stringBuffer = new StringBuffer();    //constructs a string buffer with no characters

            String line;
            while ((line = bufferedReader.readLine()) != null) //till end of file
            {
                stringBuffer.append(line);      //appends line to string buffer
                stringBuffer.append(System.lineSeparator());
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


            //print to results column in my remotemysql db
            Connection con = getDbConnection();
            try {
                PreparedStatement preparedStatement = con.prepareStatement(PREP_INSERT_STATEMENT);
                preparedStatement.setDouble(1, answer);
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            //print to file
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
