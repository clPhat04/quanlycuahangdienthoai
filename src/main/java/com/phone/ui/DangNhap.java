/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.phone.ui;

import com.phone.dao.NhanVienDAO;
import com.phone.utils.Auth;
import com.phone.utils.MsgBox;
import com.phone.utils.XImage;
import javax.swing.JOptionPane;
import com.phone.entity.NhanVien;

/**
 *
 * @author PhatLe
 */
public class DangNhap extends javax.swing.JDialog {

	NhanVienDAO dao = new NhanVienDAO();

	/**
	 * Creates new form DangNhap
	 */
	public DangNhap(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		init();
	}

	void init() {
		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		setTitle(" Đăng Nhập");
	}

	void KetThuc() {
		if (MsgBox.confirm(this, "Bạn có muốn thoát")) {
			System.exit(0);
		}
	}

	void dangNhap() {
		String username = txt_usename.getText();
		String password = new String(txt_password.getPassword());

		if (username.length() < 3 || username.length() >= 50) {
			JOptionPane.showMessageDialog(this, "vui lòng nhập Username và Username phải có ít nhất 3 và không quá 50 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else if (password.length() < 3 || password.length() >= 50) {
			JOptionPane.showMessageDialog(this, "vui lòng nhập Password và Password phải có ít nhất 3 và không quá 50 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
		} else {
			// Thực hiện kiểm tra tên đăng nhập và mật khẩu
			String manv = txt_usename.getText();
			String matkhau = new String(txt_password.getPassword());
			NhanVien nhanVien = dao.selectById(manv);
//        NhanVien nhanVien = dao.selectById(manv);
			if (nhanVien == null) {
				MsgBox.alter(this, "Sai tên đăng nhập !");
			} else if (!matkhau.equals(nhanVien.getMatKhau())) {

				MsgBox.alter(this, "Sai mật khẩu !");
			} else {
				Auth.user = nhanVien;
				this.dispose();
			}
		}
	}

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                txt_usename = new javax.swing.JTextField();
                txt_password = new javax.swing.JPasswordField();
                btn_login = new javax.swing.JButton();
                btn_logout = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jLabel1.setText("TÊN ĐĂNG NHẬP");

                jLabel3.setText("MẬT KHẨU");

                txt_usename.setText("Admin1");
                txt_usename.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txt_usenameActionPerformed(evt);
                        }
                });

                txt_password.setText("pass123");
                txt_password.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txt_passwordActionPerformed(evt);
                        }
                });

                btn_login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                btn_login.setText("Đăng nhập");
                btn_login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btn_login.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btn_login.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_loginActionPerformed(evt);
                        }
                });

                btn_logout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                btn_logout.setText("Kết Thúc");
                btn_logout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btn_logout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                btn_logout.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_logoutActionPerformed(evt);
                        }
                });

                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/logo small.jpg"))); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_usename, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(54, 54, 54)
                                                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(0, 8, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_usename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_login)
                                                        .addComponent(btn_logout)))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void txt_usenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usenameActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_txt_usenameActionPerformed

        private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
		dangNhap();

        }//GEN-LAST:event_btn_loginActionPerformed

        private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
		KetThuc();
        }//GEN-LAST:event_btn_logoutActionPerformed

        private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
		// TODO add your handling code here:
        }//GEN-LAST:event_txt_passwordActionPerformed

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
			java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				DangNhap dialog = new DangNhap(new javax.swing.JFrame(), true);
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
        private javax.swing.JButton btn_login;
        private javax.swing.JButton btn_logout;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JPasswordField txt_password;
        private javax.swing.JTextField txt_usename;
        // End of variables declaration//GEN-END:variables
}
