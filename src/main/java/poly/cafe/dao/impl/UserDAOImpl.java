/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.UserDAO;
import poly.cafe.entity.User;
import poly.cafe.util.XDialog;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;
import poly.cafe.util.XStr;

/**
 *
 * @author PHUONG LAM
 */
public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager) VALUES(?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? WHERE Username=?";
    String deleteSql = "DELETE FROM Users WHERE Username=?";
    String findAllSql = "SELECT * FROM Users";
    String findByIdSql = "SELECT * FROM Users WHERE Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            XStr.encodeB64(entity.getPassword()),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()
        };
        try {
            XJdbc.executeUpdate(createSql, values);
            return entity;
        } catch (Exception e) {
            if (e.getMessage().contains("PRIMARY KEY") || e.getMessage().contains("duplicate")) {
                XDialog.alert("Tên người dùng đã tồn tại");
            }
        }
//        XJdbc.executeUpdate(createSql, values);
        return entity;

    }

    @Override
    public void update(User entity) {
        Object[] values = {
            XStr.encodeB64(entity.getPassword()),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getUsername()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List< User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id);
    }
}
