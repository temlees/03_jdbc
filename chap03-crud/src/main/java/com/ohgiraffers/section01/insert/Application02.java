package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        /*
        * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어주세요
        * 등록이 완료되면 성공, 실패하면 실패 출력
        * */
        Scanner sc = new Scanner(System.in);
        System.out.println("등록하실 메뉴 :");
        String name = sc.nextLine();
        System.out.println("가격 ");
        int price = sc.nextInt();
        System.out.println("카테고리 코드 :");
        int category = sc.nextInt();
        sc.nextLine();
        System.out.println("주문상태");
        String status = sc.nextLine();
        Connection con = getConnection();
        Properties prop = new Properties();
        PreparedStatement pstmt = null;
        int result= 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1,name);
            pstmt.setInt(2,price);
            pstmt.setInt(3,category);
            pstmt.setString(4,status);
            result = pstmt.executeUpdate();

            if (result>0 && result ==1) {
                System.out.println("등록완료 !");
            }else{
                System.out.println("등록실패");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }//main
}
