package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        Properties prop = new Properties();
        PreparedStatement pset = null;
        int result =0 ;
        System.out.println("변경하실 메뉴의 이름을 입력 해주세요 :");
        String a= sc.nextLine();
        System.out.println("어떤 메뉴로 이름을 변경하시겠습니까?");
        String b= sc.nextLine();
        System.out.println("바꿀 메뉴의 가격입력");
        int c = sc.nextInt();
        System.out.println("바꿀메뉴의 타입선택 ");
        System.out.println("4. 한식, 5. 중식, 6.일식, 7. 퓨전");
        int d = sc.nextInt();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
           pset = con.prepareStatement(prop.getProperty("updateMenu"));
           pset.setString(4, a);
           pset.setString(1, b);
           pset.setInt(2, c);
           pset.setInt(3, d);
          result = pset.executeUpdate();
          
          if(result == 1){
              System.out.println("업데이트 성공");
          } else if (result == 0) {
              System.out.println("업데이트 실패");
          }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pset);
        }
    }
}
