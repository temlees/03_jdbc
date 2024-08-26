package com.ohgiraffers.section01;

import com.ohgiraffers.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application05 {
    public static void main(String[] args) {
        //여러 dto 를 하나의 list 로 묶어서 처리
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rset = null;

        //한 행의 정보를 받을 dto
        EmployeeDTO row = null;

        //여러 dto를 묶을 list
        // 밖에서 선언하는 이유 ? try catch 밖에서 값을 찾으려고
        List<EmployeeDTO> empList = null;
        String query ="select * from employee";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            empList = new ArrayList<EmployeeDTO>();

            while (rset.next()) {
                row = new EmployeeDTO();
                row.setEmpId(rset.getString("emp_id"));
                row.setEmpName(rset.getString("emp_name"));
                row.setEmpNo(rset.getString("emp_no"));
                row.setEmail(rset.getString("email"));
                row.setPhone(rset.getString("phone"));
                row.setDeptCode(rset.getString("dept_code"));
                row.setJobCode(rset.getString("job_code"));
                row.setSalLevel(rset.getString("sal_level"));
                row.setSalary(rset.getInt("Salary"));
                row.setBonus(rset.getDouble("bonus"));
                row.setManagerId(rset.getString("manager_id"));
                row.setHireDate(rset.getDate("hire_date"));
                row.setEntDate(rset.getDate("ent_date"));
                row.setEntYn(rset.getString("ent_yn"));
                empList.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(stmt);
            close(rset);
        }

        for (EmployeeDTO emp : empList) {
            System.out.println(emp);
        }


    }//main
}//class
