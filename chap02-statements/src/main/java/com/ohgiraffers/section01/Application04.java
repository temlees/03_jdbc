package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application04 {
    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;


        EmployeeDTO selectEmp = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("조회하실 사번을 입력하세요 :");
        String empId = sc.nextLine();

        String query = "select * From employee where emp_id = " + empId;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        if (rs.next()) {
            selectEmp = new EmployeeDTO();

            selectEmp.setEmpId(rs.getString("emp_id"));
            selectEmp.setEmpName(rs.getString("emp_name"));
            selectEmp.setEmpNo(rs.getString("emp_no"));
            selectEmp.setEmail(rs.getString("email"));
            selectEmp.setPhone(rs.getString("phone"));
            selectEmp.setDeptCode(rs.getString("dept_code"));
            selectEmp.setJobCode(rs.getString("job_code"));
            selectEmp.setSalLevel(rs.getString("sal_level"));
            selectEmp.setSalary(rs.getInt("Salary"));
            selectEmp.setBonus(rs.getDouble("bonus"));
            selectEmp.setManagerId(rs.getString("manager_id"));
            selectEmp.setHireDate(rs.getDate("hire_date"));
            selectEmp.setEntDate(rs.getDate("ent_date"));
            selectEmp.setEntYn(rs.getString("ent_yn"));
        }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(stmt);
            close(rs);

        }
        System.out.println("selectEmp = " + selectEmp);

    }//main
}//class
