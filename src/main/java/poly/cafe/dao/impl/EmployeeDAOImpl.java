/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.entity.Employee;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.dao.EmployeeDAO;

/**
 *
 * @author PHUONG LAM
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    
    String createSql = "INSERT INTO Employee(employeeID, firstName, lastName, email, phone, hireDate, jobTitle, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Employee SET firstName=?, lastName=?, email=?, phone=?, hireDate=?, jobTitle=?, salary=? WHERE employeeID=?";
    String deleteSql = "DELETE FROM Employee WHERE employeeID=?";
    String findAllSql = "SELECT * FROM Employee";
    String findByIdSql = "SELECT * FROM Employee WHERE employeeID=?";

    @Override
    public Employee create(Employee entity) {
        Object[] values = {
            entity.getEmployeeID(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getHireDate(),
            entity.getJobTitle(),
            entity.getSalary()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Employee entity) {
        Object[] values = {
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getHireDate(),
            entity.getJobTitle(),
            entity.getSalary(),
            entity.getEmployeeID()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Employee> findAll() {
        return XQuery.getBeanList(Employee.class, findAllSql);
    }

    @Override
    public Employee findById(String id) {
        return XQuery.getSingleBean(Employee.class, findByIdSql, id);
    }
    
}
