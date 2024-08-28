package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;


import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
    //데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스
    //dao는 db연결해서 결과를 받아오는 클래스

    //필드로 properties 객체를 가짐
    private Properties prop = new Properties();
    
//생성자
    public MenuDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //제일 최신메뉴코드 반환해줄 코드 정수를 반환
    public int selectLastMenuCode(Connection con){
        Statement stmt = null;
        ResultSet rs = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if(rs.next()){
                maxCode = rs.getInt("MAX(MENU_CODE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rs);
        }
        return maxCode;

    }

    public List<Map<Integer,String>> selectAllCategoryList(Connection con){
        PreparedStatement pstmt = null;
        ResultSet rset= null;
        List<Map<Integer,String>> categoryList = new ArrayList<Map<Integer,String>>();

        String query = prop.getProperty("selectAllCategoryList");
        try {
            pstmt =  con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while(rset.next()){
                Map<Integer,String> map = new HashMap<Integer,String>();
                map.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(map);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }

return categoryList;


    }
    public int insertMenu(Connection con, MenuDTO menuDTO){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuDTO.getName());
            pstmt.setInt(2,menuDTO.getPrice());
            pstmt.setInt(3,menuDTO.getCategoryCode());
            pstmt.setString(4,menuDTO.getStatus());
            result = pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println("잘못된 값이 입력됨");
        }finally {
            close(con);
            close(pstmt);

        }
        return result;
    }


}
