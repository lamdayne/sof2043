/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.CustomersDAO;
import poly.cafe.entity.Customers;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author PHUONG LAM
 */
public class CustomersDAOImpl implements CustomersDAO {
    
    String createSql = "INSERT INTO Customers(customerId, fullname, dateofbirth, gender, address, phone, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Customers SET fullname=?, dateofbirth=?, gender=?, address=?, phone=?, email=? WHERE customerId=?";
    String deleteSql = "DELETE FROM Customers WHERE customerId=?";
    String findAllSql = "SELECT * FROM Customers";
    String findByIdSql = "SELECT * FROM Customers WHERE customerId=?";

    @Override
    public Customers create(Customers entity) {
        Object[] values = {
            entity.getCustomerId(),
            entity.getFullName(),
            entity.getDateOfBirth(),
            entity.isGender(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Customers entity) {
        Object[] values = {
            entity.getFullName(),
            entity.getDateOfBirth(),
            entity.isGender(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getCustomerId()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Customers> findAll() {
        return XQuery.getBeanList(Customers.class, findAllSql);
    }

    @Override
    public Customers findById(String id) {
        return XQuery.getSingleBean(Customers.class, findByIdSql, id);
    }
    
}
