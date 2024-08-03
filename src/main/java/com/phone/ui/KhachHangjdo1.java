/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.phone.ui;

import com.phone.dao.KhachHangDAO;
import com.phone.entity.KhachHang;
import com.phone.utils.Auth; 
import com.phone.utils.MsgBox;
import com.phone.utils.XImage;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PhatLe
 */
public class KhachHangjdo1 extends javax.swing.JDialog {

	KhachHangDAO dao = new KhachHangDAO();

	int row = 0;

	/**
	 * Creates new form KhachHang
	 */
	public KhachHangjdo1(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		init();
	}

	void init() {
		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		setTitle("Quản lý khách hàng");
		fillTable();
		updateStatus();

	}

	void fillTable() {
		DefaultTableModel model = (DefaultTableModel) tblKhachhang.getModel();
		model.setRowCount(0);
		try {
		List<KhachHang> list = dao.selectAll(); //đọc dữ lịu từ sql
		for (KhachHang nv : list) {
			Object[] row = {
				nv.getMaKH(),
				nv.getHoTen(),
				nv.getSanpham(),
				nv.getSoDT(),
				nv.getDiachi(),};
			model.addRow(row);
		}
		} catch (Exception e) {
			MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
		}
	}

	void edit() {
		String manv = (String) tblKhachhang.getValueAt(this.row, 0);
		KhachHang nv = dao.selectById(manv);
		this.setForm(nv);
		tabs.setSelectedIndex(0);
		this.updateStatus();
	}

	void clearForm() {
		KhachHang kh = new KhachHang();
		this.setForm(kh);
		this.row = -1;
		this.updateStatus();
	}

	void setForm(KhachHang model) {
		txtmaKH.setText(model.getMaKH());
		txtHoten.setText(model.getHoTen());
		txtSanpham.setText(model.getSanpham());
		txtsoDT.setText(model.getSoDT());
		txtDiachi.setText(model.getDiachi());
	}

	KhachHang getForm() {
		KhachHang model = new KhachHang();
		model.setMaKH(txtmaKH.getText());
		model.setHoTen(txtHoten.getText());
		model.setSanpham(txtSanpham.getText());
		model.setSoDT(txtsoDT.getText());
		model.setDiachi(txtDiachi.getText());
		return model;
	}

	void updateStatus() {
		boolean edit = this.row >= 0;
		boolean first = this.row == 0;
		boolean last = this.row == tblKhachhang.getRowCount() - 1;

		//khi insert thì ko update, delete
		btn_them.setEnabled(!edit);
		btn_sua.setEnabled(edit);
		btn_xoa.setEnabled(edit);
//		btn_first.setEnabled(edit && !first);
//		btn_prev.setEnabled(edit && !first);
//		btn_next.setEnabled(edit && !last);
//		btn_last.setEnabled(edit && !last);
	}

	boolean validateForm() {
		String maKH = txtmaKH.getText();
		String hoTen = txtHoten.getText();
		String sanPham = txtSanpham.getText();
		String soDT = txtsoDT.getText();
		String diaChi = txtDiachi.getText();

		// Kiểm tra mã KH bao gồm chữ và số không có kí tự đặc biệt
		if (!maKH.matches("^[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(null, "Mã KH chỉ được chứa chữ cái và số");
			return false;
		}

		// Kiểm tra tên tiếng Việt không chứa số và ký tự đặc biệt
		if (!hoTen.matches("^[\\p{L}\\s]+$")) {
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!");
			return false;
		}

		// Kiểm tra định dạng email
//		if (!sanPham.matches("^[a-zA-Z0-9]+$")) {
//			JOptionPane.showMessageDialog(null, "Sản phẩm không hợp lệ!");

//		}

		// Kiểm tra số điện thoại chỉ chứa số và bắt đầu từ số 0 với độ dài là 10 số
		if (!soDT.matches("0\\d{9}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!");
			return false;
		}

		// Kiểm tra địa chỉ không chứa ký tự đặc biệt
		if (diaChi.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
			return false;
		}

		return true;
	}

	void insert() {
		KhachHang model = getForm();

		try {
			dao.insert(model);
			this.fillTable();
			this.clearForm();
			MsgBox.alter(this, "Bạn đã đặt hàng thành công");
		} catch (Exception e) {

			MsgBox.alter(this, "Thêm mới thất bại!");
		}
	}

	void update() {
		fillTable();
		KhachHang model = getForm();
//		try {
			dao.update(model);
			fillTable();
			MsgBox.alter(this, "Cập nhật đơn hàng  thành công!");
//		} catch (Exception e) {
//			MsgBox.alter(this, "Cập nhật thất bại!");
//		}
	}

	void delete() {
		if (!Auth.isManager()) {
			MsgBox.alter(this, "Bạn không có quyền xóa sản phẩm !");
		} else {
			if (MsgBox.confirm(this, "Bạn thực sự muốn xóa sản phẩm này ?")) {
				String manv = txtmaKH.getText();
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

//	void first() {
//		row = 0;
//		edit();
//	}
//
//	void prev() {
//		if (row > 0) {
//			row--;
//			edit();
//		}
//	}
//
//	void next() {
//		if (row < tblKhachhang.getRowCount() - 1) {
//			row++;
//			edit();
//		}
//	}
//
//	void last() {
//		row = tblKhachhang.getRowCount() - 1;
//		edit();
//	}


        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                tabs = new javax.swing.JTabbedPane();
                jPanel1 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                txtmaKH = new javax.swing.JTextField();
                txtHoten = new javax.swing.JTextField();
                txtSanpham = new javax.swing.JTextField();
                txtsoDT = new javax.swing.JTextField();
                txtDiachi = new javax.swing.JTextField();
                jPanel2 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                btn_them = new javax.swing.JButton();
                btn_moi = new javax.swing.JButton();
                btn_xoa = new javax.swing.JButton();
                btn_sua = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                tblKhachhang = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(0, 0, 255));
                jLabel1.setText("THÔNG TIN KHÁCH HÀNG");

                jLabel2.setText("Họ tên");

                jLabel3.setText("Mã khách hàng");

                jLabel4.setText("Sản Phẩm");

                jLabel5.setText("Địa chỉ");

                jLabel6.setText("Số điện thoại");

                txtmaKH.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtmaKHActionPerformed(evt);
                        }
                });

                txtHoten.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtHotenActionPerformed(evt);
                        }
                });

                txtSanpham.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtSanphamActionPerformed(evt);
                        }
                });

                txtDiachi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtDiachiActionPerformed(evt);
                        }
                });

                jPanel2.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

                jPanel3.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

                btn_them.setText("Mua");
                btn_them.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_themActionPerformed(evt);
                        }
                });
                jPanel3.add(btn_them);

                btn_moi.setText("Mới");
                btn_moi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_moiActionPerformed(evt);
                        }
                });

                btn_xoa.setText("Xóa");
                btn_xoa.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_xoaActionPerformed(evt);
                        }
                });

                btn_sua.setText("Sửa ");
                btn_sua.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_suaActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 617, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(71, 71, 71))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_sua)
                                                .addGap(13, 13, 13)
                                                .addComponent(btn_xoa)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btn_moi)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtDiachi, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(txtSanpham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                                                                .addComponent(txtmaKH, javax.swing.GroupLayout.Alignment.LEADING))
                                                                        .addGap(108, 108, 108)
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(txtsoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                        .addGap(6, 6, 6)
                                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(671, Short.MAX_VALUE)))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtsoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btn_moi)
                                                        .addComponent(btn_xoa)
                                                        .addComponent(btn_sua))))
                                .addContainerGap(48, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel3)
                                        .addContainerGap(232, Short.MAX_VALUE)))
                );

                tabs.addTab("Cập nhật", jPanel1);

                tblKhachhang.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null}
                        },
                        new String [] {
                                "Mã khách hàng", "Họ tên", "Sản Phẩm", "Số điện thoại", "Địa chỉ"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tblKhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tblKhachhangMousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(tblKhachhang);

                tabs.addTab("Danh sách", jScrollPane1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tabs)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtDiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiachiActionPerformed
	    // TODO add your handling code here:
    }//GEN-LAST:event_txtDiachiActionPerformed

    private void btn_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moiActionPerformed
	    clearForm();
    }//GEN-LAST:event_btn_moiActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
	    // TODO add your handling code here:
	    if (validateForm()) {
		    insert();
	    }

    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
	    // TODO add your handling code here:
	    if (validateForm()) {
		    update();
	    }

    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
	    // TODO add your handling code here:
	    delete();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void txtmaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaKHActionPerformed
	    // TODO add your handling code here:
    }//GEN-LAST:event_txtmaKHActionPerformed

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed
	    // TODO add your handling code here:
    }//GEN-LAST:event_txtHotenActionPerformed

        private void tblKhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachhangMousePressed
		if (evt.getClickCount() == 2) {
			this.row = tblKhachhang.rowAtPoint(evt.getPoint());
			edit();
		}
        }//GEN-LAST:event_tblKhachhangMousePressed

        private void txtSanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSanphamActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_txtSanphamActionPerformed
	private void tbl_nhanvienMousePressed(java.awt.event.MouseEvent evt) {
		int selectedRow = tblKhachhang.getSelectedRow();
		if (selectedRow != -1 && evt.getClickCount() == 2) {
			this.row = selectedRow; // Gán giá trị row bằng hàng được chọn
			edit();
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
			java.util.logging.Logger.getLogger(KhachHangjdo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(KhachHangjdo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(KhachHangjdo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(KhachHangjdo1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				KhachHangjdo1 dialog = new KhachHangjdo1(new javax.swing.JFrame(), true);
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
        private javax.swing.JButton btn_moi;
        private javax.swing.JButton btn_sua;
        private javax.swing.JButton btn_them;
        private javax.swing.JButton btn_xoa;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTabbedPane tabs;
        private javax.swing.JTable tblKhachhang;
        private javax.swing.JTextField txtDiachi;
        private javax.swing.JTextField txtHoten;
        private javax.swing.JTextField txtSanpham;
        private javax.swing.JTextField txtmaKH;
        private javax.swing.JTextField txtsoDT;
        // End of variables declaration//GEN-END:variables
}
