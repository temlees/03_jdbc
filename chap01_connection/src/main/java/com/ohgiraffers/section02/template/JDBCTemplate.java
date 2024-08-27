package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    //d
    public static Connection getConnection() {

        Connection con = null;
        Properties prop = new Properties();

        try {
            //FileReader는 src/main/java/com/ohgiraffers/config/connection-info.properties"
            //경로의 파일을 읽기위해 사용
            //prop.load() 메서드는 파일에서 속성 정보를 읽어와 prop 객체에 로드합니다.
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            try {
                //DriverManager는 JDBC 드라이버를 관리하고
                //데이터베이스 연결을 생성하는 역할을 합니다.
                con = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return con;
    }//getConnection메소드

    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}//class
