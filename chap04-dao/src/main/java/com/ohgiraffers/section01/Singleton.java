package com.ohgiraffers.section01;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class Singleton {
    public static void main(String[] args) {
        //싱글톤 객체 확인
        //같은주소값
        Connection con= getConnection();
        Connection con2= getConnection();
        Connection con3= getConnection2();
        Connection con4= getConnection2();

        System.out.println(con);
        System.out.println(con2);
        System.out.println("-------------");
        System.out.println(con3);
        System.out.println(con4);


    }//main
}
