/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author PHUONG LAM
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Coupon {
    private String nameProgram;
    private String desc;
    private Date startDate;
    private Date endDate;
    private float percentCoupon;
}
