import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Bills {
    static Connection c;
    static Statement st;
    static HashMap<Integer, String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement pst;

    // createBills method to create bill
    public static void createBills() throws IOException, SQLException {

        for (int i = 0; i < map.size(); i++) {
            PreparedStatement pst = c.prepareStatement("UPDATE " + map.get(i + 1) + " SET bill = ? WHERE user_id = ?");
            Statement s = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM " + map.get(i + 1));
            while (rs.next()) {
                ResultSet r1 = s.executeQuery(
                        "SELECT payment_status FROM payment where payment_id = " + rs.getInt("payment_id"));
                File file = new File("Bill_" + (i + 1) + rs.getInt("user_id") + ".txt");
                FileWriter fw = new FileWriter(file, false);
                try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write("/t             TRIP MANAGEMENT SYSTEM \n");
                    bw.newLine();
                    bw.write("Trip name : " + map.get(i + 1) + "              ");
                    bw.newLine();
                    bw.write("Trip id : " + i + 1 + "              \n");
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
                pst.setCharacterStream(1, new FileReader(file));
                pst.setInt(2, rs.getInt("user_id"));
                pst.executeUpdate();
            }
        }
    }
}
