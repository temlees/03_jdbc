package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제하실 메뉴 이름 :");
        String a = sc.nextLine();
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt =con.prepareStatement(prop.getProperty("deleteMenu"));
            pstmt.setString(1,a);
            result = pstmt.executeUpdate();
            
            if (result == 1) {
                System.out.println("삭제 완료");
            } else if (result == 0) {
                System.out.println("삭제 실패");
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
    }
}
