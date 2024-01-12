import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Tourism {
    static Connection c;
    static Statement st;
    static HashMap<Integer, String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement pst;

    // main method
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        // loading driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // create connection
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tourism", "root", "");
        st = c.createStatement();

        // method to insert initial data
        Initialization.getInitialDetails();
        // createTables();
        // insertPictures();
        while (true) {
            System.out.println("""
                    1. add user\s
                    2. cancel the registration \s
                    3. payment\s
                    0. exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (choice) {
                case 1 -> {
                    System.out.println();
                    User.addUser();
                }

                case 2 -> {
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter trip id: ");
                    int t_id = sc.nextInt();
                    Payment.cancellation(name, t_id);
                    System.out.println();
                }

                case 3 -> {
                    System.out.print("Enter your name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter trip id: ");
                    int t_id1 = sc.nextInt();
                    Payment.clearPayment(name1, t_id1);
                    System.out.println();
                }

                case 0 -> {
                    System.exit(choice);
                    System.out.println();
                }

                default -> System.out.println("invalid case !! \n please choose valid case ");
            }
        }
    }
}
