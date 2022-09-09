/*
 * ProductTypeInterFrm.java
 *
 * Created on __DATE__, __TIME__
 */

package com.view;

import java.sql.Connection;

import javax.swing.JOptionPane;

import com.dao.ProductTypeDao;
import com.model.ProductType;
import com.util.Dbutil;
import com.util.StringUtil;

/**
 *
 * @author  __USER__
 */
public class ProductTypeInterFrm extends javax.swing.JInternalFrame {
	Dbutil dbutil = new Dbutil();
	ProductTypeDao productTypeDao = new ProductTypeDao();

	/** Creates new form ProductTypeInterFrm */
	public ProductTypeInterFrm() {
		initComponents();
		//InterFrm的位置设置
		this.setLocation(300, 100);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jb_Add = new javax.swing.JButton();
		jb_reset = new javax.swing.JButton();
		productTypeNameTxt = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		productTypeDescTxt = new javax.swing.JTextArea();

		setClosable(true);
		setIconifiable(true);
		setTitle("\u5546\u54c1\u7c7b\u522b\u6dfb\u52a0");

		jLabel1.setText("\u5546\u54c1\u7c7b\u522b\u540d\u79f0");

		jLabel2.setText("\u5546\u54c1\u7c7b\u522b\u63cf\u8ff0");

		jb_Add.setIcon(new javax.swing.ImageIcon(
				"./images\\add.png")); // NOI18N
		jb_Add.setText("\u6dfb\u52a0");
		jb_Add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_AddActionPerformed(evt);
			}
		});

		jb_reset.setIcon(new javax.swing.ImageIcon(
				"./images\\reset.png")); // NOI18N
		jb_reset.setText("\u91cd\u7f6e");
		jb_reset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jb_resetActionPerformed(evt);
			}
		});

		productTypeDescTxt.setColumns(20);
		productTypeDescTxt.setRows(5);
		jScrollPane1.setViewportView(productTypeDescTxt);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(41, 41, 41)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jb_Add)
																		.addGap(
																				88,
																				88,
																				88)
																		.addComponent(
																				jb_reset))
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								jLabel2))
																		.addGap(
																				18,
																				18,
																				18)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								productTypeNameTxt)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								318,
																								Short.MAX_VALUE))))
										.addContainerGap(20, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(46, 46, 46)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																productTypeNameTxt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(37, 37, 37)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel2)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												43, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jb_Add)
														.addComponent(jb_reset))
										.addGap(61, 61, 61)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jb_AddActionPerformed(java.awt.event.ActionEvent evt) {
		String productTypeName=this.productTypeNameTxt.getText();
		String productTypeDesc=this.productTypeDescTxt.getText();
		//要使用Util语句前要先在开头创个Dbutil的对象,同理也要引进Dao对象
		if(StringUtil.isEmpty(productTypeName)){
			JOptionPane.showMessageDialog(null, "商品类别不能为空");
			return;
		}
		ProductType productType=new ProductType(productTypeName, productTypeDesc);
		Connection con=null;
		try {
			con=dbutil.getCon();
			int n=productTypeDao.productTypeAdd(con, productType);
			if(n==1){
				JOptionPane.showMessageDialog(null,"商品类别添加成功");
				this.resetValues();
			}else{
				JOptionPane.showMessageDialog(null,"商品类别添加失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"商品类别添加失败");
		}
	}

	private void jb_resetActionPerformed(java.awt.event.ActionEvent evt) {
		this.resetValues();
	}

	private void resetValues() {
		this.productTypeNameTxt.setText("");
		this.productTypeDescTxt.setText("");
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton jb_Add;
	private javax.swing.JButton jb_reset;
	private javax.swing.JTextArea productTypeDescTxt;
	private javax.swing.JTextField productTypeNameTxt;
	// End of variables declaration//GEN-END:variables

}