package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset = null;
        ResultSet rset2 = null;

        //카테고리 결과 담아줄 list
        List<Map<Integer,String>> categoryList =null;

        int result =0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            rset = pstmt.executeQuery();


            if (rset.next()) {
                result = rset.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신메뉴코드 :"+result);

            rset2 = pstmt2.executeQuery();

            categoryList = new ArrayList<>();

            while (rset2.next()) {
                Map<Integer,String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
            System.out.println("categoryList:"+categoryList);

            System.out.println("--------------");

            System.out.println("입력하실 메뉴");
            String a = sc.nextLine();
            System.out.println("메뉴 가격");
            int b = sc.nextInt();
            System.out.println("카테고리 코드");
            int c = sc.nextInt();
            sc.nextLine();
            System.out.println("주문상태");
            String d = sc.nextLine();
            pstmt3.setString(1,a);
            pstmt3.setInt(2,b);
            pstmt3.setInt(3,c);
            pstmt3.setString(4,d);

              result = pstmt3.executeUpdate();
              //MENU_NAME,MENU_PRICE,CATEGORY_CODE,ORERABLE_STATUS

              if (result == 1) {
                  System.out.println("성공");
              } else if (result == 0) {
                  System.out.println("실패");
              }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
            close(pstmt2);
            close(rset2);
            close(pstmt3);
        }




    }//main
}
