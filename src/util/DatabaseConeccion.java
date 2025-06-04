package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConeccion {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/gestion_transporte";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_nueva_contraseña";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
    
    
}
