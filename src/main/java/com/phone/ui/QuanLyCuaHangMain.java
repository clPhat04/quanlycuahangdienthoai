/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.phone.ui;

import com.phone.utils.Auth;
import com.phone.utils.MsgBox;
import com.phone.utils.XImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author PhatLe
 */
public class QuanLyCuaHangMain extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */
	public QuanLyCuaHangMain() {
		initComponents();
		init();
	}

	void init() {

		setIconImage(XImage.getAppIcon());
		setLocationRelativeTo(null);
		setTitle("Hệ Thống Quản Lý Đào Tạo Edusys");
		new Timer(1000, new ActionListener() {
			SimpleDateFormat fomat = new SimpleDateFormat("hh:mm:ss a");

			@Override
			public void actionPerformed(ActionEvent e) {
				LblDongHo.setText(fomat.format(new Date()));

			}
		}).start();
		this.openWelcome();
		this.openLogin();
	}

	void openWelcome() {
		new Chao(this, true).setVisible(true);

	}

	void openLogin() {
		new DangNhap(this, true).setVisible(true);
	}

	void openDoiMatKhau() {
		if (Auth.isLogin()) {
			new DoiPassJDialog(this, true).setVisible(true);
		} else {
			MsgBox.alter(this, "Vui lòng đăng nhập !");
		}

	}

	void ketThuc() {
		if (MsgBox.confirm(this, "Bạn muốn thoát")) {
			System.exit(0);
		}
	}

	void dangXuat() {
		Auth.clear();
		openLogin();
	}

	void openNhanVien() {
		if (Auth.isLogin()) {
			new NhanVienJDialog(this, true).setVisible(true);
		} else {
			MsgBox.alter(this, "Vui lòng đăng nhập !");
		}
	}

	void openThongKe() {
		if (Auth.isLogin()) {
			if (Auth.isManager()) {
				new ThongKeDialog(this, true).setVisible(true);
			} else {

				MsgBox.alter(this, "Bạn không đủ quyền để truy cập thống kê !");
			}
		} else {
			MsgBox.alter(this, "Vui lòng đăng nhập !");
		}

	}

	void openKhachhang() {
		new KhachHangjdo1(this, true).setVisible(true);
	}

	void openDonHang() {
		new DonHang2(this, true).setVisible(true);
	}

	void openSanPham() {
		new SanPhamJDialog(this, true).setVisible(true);

	}

	void opendathang() {
		new dathang1().setVisible(true);
	}


        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                btnDangxuat = new javax.swing.JButton();
                btnThoat = new javax.swing.JButton();
                btnNhanVien = new javax.swing.JButton();
                btnSanPham = new javax.swing.JButton();
                btnKhachHang = new javax.swing.JButton();
                btnDonHang = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                LblDongHo = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                jMenuBar1 = new javax.swing.JMenuBar();
                mnuHThong = new javax.swing.JMenu();
                mniLogin = new javax.swing.JMenuItem();
                mniDangxuat = new javax.swing.JMenuItem();
                mniDoimk = new javax.swing.JMenuItem();
                mniKetthuc = new javax.swing.JMenuItem();
                jMenu1 = new javax.swing.JMenu();
                jMenuItem5 = new javax.swing.JMenuItem();
                jMenuItem6 = new javax.swing.JMenuItem();
                jMenuItem7 = new javax.swing.JMenuItem();
                jMenu4 = new javax.swing.JMenu();
                jMenu3 = new javax.swing.JMenu();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnDangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Log out.png"))); // NOI18N
                btnDangxuat.setText("Đăng xuất");
                btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDangxuatActionPerformed(evt);
                        }
                });

                btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Stop.png"))); // NOI18N
                btnThoat.setText("Thoát");
                btnThoat.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnThoatActionPerformed(evt);
                        }
                });

                btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/User.png"))); // NOI18N
                btnNhanVien.setText("Nhân viên");
                btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnNhanVienActionPerformed(evt);
                        }
                });

                btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Camera.png"))); // NOI18N
                btnSanPham.setText("Sản phẩm");
                btnSanPham.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSanPhamActionPerformed(evt);
                        }
                });

                btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Boy.png"))); // NOI18N
                btnKhachHang.setText("Khách hàng");
                btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnKhachHangActionPerformed(evt);
                        }
                });

                btnDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Full basket.png"))); // NOI18N
                btnDonHang.setText("Đơn hàng");
                btnDonHang.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDonHangActionPerformed(evt);
                        }
                });

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/logo100px.jpg"))); // NOI18N

                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Billboard.png"))); // NOI18N
                jButton1.setText("Thống kê");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                        }
                });

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Add to basket.png"))); // NOI18N
                jButton2.setText("Đặt hàng");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel4))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnDangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)
                                .addComponent(btnDangxuat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThoat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNhanVien)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSanPham)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKhachHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDonHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Zoom out.png"))); // NOI18N
                jLabel2.setText("CỬA HÀNG ĐIỆN THOẠI N9");

                LblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Clock.png"))); // NOI18N
                LblDongHo.setText("00:23:00 SA");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 736, Short.MAX_VALUE)
                                .addComponent(LblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LblDongHo)
                                .addComponent(jLabel2))
                );

                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/nencuahang.jpg"))); // NOI18N
                jLabel1.setText("jLabel1");

                mnuHThong.setText("Hệ thống");

                mniLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
                mniLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Login.png"))); // NOI18N
                mniLogin.setText("Đăng nhập ");
                mniLogin.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mniLoginActionPerformed(evt);
                        }
                });
                mnuHThong.add(mniLogin);

                mniDangxuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
                mniDangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Log out.png"))); // NOI18N
                mniDangxuat.setText("Đăng xuất");
                mniDangxuat.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mniDangxuatActionPerformed(evt);
                        }
                });
                mnuHThong.add(mniDangxuat);

                mniDoimk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Refresh.png"))); // NOI18N
                mniDoimk.setText("Đổi mật khẩu");
                mniDoimk.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mniDoimkActionPerformed(evt);
                        }
                });
                mnuHThong.add(mniDoimk);

                mniKetthuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
                mniKetthuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Stop.png"))); // NOI18N
                mniKetthuc.setText("Kết thúc");
                mniKetthuc.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                mniKetthucActionPerformed(evt);
                        }
                });
                mnuHThong.add(mniKetthuc);

                jMenuBar1.add(mnuHThong);

                jMenu1.setText("Quản lý");

                jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
                jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/User.png"))); // NOI18N
                jMenuItem5.setText("Nhân viên");
                jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem5ActionPerformed(evt);
                        }
                });
                jMenu1.add(jMenuItem5);

                jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
                jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Camera.png"))); // NOI18N
                jMenuItem6.setText("Sản phẩm");
                jMenu1.add(jMenuItem6);

                jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
                jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/phone/icon/Boy.png"))); // NOI18N
                jMenuItem7.setText("Khách hàng");
                jMenu1.add(jMenuItem7);

                jMenuBar1.add(jMenu1);

                jMenu4.setText("Đơn hàng ");
                jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jMenu4MouseClicked(evt);
                        }
                });
                jMenuBar1.add(jMenu4);

                jMenu3.setText("Thống kê");
                jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jMenu3MouseClicked(evt);
                        }
                });
                jMenuBar1.add(jMenu3);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void mniLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoginActionPerformed
		openLogin();
        }//GEN-LAST:event_mniLoginActionPerformed

        private void mniDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangxuatActionPerformed
		dangXuat();
        }//GEN-LAST:event_mniDangxuatActionPerformed

        private void mniDoimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoimkActionPerformed
		openDoiMatKhau();
        }//GEN-LAST:event_mniDoimkActionPerformed

        private void mniKetthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetthucActionPerformed
		ketThuc();
        }//GEN-LAST:event_mniKetthucActionPerformed

        private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
		dangXuat();
        }//GEN-LAST:event_btnDangxuatActionPerformed

        private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
		openNhanVien();
        }//GEN-LAST:event_btnNhanVienActionPerformed

        private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
		openNhanVien();
        }//GEN-LAST:event_jMenuItem5ActionPerformed

        private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
		ketThuc();
        }//GEN-LAST:event_btnThoatActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
	    // TODO add your handling code here:
	    openKhachhang();

    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
	    // TODO add your handling code here:
	    openSanPham();
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonHangActionPerformed
	    // TODO add your handling code here:
	    openDonHang();
    }//GEN-LAST:event_btnDonHangActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		openThongKe();
        }//GEN-LAST:event_jButton1ActionPerformed

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		opendathang();
        }//GEN-LAST:event_jButton2ActionPerformed

        private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
                // TODO add your handling code here:
		openDonHang();
        }//GEN-LAST:event_jMenu4MouseClicked

        private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
                // TODO add your handling code here:
		openThongKe();
        }//GEN-LAST:event_jMenu3MouseClicked

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
			java.util.logging.Logger.getLogger(QuanLyCuaHangMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(QuanLyCuaHangMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(QuanLyCuaHangMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(QuanLyCuaHangMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new QuanLyCuaHangMain().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel LblDongHo;
        private javax.swing.JButton btnDangxuat;
        private javax.swing.JButton btnDonHang;
        private javax.swing.JButton btnKhachHang;
        private javax.swing.JButton btnNhanVien;
        private javax.swing.JButton btnSanPham;
        private javax.swing.JButton btnThoat;
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenu jMenu3;
        private javax.swing.JMenu jMenu4;
        private javax.swing.JMenuBar jMenuBar1;
        private javax.swing.JMenuItem jMenuItem5;
        private javax.swing.JMenuItem jMenuItem6;
        private javax.swing.JMenuItem jMenuItem7;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JMenuItem mniDangxuat;
        private javax.swing.JMenuItem mniDoimk;
        private javax.swing.JMenuItem mniKetthuc;
        private javax.swing.JMenuItem mniLogin;
        private javax.swing.JMenu mnuHThong;
        // End of variables declaration//GEN-END:variables
}
