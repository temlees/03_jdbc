package com.ohgiraffers.section02.template;

import java.sql.Connection;

import static com.ohgiraffers.section02.template.JDBCTemplate.close;
import static com.ohgiraffers.section02.template.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        //static으로 메소드를 만들었기 때문에 바로 메소드 호출 가능
        Connection con = getConnection();
        System.out.println(con);
        close(con);
    }
}
