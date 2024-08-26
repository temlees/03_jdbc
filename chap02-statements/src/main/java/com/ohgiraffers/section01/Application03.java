package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application03 {
    public static void main(String[] args) {

        // 이름을 입력받아서 해당사원 아이디와 이름조회
        // 쿼리문도 변수로 따로 만들어서 넣어주세요
        //선동일
        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;
        String result ;

        try {
            stmt = con.createStatement();
            System.out.println("조회할 사원의 이름 :");
            String name = sc.nextLine();
            String query = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_NAME ='"+name+"'";

            rset = stmt.executeQuery(query);
            while (rset.next()) {
                result =rset.getString("EMP_ID")+" "+rset.getString("EMP_NAME");
                System.out.println(result);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(stmt);
            close(con);
        }

    }
}
