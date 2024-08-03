/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.phone.ui;

import com.phone.DAO.SanPhamDAO;
import com.phone.utils.MsgBox;
import com.phone.utils.XImage;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.phone.entity.SanPham;
import com.phone.utils.Auth;

/**
 *
 * @author PhatLe
 */
public class SanPhamJDialog extends javax.swing.JDialog {

	int row = -1;
	SanPhamDAO dao = new SanPhamDAO();

	public SanPhamJDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		init();
	}

	void init() {
		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		setTitle("Quản lý sản phẩm");
		fillTable();

	}

	public void fillTable() {
		DefaultTableModel model = (DefaultTableModel) tblSP.getModel();
		model.setRowCount(0);
		try {
			List<SanPham> list = dao.selectAll();
			System.out.println("List: " + list.size());
			for (SanPham sp : list) {
				Object[] row = {
					sp.getMaSP(),
					sp.getTenSP(), sp.getGiaTien(), sp.getSoluongSP()
				};
				model.addRow(row);
			}
		} catch (Exception e) {
			MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
		}
	}

	void edit() {
//        try {
//            String MaNV = (String) tbl_nhanvien.getValueAt(this.row, 0);
//            NhanVien model = dao.selectById(MaNV);
//            if (model != null) {
//                setForm(model);
//                updateStatus();
//                tabs.setSelectedIndex(0);// chuyển tab khi click
//
//            }
//        } catch (Exception e) {
//            MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
//        }

		String maSP = (String) tblSP.getValueAt(this.row, 0);
		SanPham sp = dao.selectById(maSP);
		this.setForm(sp);
		tabs.setSelectedIndex(0);
		this.updateStatus();
	}

	void setForm(SanPham model) {
		txtMaSP.setText(model.getMaSP());
		txtTenSP.setText(model.getTenSP());
		
		txtGiatien.setText(Double.toString(model.getGiaTien()));
		txtSoluonghang.setText(Double.toString(model.getSoluongSP()));

	}

	SanPham getForm() {
		SanPham model = new SanPham();
		model.setMaSP(txtMaSP.getText());
		model.setTenSP(txtTenSP.getText());

		try {
			// Xử lý trường giá tiền
			double giaTien = Double.parseDouble(txtGiatien.getText());
			model.setGiaTien(giaTien);
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}
		try {
			// Xử lý trường giá tiền
			double SLSP = Double.parseDouble(txtSoluonghang.getText());
			model.setSoluongSP(SLSP);
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}

		return model;
	}

	void updateStatus() {
		boolean edit = this.row >= 0;
		boolean first = this.row == 0;
		boolean last = this.row == tblSP.getRowCount() - 1;
		txtMaSP.setEditable(!edit);
		//khi insert thì ko update, delete
		btn_them.setEnabled(!edit);
		btn_sua.setEnabled(edit);
		btn_xoa.setEnabled(edit);

		btn_first.setEnabled(edit && !first);
		btn_prev.setEnabled(edit && !first);
		btn_next.setEnabled(edit && !last);
		btn_last.setEnabled(edit && !last);
	}

	void clearForm() {
		SanPham sp = new SanPham();
		this.setForm(sp);
		this.row = -1;
		this.updateStatus();
	}
							//tí thêm kiểm lỗi nữa là đc :)))
	void insert() {
		SanPham model = getForm();

		try {
			dao.insert(model);
			this.fillTable();
			this.clearForm();
			MsgBox.alter(this, "Thêm mới thành công");
		} catch (Exception e) {

			MsgBox.alter(this, "Thêm mới thất bại!");
		}
	}

	void update() {
		fillTable();
		SanPham model = getForm();
		try {
			dao.update(model);
			fillTable();
			MsgBox.alter(this, "Cập nhật thành công!");
		} catch (Exception e) {
			MsgBox.alter(this, "Cập nhật thất bại!");
		}
	}

	void delete() {
		if (!Auth.isManager()) {
			MsgBox.alter(this, "Bạn không có quyền xóa sản phẩm !");
		} else {
			if (MsgBox.confirm(this, "Bạn thực sự muốn xóa sản phẩm này ?")) {
				String manv = txtMaSP.getText();
				try {
					dao.delete(manv);
					this.fillTable();
					this.clearForm();
					MsgBox.alter(this, "Xóa thành công !");
				} catch (Exception e) {
					MsgBox.alter(this, "Xóa thất bại !");
				}
			}
		}

	}

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                tabs = new javax.swing.JTabbedPane();
                jPanel3 = new javax.swing.JPanel();
                jPanel5 = new javax.swing.JPanel();
                btn_first = new javax.swing.JButton();
                btn_prev = new javax.swing.JButton();
                btn_next = new javax.swing.JButton();
                btn_last = new javax.swing.JButton();
                jPanel6 = new javax.swing.JPanel();
                btn_them = new javax.swing.JButton();
                btn_sua = new javax.swing.JButton();
                btn_xoa = new javax.swing.JButton();
                btn_moi = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                txtMaSP = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txtGiatien = new javax.swing.JTextField();
                txtSoluonghang = new javax.swing.JTextField();
                txtTenSP = new javax.swing.JTextField();
                jPanel4 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tblSP = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(0, 0, 255));
                jLabel1.setText("QUẢN LÝ SẢN PHẨM");

                jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                jPanel5.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

                btn_first.setText("|<");
                btn_first.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_firstActionPerformed(evt);
                        }
                });
                jPanel5.add(btn_first);

                btn_prev.setText("<<");
                btn_prev.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_prevActionPerformed(evt);
                        }
                });
                jPanel5.add(btn_prev);

                btn_next.setText(">>");
                btn_next.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_nextActionPerformed(evt);
                        }
                });
                jPanel5.add(btn_next);

                btn_last.setText(">|");
                btn_last.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_lastActionPerformed(evt);
                        }
                });
                jPanel5.add(btn_last);

                jPanel6.setLayout(new java.awt.GridLayout(1, 4, 5, 0));

                btn_them.setText("Thêm");
                btn_them.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_themActionPerformed(evt);
                        }
                });
                jPanel6.add(btn_them);

                btn_sua.setText("Sửa");
                btn_sua.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_suaActionPerformed(evt);
                        }
                });
                jPanel6.add(btn_sua);

                btn_xoa.setText("Xóa");
                btn_xoa.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_xoaActionPerformed(evt);
                        }
                });
                jPanel6.add(btn_xoa);

                btn_moi.setText("Mới");
                btn_moi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_moiActionPerformed(evt);
                        }
                });
                jPanel6.add(btn_moi);

                jLabel2.setText("Mã sản phẩm");

                jLabel3.setText("Tên sản phẩm");

                jLabel4.setText("Giá tiền");

                jLabel5.setText("Số lượng hàng trong kho");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTenSP)
                                        .addComponent(txtMaSP)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(txtGiatien)
                                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtSoluonghang))))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtGiatien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoluonghang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(31, Short.MAX_VALUE))
                );

                tabs.addTab("Cập Nhật", jPanel3);

                tblSP.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                tblSP.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Mã SP", "Tên SP", "Giá ", "Số lượng"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, true
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tblSPMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tblSPMousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(tblSP);

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE))
                );

                tabs.addTab("Danh Sách", jPanel4);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(15, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed

        }//GEN-LAST:event_btn_firstActionPerformed

        private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed

        }//GEN-LAST:event_btn_prevActionPerformed

        private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed

        }//GEN-LAST:event_btn_nextActionPerformed

        private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed

        }//GEN-LAST:event_btn_lastActionPerformed

        private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed

		insert();
        }//GEN-LAST:event_btn_themActionPerformed

        private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed

		update();
        }//GEN-LAST:event_btn_suaActionPerformed

        private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed

		delete();
        }//GEN-LAST:event_btn_xoaActionPerformed

        private void btn_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moiActionPerformed
		clearForm();
        }//GEN-LAST:event_btn_moiActionPerformed

        private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked

        }//GEN-LAST:event_tblSPMouseClicked

        private void tblSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMousePressed
		int selectedRow = tblSP.getSelectedRow();
		if (selectedRow != -1 && evt.getClickCount() == 2) {
			this.row = selectedRow; // Gán giá trị row bằng hàng được chọn
			edit();
		}
        }//GEN-LAST:event_tblSPMousePressed

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
			java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the dialog */     //chăc cái seting của netbean bị hỏng r :))) nên cài lại k.. từ cài hh
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				SanPhamJDialog dialog = new SanPhamJDialog(new javax.swing.JFrame(), true);
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
        private javax.swing.JButton btn_first;
        private javax.swing.JButton btn_last;
        private javax.swing.JButton btn_moi;
        private javax.swing.JButton btn_next;
        private javax.swing.JButton btn_prev;
        private javax.swing.JButton btn_sua;
        private javax.swing.JButton btn_them;
        private javax.swing.JButton btn_xoa;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTabbedPane tabs;
        private javax.swing.JTable tblSP;
        private javax.swing.JTextField txtGiatien;
        private javax.swing.JTextField txtMaSP;
        private javax.swing.JTextField txtSoluonghang;
        private javax.swing.JTextField txtTenSP;
        // End of variables declaration//GEN-END:variables
}
