package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("성씨 입력 :");
        // 성씨를 입력받아 해당 성을 가진 사원 조회
        //SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LINKE CONCAT(?,'%')
        Connection con = getConnection();
        PreparedStatement psmt = null;
        ResultSet rset = null;

        try {
            psmt= con.prepareStatement("SELECT  EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?,'%')");
            psmt.setString(1,sc.nextLine());
            rset = psmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(psmt);
            close(rset);
        }

    }
}
