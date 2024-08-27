package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    private static String empId = "210";
    private static String empName = "'OR 1=1 AND EMP_ID='205";

    public static void main(String[] args) {
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "select * from employee where emp_id = '" + empId + "' AND EMP_NAME = '" + empName + "'";
        System.out.println(query);

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println(rs.getString("emp_name")+"님 환영합니다");
            }else{
                System.out.println("결과가 존재하지 않습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rs);
        }

    }//main
}//class
