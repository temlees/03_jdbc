package com.ohgiraffers.understand;

import com.ohgiraffers.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        EmployeeDTO  selectEmp = new EmployeeDTO();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("top"));
            rset = pstmt.executeQuery();

            while (rset.next()) {

                selectEmp.setEmpId(rset.getString("emp_id"));
                selectEmp.setEmpName(rset.getString("emp_name"));
                selectEmp.setEmpNo(rset.getString("emp_no"));
                selectEmp.setEmail(rset.getString("email"));
                selectEmp.setPhone(rset.getString("phone"));
                selectEmp.setDeptCode(rset.getString("dept_code"));
                selectEmp.setJobCode(rset.getString("job_code"));
                selectEmp.setSalLevel(rset.getString("sal_level"));
                selectEmp.setSalary(rset.getInt("Salary"));
                selectEmp.setBonus(rset.getDouble("bonus"));
                selectEmp.setManagerId(rset.getString("manager_id"));
                selectEmp.setHireDate(rset.getDate("hire_date"));
                selectEmp.setEntDate(rset.getDate("ent_date"));
                selectEmp.setEntYn(rset.getString("ent_yn"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        System.out.println(selectEmp);

    }//main
}//class
