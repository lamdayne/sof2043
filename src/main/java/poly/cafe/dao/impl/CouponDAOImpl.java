/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.dao.impl;

import java.util.List;
import poly.cafe.dao.CouponDAO;
import poly.cafe.entity.Coupon;
import poly.cafe.util.XJdbc;
import poly.cafe.util.XQuery;

/**
 *
 * @author PHUONG LAM
 */
public class CouponDAOImpl implements CouponDAO {
    
    String createSql = "INSERT INTO Coupon(Name, Desc, startdate, enddate, percent) VALUES (?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Coupon SET Desc=?, startdate=?, enddate=?, percent=? WHERE Name=?";
    String deleteSql = "DELETE FROM Coupon WHERE Name=?";
    String findAllSql = "SELECT * FROM Coupon";
    String findByIdSql = "SELECT * FROM Coupon WHERE Name=?";

    @Override
    public Coupon create(Coupon entity) {
        Object[] values = {
            entity.getNameProgram(),
            entity.getDesc(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getPercentCoupon()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Coupon entity) {
        Object[] values = {
            entity.getDesc(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getPercentCoupon(),
            entity.getNameProgram()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Coupon> findAll() {
        return XQuery.getBeanList(Coupon.class, findAllSql);
    }

    @Override
    public Coupon findById(String id) {
        return XQuery.getSingleBean(Coupon.class, findByIdSql, id);
    }
    
}
