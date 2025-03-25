/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tusha
 */
public class DB {
    static Connection createConn()
    {
        Connection conn=null;
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Car","drive","drive");
        }
        catch(Exception e)
        {
            
        }
        return conn;
    }
}
