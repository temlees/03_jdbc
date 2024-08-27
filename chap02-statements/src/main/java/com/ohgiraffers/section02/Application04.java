package com.ohgiraffers.section02;

import com.ohgiraffers.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {
    public static void main(String[] args) {

        //xml 파일을 이용한 조회
        //xml - 특수한 목적을 가진 마크업 언어
        //마크업 언어 : 태그로 이루어진 언어

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("성씨를 입력해주세요 : ");
        String empName = sc.nextLine();
        Properties prop = new Properties();
        EmployeeDTO selectDTO = new EmployeeDTO();
        try {
            //xml 파일경로 읽어오는 메소드
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("selectEmpByName"));
            pstmt.setString(1,empName);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString(1)+" "+rset.getString(2));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(pstmt);

        }
    }
}
