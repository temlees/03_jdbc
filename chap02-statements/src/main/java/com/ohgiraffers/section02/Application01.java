package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {
        Connection con =  getConnection();

        //stament를 상속받음
        PreparedStatement pstmt = null;
        ResultSet rset =null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID,EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString(1)+" "+rset.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);

        }

    }

}
