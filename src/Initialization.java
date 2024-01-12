import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class Initialization {
    static Connection c;
    static Statement st;
    static HashMap<Integer, String> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static PreparedStatement pst;
     // createTables method to create tables and insert data into it
    public static void createTables() throws IOException, SQLException {

        // Creation and insertion of values in TRIP table
        insertPictures();

        /* Creation and insertion of values in Manali Trip table */
        String createManali = "CREATE TABLE Manali(user_id int PRIMARY KEY, user_name varchar(50), phone_no bigint," +
                "alternative_no bigint, email varchar(100), trip_id int, payment_id int, bill longtext);";
        st.executeUpdate(createManali);

        String insertManali = "INSERT INTO Manali (user_id, user_name, phone_no, alternative_no, email, trip_id, payment_id, bill) VALUES"
                +
                "(1, 'John Doe', 1234567890, 9876543210, 'john.doe@gmail.com', 1, 101, ''),"
                +
                "(2, 'Jane Smith', 9876543210, NULL, 'jane.smith@gmail.com', 1, 102, ''),"
                +
                "(3, 'Alice Johnson', 5453658525, 9846425356, 'alice.johnson@gmail.com', 1, 103, ''),"
                +
                "(4, 'Bob Wilson', 7647758697, 8576485743, 'bob.wilson@gmail.com', 1, 104, ''),"
                +
                "(5, 'Eva Brown', 9845756479, 8857468348, 'eva.brown@gmail.com', 1, 105, ''),"
                +
                "(6, 'David Lee', 4746353734, NULL, 'david.lee@gmail.com', 1, 106, ''),"
                +
                "(7, 'Grace Clark', 6243557096, 9836453422, 'grace.clark@gmail.com', 1, 107, ''),"
                +
                "(8, 'Frank Martin', 8857468348, NULL, 'frank.martin@gmail.com', 1, 108, ''),"
                +
                "(9, 'Olivia White', 3857663493, 9946563729, 'olivia.white@gmail.com', 1, 109, ''),"
                +
                "(10, 'George Wilson', 8576485743, NULL, 'george.wilson@gmail.com', 1, 110, '';";
        st.executeUpdate(insertManali);

        /* Creation and insertion of values in Kerela Trip table */
        String createKerela = "CREATE TABLE Kerela(user_id int PRIMARY KEY, user_name varchar(50), phone_no bigint, alternative_no bigint, \r\n"
                +
                "\t\t\temail varchar(100), trip_id int, payment_id int, bill longtext);";
        st.executeUpdate(createKerela);

        String insertKerela = "INSERT INTO kerela (user_id, user_name, phone_no, alternative_no, email, trip_id, payment_id, bill)\r\n"
                +
                "VALUES(1, 'Michael Johnson', 3857663493, 9946563729, 'michael.johnson@gmail.com', 2, 111, ''),\r\n"
                +
                "    (2, 'Sarah Wilson', 8576485743, NULL, 'sarah.wilson@gmail.com', 2, 112, ''),\r\n"
                +
                "    (3, 'Emily Davis', 6243557096, 8576485743, 'emily.davis@gmail.com', 2, 113, ''),\r\n"
                +
                "    (4, 'Matthew Martin', 9845756479, 9385766349, 'matthew.martin@gmail.com', 2, 114, ''),\r\n"
                +
                "    (5, 'Sophia Smith', 9876543210, NULL, 'sophia.smith@gmail.com', 2, 115, ''),\r\n"
                +
                "    (6, 'James White', 9946563729, 9385766349, 'james.white@gmail.com', 2, 116, ''),\r\n"
                +
                "    (7, 'Oliver Brown', 7647758697, 9845756479, 'oliver.brown@gmail.com', 2, 117, ''),\r\n"
                +
                "    (8, 'Emma Johnson', 5453658525, 6243557096, 'emma.johnson@gmail.com', 2, 118, ''),\r\n"
                +
                "    (9, 'Noah Smith', 9946563729, NULL, 'noah.smith@gmail.com', 2, 119, ''),\r\n"
                +
                "    (10, 'Ava Wilson', 1515151515, 7647758697, 'ava.wilson@gmail.com', 2, 120, '');";
        st.executeUpdate(insertKerela);

        /* Creation and insertion of values in Goa Trip table */
        String createGoa = "CREATE TABLE Goa(user_id int PRIMARY KEY, user_name varchar(50), phone_no bigint, alternative_no bigint, \r\n"
                +
                "\t\t\temail varchar(100), trip_id int, payment_id int, bill longtext);";
        st.executeUpdate(createGoa);

        String insertGoa = "INSERT INTO goa (user_id, user_name, phone_no, alternative_no, email, trip_id, payment_id, bill)\r\n"
                +
                "VALUES(1, 'Liam Anderson', 6243557096, 2223334444, 'liam.anderson@gmail.com', 3, 121, ''),\r\n"
                +
                "    (2, 'Olivia Smith', 9845756479, 9876543210, 'olivia.smith@gmail.com', 3, 122, ''),\r\n"
                +
                "    (3, 'Noah Johnson', 4445556666, NULL, 'noah.johnson@gmail.com', 3, 123, ''),\r\n"
                +
                "    (4, 'Emma Williams', 5556667777, 6667778888, 'emma.williams@gmail.com', 3, 124, ''),\r\n"
                +
                "    (5, 'Ava Brown', 7778889999, NULL, 'ava.brown@gmail.com', 3, 125, ''),\r\n"
                +
                "    (6, 'Liam Wilson', 8889990000, NULL, 'liam.wilson@gmail.com', 3, 126, ''),\r\n"
                +
                "    (7, 'Oliver Martin', 9990001111, 9845756479, 'oliver.martin@gmail.com', 3, 127, ''),\r\n"
                +
                "    (8, 'Sophia Lee', 1112223333, NULL, 'sophia.lee@gmail.com', 3, 128, ''),\r\n"
                +
                "    (9, 'Jackson Davis', 2223334444, 3334445555, 'jackson.davis@gmail.com', 3, 129, ''),\r\n"
                +
                "    (10, 'Isabella Harris', 4445556666, 6243557096, 'isabella.harris@gmail.com', 3, 130, '');";
        st.executeUpdate(insertGoa);

        /* Creation and insertion of GOA Trip table */
        String createAssam = "CREATE TABLE ASSAM(user_id int PRIMARY KEY, user_name varchar(50), phone_no bigint, alternative_no bigint, \r\n"
                +
                "\t\t\temail varchar(100), trip_id int, payment_id int, bill longtext);";
        st.executeUpdate(createAssam);

        String insertGOA = "INSERT INTO ASSAM (user_id, user_name, phone_no, alternative_no, email, trip_id, payment_id, bill)\r\n"
                +
                "VALUES(1, 'Mia Anderson', 11112222333, 22223334444, 'mia.anderson@gmail.com', 4, 131, ''),\r\n"
                +
                "    (2, 'Lucas Smith', 33334445555, 9845756479, 'lucas.smith@gmail.com', 4, 132, ''),\r\n"
                +
                "    (3, 'Henry Johnson', 44445556666, NULL, 'henry.johnson@gmail.com', 4, 133, ''),\r\n"
                +
                "    (4, 'Amelia Williams', 6243557096, 66667778888, 'amelia.williams@gmail.com', 4, 134, ''),\r\n"
                +
                "    (5, 'Liam Brown', 77778889999, NULL, 'liam.brown@gmail.com', 4, 135, ''),\r\n"
                +
                "    (6, 'Zoe Wilson', 9876543210, NULL, 'zoe.wilson@gmail.com', 4, 136, ''),\r\n"
                +
                "    (7, 'Sophia Martin', 99990001111, 6243557096, 'sophia.martin@gmail.com', 4, 137, ''),\r\n"
                +
                "    (8, 'Henry Lee', 11112223333, 9876543210, 'henry.lee@gmail.com', 4, 138, ''),\r\n"
                +
                "    (9, 'Liam Davis', 9845756479, 33334445555, 'liam.davis@gmail.com', 4, 139, ''),\r\n"
                +
                "    (10, 'Chloe Harris', 33334445555, NULL, 'chloe.harris@gmail.com', 4, 140, '');\r\n";
        st.executeUpdate(insertGOA);

        /* Creation and insertion of values in Payment table */
        String createPayment = "CREATE TABLE payment(payment_id int PRIMARY KEY, payment_mode varchar(50), user_id int, trip_id int, payment_status varchar(50));";
        st.executeUpdate(createPayment);

        String insertPayment = "INSERT INTO payment (payment_id, payment_mode, user_id, trip_id, payment_status)\r\n"
                +
                "VALUES(101, 'Credit Card', 1, 1, 'Paid'),\r\n" + //
                "    (102, 'PayPal', 2, 1, 'Pending'),\r\n" + //
                "    (103, 'Debit Card', 1, 1, 'Paid'),\r\n" + //
                "    (104, 'Credit Card', 1, 4, 'Paid'),\r\n" + //
                "    (105, 'Cash', 4, 1, 'Paid'),\r\n" + //
                "    (106, 'Bank Transfer', 5, 1, 'Pending'),\r\n" + //
                "    (107, 'Debit Card', 3, 4, 'Paid'),\r\n" + //
                "    (108, 'Credit Card', 6, 1, 'Paid'),\r\n" + //
                "    (109, 'Credit Card', 6, 2, 'Paid'),\r\n" + //
                "    (110, 'PayPal', 7, 1, 'Paid'),\r\n" + //
                "    (111, 'Debit Card', 8, 1, 'Pending'),\r\n" + //
                "    (112, 'Bank Transfer', 10, 1, 'Paid'),\r\n" + //
                "    (113, 'Credit Card', 1, 2, 'Paid'),\r\n" + //
                "    (114, 'PayPal', 2, 2, 'Pending'),\r\n" + //
                "    (115, 'Cash', 9, 1, 'Paid'),\r\n" + //
                "    (116, 'Debit Card', 3, 2, 'Paid'),\r\n" + //
                "    (117, 'Cash', 4, 3, 'Paid'),\r\n" + //
                "    (118, 'Cash', 4, 2, 'Paid'),\r\n" + //
                "    (119, 'Bank Transfer', 5, 2, 'Pending'),\r\n" + //
                "    (120, 'PayPal', 7, 2, 'Paid'),\r\n" + //
                "    (121, 'Debit Card', 8, 2, 'Pending'),\r\n" + //
                "    (122, 'Cash', 9, 2, 'Paid'),\r\n" + //
                "    (123, 'Bank Transfer', 10, 2, 'Paid'),\r\n" + //
                "    (124, 'Credit Card', 1, 3, 'Paid'),\r\n" + //
                "    (125, 'PayPal', 2, 4, 'Pending'),\r\n" + //
                "    (126, 'PayPal', 2, 3, 'Pending'),\r\n" + //
                "    (127, 'Debit Card', 3, 3, 'Paid'),\r\n" + //
                "    (128, 'Bank Transfer', 5, 3, 'Pending'),\r\n" + //
                "    (129, 'Credit Card', 6, 3, 'Paid'),\r\n" + //
                "    (130, 'PayPal', 7, 3, 'Paid'),\r\n" + //
                "    (131, 'Debit Card', 8, 3, 'Pending'),\r\n" + //
                "    (132, 'Cash', 9, 3, 'Paid'),\r\n" + //
                "    (133, 'Bank Transfer', 10, 3, 'Paid'),\r\n" + //
                "    (134, 'Cash', 4, 4, 'Paid'),\r\n" + //
                "    (135, 'Bank Transfer', 5, 4, 'Pending'),\r\n" + //
                "    (136, 'Credit Card', 6, 4, 'Paid'),\r\n" + //
                "    (137, 'PayPal', 7, 4, 'Paid'),\r\n" + //
                "    (138, 'Debit Card', 8, 4, 'Pending'),\r\n" + //
                "    (139, 'Cash', 9, 4, 'Paid'),\r\n" + //
                "    (140, 'Bank Transfer', 10, 4, 'Paid');";
        st.executeUpdate(insertPayment);

        /* Creation and insertion of values in Cancellation table */
        String createCancel = "CREATE TABLE cancellation(cancellation_id int PRIMARY KEY, cancellation_date date, trip_id int, user_name varchar(50), status varchar(100));";
        st.executeUpdate(createCancel);

        String insertCancel = "INSERT INTO cancellation (cancellation_id, cancellation_date,trip_id, user_name,  status)\r\n"
                +
                "VALUES\r\n" +
                "    (1, '2023-09-05', 1, 'Isabella Harris', 'Cancelled'),\r\n" + //
                "    (2, '2023-11-02', 2, 'Alice Johnson', 'Cancelled!!! Refund not granted'),\r\n"
                +
                "    (3, '2023-09-15', 4, 'John Doe', 'Cancelled'),\r\n" + //
                "    (4, '2023-10-20', 3, 'Henry Lee', 'Cancelled'),\r\n" + //
                "    (5, '2023-06-25', 1, 'Emma Johnson', 'Cancelled!!! Refund not granted');";
        st.executeUpdate(insertCancel);

    }

    // getInitialDetails method
    public static void getInitialDetails() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT trip_id, trip_name FROM trip");
        while (rs.next()) {
            map.put(rs.getInt("trip_id"), rs.getString("trip_name"));
        }

    }

    // method to insert pictures in file
    public static void insertPictures() throws IOException, SQLException {
        String createTrip = "CREATE TABLE trip1(trip_id int PRIMARY KEY, trip_name varchar(30), start_date date, end_date date, rates numeric, information longtext, photo1 blob, photo2 blob, photo3 blob);";
        st.executeUpdate(createTrip);

        String insertTrip = "INSERT INTO trip1 (trip_id, trip_name, start_date, end_date, rates)" +
                "VALUES(1, 'Manali', '2023-10-20', '2023-10-25', 2000.00),"
                + "(2, 'Kerela', '2023-11-05', '2023-11-9', 2500.50),"
                + "(3, 'Goa', '2023-12-20', '2023-12-23', 1500.00),"
                + "(4, 'GOA', '2023-12-25', '2023-12-30', 3000.00);";
        st.executeUpdate(insertTrip);
        pst = c.prepareStatement(
                "UPDATE trip1 SET information = ?, photo1 = ?, photo2 = ?, photo3 = ? WHERE trip_id = 1");
        FileReader fr1 = new FileReader(new File("D:\\ASSAM\\Assam.txt"));
        pst.setClob(1, fr1);
        FileInputStream fis1 = new FileInputStream(new File("D:\\ASSAM\\A1.jpg"));
        pst.setBlob(2, fis1);
        FileInputStream fis2 = new FileInputStream(new File("D:\\ASSAM\\A4.jpg"));
        pst.setBlob(3, fis2);
        FileInputStream fis3 = new FileInputStream(new File("D:\\ASSAM\\A6.jpg"));
        pst.setBlob(4, fis3);
        pst.execute();

        pst = c.prepareStatement(
                "UPDATE trip1 SET  information = ?, photo1 = ?, photo2 = ?, photo3 = ? WHERE trip_id = 2");
        FileReader fr2 = new FileReader(new File("D:\\MANALI\\Manali.txt"));
        pst.setClob(1, fr2);
        FileInputStream fis4 = new FileInputStream(new File("D:\\MANALI\\M1.jpg"));
        pst.setBlob(2, fis4);
        FileInputStream fis5 = new FileInputStream(new File("D:\\MANALI\\M6.jpg"));
        pst.setBlob(3, fis5);
        FileInputStream fis6 = new FileInputStream(new File("D:\\MANALI\\M10.jpg"));
        pst.setBlob(4, fis6);
        pst.execute();

        pst = c.prepareStatement(
                "UPDATE trip1 SET information = ?, photo1 = ?, photo2 = ?, photo3 = ? WHERE trip_id = 3");
        FileReader fr3 = new FileReader(new File("D:\\KERALA\\Kerela.txt"));
        pst.setClob(1, fr3);
        FileInputStream fis7 = new FileInputStream(new File("D:\\KERALA\\K1.jpg"));
        pst.setBlob(2, fis7);
        FileInputStream fis8 = new FileInputStream(new File("D:\\KERALA\\K5.jpg"));
        pst.setBlob(3, fis8);
        FileInputStream fis9 = new FileInputStream(new File("D:\\KERALA\\K10.jpg"));
        pst.setBlob(4, fis9);
        pst.execute();

        pst = c.prepareStatement(
                "UPDATE trip1 SET information = ?, photo1 = ?, photo2 = ?, photo3 = ? WHERE trip_id = 4");
        FileReader fr4 = new FileReader(new File("D:\\GOA\\Goa.txt"));
        pst.setClob(1, fr4);
        FileInputStream fis10 = new FileInputStream(new File("D:\\GOA\\G4.jpg"));
        pst.setBlob(2, fis10);
        FileInputStream fis11 = new FileInputStream(new File("D:\\GOA\\G9.jpg"));
        pst.setBlob(3, fis11);
        FileInputStream fis12 = new FileInputStream(new File("D:\\GOA\\G11.jpg"));
        pst.setBlob(4, fis12);
        pst.execute();
    }
}
