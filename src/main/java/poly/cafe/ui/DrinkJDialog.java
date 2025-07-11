/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package poly.cafe.ui;

import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import lombok.Setter;
import poly.cafe.dao.CategoryDAO;
import poly.cafe.dao.DrinkDAO;
import poly.cafe.dao.impl.BillDetailDAOImpl;
import poly.cafe.dao.impl.CategoryDAOImpl;
import poly.cafe.dao.impl.DrinkDAOImpl;
import poly.cafe.entity.Bill;
import poly.cafe.entity.BillDetail;
import poly.cafe.entity.Category;
import poly.cafe.entity.Drink;
import poly.cafe.util.XDialog;

/**
 *
 * @author PHUONG LAM
 */
public class DrinkJDialog extends javax.swing.JDialog implements DrinkController {

    @Setter
    Bill bill;

    CategoryDAO categoryDao = new CategoryDAOImpl();
    List<Category> categories = List.of();
    DrinkDAO drinkDao = new DrinkDAOImpl();
    List<Drink> drinks = List.of();

    /**
     * Creates new form DrinkJDialog
     */
    public DrinkJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategories = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDrinks = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Loại đồ uống");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblCategories.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblCategories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Loại đồ uống"
            }
        ));
        tblCategories.setRowHeight(30);
        tblCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCategories);

        tblDrinks.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDrinks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên đồ uống", "Đơn giá", "Giảm giá"
            }
        ));
        tblDrinks.setRowHeight(22);
        tblDrinks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDrinksMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDrinks);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriesMouseClicked
        // TODO add your handling code here:
        tblCategories.setSelectionBackground(Color.YELLOW);
        tblCategories.setSelectionForeground(Color.RED);
        this.fillDrinks();
    }//GEN-LAST:event_tblCategoriesMouseClicked

    private void tblDrinksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDrinksMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.addDrinkToBill();
        }
    }//GEN-LAST:event_tblDrinksMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.open();
    }//GEN-LAST:event_formWindowOpened

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
        this.fillCategories();
        this.fillDrinks();
    }

    @Override
    public void fillCategories() {
        categories = categoryDao.findAll();
        DefaultTableModel model = (DefaultTableModel) tblCategories.getModel();
        model.setRowCount(0);
        categories.forEach(d -> model.addRow(new Object[]{d.getName()}));
        tblCategories.setRowSelectionInterval(0, 0);
    }

    @Override
    public void fillDrinks() {
        Category category = categories.get(tblCategories.getSelectedRow());
        drinks = drinkDao.findByCategoryId(category.getId());
        DefaultTableModel model = (DefaultTableModel) tblDrinks.getModel();
        model.setRowCount(0);
        drinks.forEach(d -> {
            Object[] row = {
                d.getId(),
                d.getName(),
                String.format("$%.1f", d.getUnitPrice()),
                String.format("%.0f%%", d.getDiscount() * 100)
            };
            model.addRow(row);
        });
    }

    @Override
    public void addDrinkToBill() {
        try {
            String quantity = XDialog.prompt("Số lượng?");
            if (quantity != null && quantity.length() > 0) {
                if (Integer.parseInt(quantity) <= 0) {
                    XDialog.alert("Số lượng phải lớn hơn 0");
                } else if (Integer.parseInt(quantity) >= 100) {
                    XDialog.alert("Số lượng phải nhỏ hơn 100");
                } else {
                    Drink drink = drinks.get(tblDrinks.getSelectedRow());
                    BillDetail detail = new BillDetail();
                    detail.setBillId(bill.getId());
                    detail.setDiscount(drink.getDiscount());
                    detail.setDrinkId(drink.getId());
                    detail.setQuantity(Integer.parseInt(quantity));
                    detail.setUnitPrice(drink.getUnitPrice());
                    new BillDetailDAOImpl().create(detail);
                }

            }
        } catch (Exception e) {
            XDialog.alert("Nhập vào một số hợp lẹ");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DrinkJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DrinkJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrinkJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrinkJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DrinkJDialog dialog = new DrinkJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCategories;
    private javax.swing.JTable tblDrinks;
    // End of variables declaration//GEN-END:variables
}
