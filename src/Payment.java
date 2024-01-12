import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Payment {
    static Connection c;
    static Statement st;
    static HashMap<Integer, String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement pst;

    // clearPayment method to completet the payment for booking
    public static void clearPayment(String name, int trip_id) throws SQLException, IOException {

        if (!map.containsKey(trip_id)) {
            System.out.println("No trip package exists!!");
            return;
        }

        ResultSet rs = st.executeQuery("SELECT user_id FROM " + map.get(trip_id) + " WHERE user_name = '" + name + "'");

        if (rs.next()) {
            int id = rs.getInt("user_id");

            rs = st.executeQuery("SELECT payment_id,payment_mode,payment_status FROM payment WHERE user_id = " + id
                    + " AND trip_id = " + trip_id);

            if (rs.next()) {
                System.out.print("Enter the payment mode :  ");
                String mode = sc.nextLine();
                if (rs.getString("payment_status").equalsIgnoreCase("Paid")) {
                    System.out.println("The following payment has already been completed!!");
                    return;
                }
                st.executeUpdate(
                        "UPDATE payment SET payment_mode = '" + mode + "', payment_status = 'Paid' WHERE user_id = "
                                + id + " AND trip_id = " + trip_id);

                bill(name, trip_id, id);

                System.out.println("Thank you!! Your payment has been completed successfully!!");
            }
        }
        System.out.println("No such record found!!");
    }

    // cancellation method to cancle the booking
    public static void cancellation(String name, int trip_id) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT user_id FROM " + map.get(trip_id) + " WHERE user_name = '" + name + "'");
        if (rs.next()) {
            System.out.print("Enter date of cancellation (In american format (YYYY/MM/DD)) : ");
            String date = sc.nextLine();
            Date d1 = Date.valueOf(date);
            ResultSet r = st.executeQuery("SELECT * FROM trip WHERE trip_id = " + trip_id);
            Date d = null;
            if (r.next()) {
                d = r.getDate("start_date");
            }

            long days = calculateDateDifference(d, d1);
            if (days >= 5) {
                st.executeUpdate("INSERT INTO cancellation(cancellation_date,trip_id, user_name, status) VALUES('" + d1
                        + "'," + trip_id + ",'" + name + "', 'Cancelled')");
            } else {
                st.executeUpdate("INSERT INTO cancellation(cancellation_date,trip_id, user_name, status) VALUES('" + d1
                        + "'," + trip_id + ",'" + name + "', 'Cancelled!!\n Refund not granted')");
            }
            st.executeUpdate("DELETE FROM " + map.get(trip_id) + " WHERE user_name = '" + name + "'");
            System.out.println("Cancellation is successfull!!");
            return;
        }
        System.out.println("No such record found!!");
    }

    // calculateDateDifference method to cancle the booking in due time and days
    protected static long calculateDateDifference(Date startDate, Date endDate) {
        long millisecondsDifference = Math.abs(endDate.getTime() - startDate.getTime());
        return millisecondsDifference / (24 * 60 * 60 * 1000); // Convert milliseconds to days
    }

    protected static String bill(String t_name, int t_id, int u_id) throws IOException, SQLException {
        PreparedStatement pst = c.prepareStatement("UPDATE " + t_name + " SET bill = ? WHERE user_id = ?");
        Statement s = c.createStatement();
        String pathName = "";
        ResultSet rs = st.executeQuery("SELECT * FROM " + t_name + " WHERE user_id = " + u_id);
        while (rs.next()) {
            pathName = "Bill_" + t_id + "_" + rs.getInt("user_id") + ".txt";
            ResultSet r1 = s
                    .executeQuery("SELECT payment_status FROM payment where payment_id = " + rs.getInt("payment_id"));
            FileWriter fw = new FileWriter(new File(pathName), false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("/t             TRIP MANAGEMENT SYSTEM \n");
                bw.newLine();
                bw.write("Trip name : " + t_name + "              ");
                bw.newLine();
                bw.write("Trip id : " + t_id + "              \n");
                bw.newLine();
                bw.write("User name : " + rs.getString("user_name") + "              ");
                bw.newLine();
                bw.write("User id : " + rs.getInt("user_id") + "              \n");
                bw.newLine();
                bw.write("User contact : " + rs.getBigDecimal("phone_no") + "              ");
                bw.newLine();
                bw.write("User email : " + rs.getString("email") + "              ");
                bw.write("User payment id : " + rs.getInt("payment_id") + "              \n");
                bw.newLine();
                if (r1.next())
                    bw.write("Payment status : " + r1.getString("payment_status"));
                bw.flush();
                fw.flush();
            }
            pst.setCharacterStream(1, new FileReader(pathName));
            pst.setInt(2, u_id);
            pst.executeUpdate();
        }
        return pathName;
    }
}
