import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User extends Payment {
    static Connection c;
    static Statement st;
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement pst;

    public static void addUser() throws SQLException, IOException {
        ResultSet rs = st.executeQuery("SELECT * FROM trip");
        while (rs.next()) {
            System.out.println(rs.getInt("trip_id") + ".   Trip Name: "
                    + rs.getString("trip_name") + "   No. of days: "
                    + Payment.calculateDateDifference(rs.getDate("start_date"), rs.getDate("end_date"))
                    + "   Rates:" + rs.getDouble("rates"));
        }
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        rs = st.executeQuery("SELECT trip_name FROM trip WHERE trip_id = " + choice);
        switch (choice) {
            case 1:
                if (rs.next())
                    userInformation(rs.getString("trip_name"), choice);
                break;

            case 2:
                if (rs.next())
                    userInformation(rs.getString("trip_name"), choice);
                break;

            case 3:
                if (rs.next())
                    userInformation(rs.getString("trip_name"), choice);
                break;

            case 4:
                if (rs.next())
                    userInformation(rs.getString("trip_name"), choice);
                break;

            default:
                System.out.println("Enter valid choice!!!");
                break;
        }
    }

    private static void userInformation(String t_name, int t_id) throws SQLException, IOException {
        pst = c.prepareStatement(
                "INSERT INTO " + t_name + " (user_name, phone_no, alternative_no, email, trip_id) VALUES (?,?,?,?,?)");
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        pst.setString(1, name);
        System.out.print("Enter number: ");
        pst.setLong(2, sc.nextLong());
        System.out.print("Enter alternative number: ");
        pst.setLong(3, sc.nextLong());
        sc.nextLine();
        System.out.print("Enter email: ");
        pst.setString(4, sc.nextLine());
        pst.setInt(5, t_id);
        pst.executeUpdate();
        pst = c.prepareStatement(
                "INSERT INTO payment (payment_mode, user_id, trip_id, payment_status) VALUES (?,?,?,?)");
        System.out.print("is Payment (paid/pending)?: ");
        String payment_status = sc.nextLine();
        pst.setString(4, payment_status);
        ResultSet rs = st.executeQuery("SELECT user_id FROM " + t_name + " WHERE user_name = '" + name + "' ");
        int u_id = 0;
        if (rs.next())
            u_id = rs.getInt("user_id");
        pst.setInt(2, u_id);
        pst.setInt(3, t_id);
        if (payment_status.equalsIgnoreCase("paid")) {
            System.out.print("Payment Mode: ");
            pst.setString(1, sc.nextLine().toLowerCase());
        } else {
            pst.setString(1, "Null");
        }
        pst.executeUpdate();
        rs = st.executeQuery("SELECT payment_id FROM payment WHERE user_id =" + u_id);
        pst = c.prepareStatement("UPDATE " + t_name + " SET payment_id = ? WHERE user_id = ?");
        if (rs.next())
            pst.setInt(1, rs.getInt("payment_id"));
        pst.setInt(2, u_id);
        pst.executeUpdate();
        if (payment_status.equalsIgnoreCase("paid"))
            Payment.bill(t_name, t_id, u_id);
    }
}
