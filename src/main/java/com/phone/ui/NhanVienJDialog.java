/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.phone.ui;

import com.phone.dao.NhanVienDAO;
import com.phone.entity.NhanVien;
import com.phone.utils.Auth;
import com.phone.utils.MsgBox;
import com.phone.utils.XImage;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dino
 */
public class NhanVienJDialog extends javax.swing.JDialog {

	NhanVienDAO dao = new NhanVienDAO();
	int row = 0;

	/**
	 * Creates new form NhanVienJDialog
	 */
	public NhanVienJDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		init();
	}

	void init() {
		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		setTitle("Quản lý nhân viên ");
		fillTable();
		updateStatus();
	}

	void fillTable() {
		DefaultTableModel model = (DefaultTableModel) tbl_nhanvien.getModel();
		model.setRowCount(0);
		try {
			List<NhanVien> list = dao.selectAll(); //đọc dữ lịu từ sql
			for (NhanVien nv : list) {
				Object[] row = {
					nv.getMaNV(),
					nv.getMatKhau(),
					nv.getHoTen(),};
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

		String manv = (String) tbl_nhanvien.getValueAt(this.row, 0);
		NhanVien nv = dao.selectById(manv);
		this.setForm(nv);
		tabs.setSelectedIndex(0);
		this.updateStatus();
	}
	//đưa dữ liệu lên form 

	void setForm(NhanVien model) {
		txt_manv.setText(model.getMaNV());
		txt_hovaten.setText(model.getHoTen());
		txt_matkhau.setText(model.getMatKhau());
		txt_xacnhanmk.setText(model.getMatKhau());
	}

	NhanVien getForm() {
		NhanVien model = new NhanVien();
		model.setMaNV(txt_manv.getText());
		model.setHoTen(txt_hovaten.getText());
		model.setMatKhau(new String(txt_matkhau.getPassword()));
		return model;
	}

	void updateStatus() {
		boolean edit = this.row >= 0;
		boolean first = this.row == 0;
		boolean last = this.row == tbl_nhanvien.getRowCount() - 1;
		txt_manv.setEditable(!edit);
		//khi insert thì ko update, delete
		btn_them.setEnabled(!edit);
		btn_sua.setEnabled(edit);
		btn_xoa.setEnabled(edit);

		btn_first.setEnabled(edit && !first);
		btn_prev.setEnabled(edit && !first);
		btn_next.setEnabled(edit && !last);
		btn_last.setEnabled(edit && !last);
	}

	boolean validateForm() {
		String maNV = txt_manv.getText();
		String matKhau = txt_matkhau.getText();
		String hoTen = txt_hovaten.getText();

		//ktra ma nv chỉ có số va chữ
		if (maNV.isEmpty() || matKhau.isEmpty() || hoTen.isEmpty()) {
			// Hiển thị thông báo nếu có thông tin bị bỏ trống
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false; // Trả về false vì form chưa hợp lệ
		}
		if (!maNV.matches("^[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(null, "Mã NV chỉ được chứa chữ cái và số");
			return false;
		}

		// Kiểm tra tên tiếng Việt không chứa số và ký tự đặc biệt
		if (!hoTen.matches("^[\\p{L}\\s]+$")) {
			JOptionPane.showMessageDialog(null, "Họ tên không hợp lệ!");
			return false;
		}

		if (!matKhau.matches("^[a-zA-Z0-9]+$")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu chỉ được chứa chữ cái và số");
			return false;
		}
		if (new String(txt_matkhau.getPassword()).equals("")) {
			MsgBox.alter(this, "Vui lòng nhập mật khẩu!");
			return false;
		}
		if (new String(txt_xacnhanmk.getPassword()).equals("")) {
			MsgBox.alter(this, "Vui lòng nhập lại mật khẩu!");
			txt_xacnhanmk.requestFocus();
			return false;
		}
		if (!new String(txt_matkhau.getPassword()).equals(new String(txt_xacnhanmk.getPassword()))) {
			MsgBox.alter(this, "Mật khẩu không trùng khớp!");
			return false;
		}
		return true;

	}

//    void clearForm() {
//        this.setForm(new NhanVien());
//        this.updateStatus();
//        row = -1;
//        updateStatus();
//    }
	void clearForm() {
		NhanVien nv = new NhanVien();
		this.setForm(nv);
		this.row = -1;
		this.updateStatus();
	}

	void insert() {
		NhanVien model = getForm();
		String confirm = new String(txt_xacnhanmk.getPassword());
		if (confirm.equals(model.getMatKhau())) {
			try {
				dao.insert(model);
				this.fillTable();
				this.clearForm();
				MsgBox.alter(this, "Thêm mới thành công");
			} catch (Exception e) {
				MsgBox.alter(this, "thêm mới thất bại !");
			}
		} else {
			MsgBox.alter(this, "Mật khẩu xác nhận không đúng @@");
		}
	}

	void update() {
		NhanVien model = getForm();
		String confirm = new String(txt_xacnhanmk.getPassword());
		if (!confirm.equals(model.getMatKhau())) {
			MsgBox.alter(this, "Xác nhận mật khẩu không đúng @@");
		} else {
			try {
				dao.update(model);
				this.fillTable();
				MsgBox.alter(this, "Cập nhật thành công !");
			} catch (Exception e) {
				MsgBox.alter(this, "Cập nhật thất bại !");
			}
		}
	}

//    void delete() {
//        if (!Auth.isManager()) {
//            MsgBox.alter(this, "Bạn không có quyền xóa nhân viên !");
//        } else {
//            if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này ?")) {
//                String manv = txt_manv.getText();
////                try {
//                    dao.delete(manv);
//                    this.fillTable();
//                    this.clearForm();
//                    MsgBox.alter(this, "Xóa thành công !");
////                } catch (Exception e) {
////                    MsgBox.alter(this, "Xóa thất bại !");
//        //}
//            }
//        }
//
//    }
	void delete() {
		if (!Auth.isManager()) {
			MsgBox.alter(this, "Bạn không có quyền xoá!");
		} else {
			try {
				if (MsgBox.confirm(this, "Bạn thực sự muốn xoá người dùng này?")) {
					String maNV = txt_manv.getText();
					dao.delete(maNV);
					this.fillTable();
					this.clearForm();
					MsgBox.alter(this, "Xoá thành công!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				MsgBox.alter(this, "Xoá thất bại vì NV này có liên kết với Bảng khác");
			}
		}
	}

	void first() {
		row = 0;
		edit();
	}

	void prev() {
		if (row > 0) {
			row--;
			edit();
		}
	}

	void next() {
		if (row < tbl_nhanvien.getRowCount() - 1) {
			row++;
			edit();
		}
	}

	void last() {
		row = tbl_nhanvien.getRowCount() - 1;
		edit();
	}


        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                btn_Vaitro = new javax.swing.ButtonGroup();
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
                txt_manv = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txt_hovaten = new javax.swing.JTextField();
                txt_matkhau = new javax.swing.JPasswordField();
                txt_xacnhanmk = new javax.swing.JPasswordField();
                jPanel4 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tbl_nhanvien = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(0, 0, 255));
                jLabel1.setText("QUẢN LÝ NHÂN VIÊN QUẢN TRỊ");

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

                jLabel2.setText("Mã nhân viên");

                jLabel3.setText("Mật khẩu ");

                jLabel4.setText("Xác nhận mật khẩu ");

                jLabel5.setText("Họ và tên");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_manv))
                                        .addComponent(txt_hovaten)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txt_matkhau)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5))
                                                .addGap(0, 476, Short.MAX_VALUE))
                                        .addComponent(txt_xacnhanmk))
                                .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_xacnhanmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(95, Short.MAX_VALUE))
                );

                tabs.addTab("Cập Nhật", jPanel3);

                tbl_nhanvien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                tbl_nhanvien.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Mã NV", "Mật khẩu", "Họ và Tên"
                        }
                ) {
                        boolean[] canEdit = new boolean [] {
                                false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tbl_nhanvien.addAncestorListener(new javax.swing.event.AncestorListener() {
                        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                tbl_nhanvienAncestorAdded(evt);
                        }
                        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                        }
                        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                        }
                });
                tbl_nhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tbl_nhanvienMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                tbl_nhanvienMousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(tbl_nhanvien);

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 21, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                tabs.addTab("Danh Sách", jPanel4);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(tabs, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tbl_nhanvienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvienMousePressed
	    int selectedRow = tbl_nhanvien.getSelectedRow();
	    if (selectedRow != -1 && evt.getClickCount() == 2) {
		    this.row = selectedRow; // Gán giá trị row bằng hàng được chọn
		    edit();
	    }
    }//GEN-LAST:event_tbl_nhanvienMousePressed

    private void tbl_nhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvienMouseClicked
//        int selectedRow = tbl_nhanvien.getSelectedRow();
//        if (evt.getClickCount() == 2 &&selectedRow != -1) {
//            this.row = selectedRow; // Gán giá trị row bằng hàng được chọn
//            edit();
//        }
    }//GEN-LAST:event_tbl_nhanvienMouseClicked

        private void btn_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moiActionPerformed
		clearForm();
        }//GEN-LAST:event_btn_moiActionPerformed

        private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
		delete();
        }//GEN-LAST:event_btn_xoaActionPerformed

        private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
		if (validateForm()) {
			update();
		}
        }//GEN-LAST:event_btn_suaActionPerformed

        private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
		if (validateForm()) {
			insert();
		}
        }//GEN-LAST:event_btn_themActionPerformed

        private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
		last();
        }//GEN-LAST:event_btn_lastActionPerformed

        private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
		next();
        }//GEN-LAST:event_btn_nextActionPerformed

        private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
		prev();
        }//GEN-LAST:event_btn_prevActionPerformed

        private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
		first();
        }//GEN-LAST:event_btn_firstActionPerformed

    private void tbl_nhanvienAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbl_nhanvienAncestorAdded
	    // TODO add your handling code here:
    }//GEN-LAST:event_tbl_nhanvienAncestorAdded

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
			java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				NhanVienJDialog dialog = new NhanVienJDialog(new javax.swing.JFrame(), true);
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
        private javax.swing.ButtonGroup btn_Vaitro;
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
        private javax.swing.JTable tbl_nhanvien;
        private javax.swing.JTextField txt_hovaten;
        private javax.swing.JTextField txt_manv;
        private javax.swing.JPasswordField txt_matkhau;
        private javax.swing.JPasswordField txt_xacnhanmk;
        // End of variables declaration//GEN-END:variables
}
