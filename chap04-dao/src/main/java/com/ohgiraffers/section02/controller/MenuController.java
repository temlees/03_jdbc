package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection2;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){

        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴코드 :" + result);
    }

    public void CategoryList(){
        List<Map<Integer,String>> list ;
        list =  menuDAO.selectAllCategoryList(getConnection2());
        System.out.println(list);

    }
    
    public void insertMenu(){
        Scanner sc = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO();
        System.out.println("메뉴 이름 입력");
        menuDTO.menuName(sc.nextLine());
        System.out.println("메뉴 가격 입력 ");
        menuDTO.price(sc.nextInt());
        System.out.println("카테고리 번호 입력");
        menuDTO.categoryCode(sc.nextInt());
        sc.nextLine();
        System.out.println("판매여부 등록");
        menuDTO.status(sc.nextLine());
        
        int result = menuDAO.insertMenu(getConnection2(),menuDTO);
        if(result == 1){
            System.out.println("메뉴 등록 완료");
        }else {
            System.out.println("메뉴등록 실패");
        }
    }
}
