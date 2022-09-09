/*
 * ProductBuyInterFrm.java
 *
 * Created on __DATE__, __TIME__
 */

package com.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.dao.ProductChosenDao;
import com.dao.ProductDao;
import com.dao.ProductTypeDao;
import com.model.Product;
import com.model.ProductType;
import com.util.Dbutil;
import com.util.StringUtil;

/**
 *
 * @author  __USER__
 */
public class ProductBuyInterFrm extends javax.swing.JInternalFrame {

	Dbutil dbutil = new Dbutil();
	ProductDao productDao = new ProductDao();
	ProductTypeDao productTypeDao = new ProductTypeDao();
	ProductChosenDao productChosenDao = new ProductChosenDao();

	/** Creates new form ProductBuyInterFrm */
	public ProductBuyInterFrm() {
		initComponents();
		this.setLocation(200, 20);
		this.fillTable(new Product());
		this.fillChosenTable();
		this.fillProductType("search");
		this.fillProductType("putInto");
	}

	private void fillProductType(String type) {
		Connection con = null;
		ProductType productType = null;
		try {
			con = dbutil.getCon();
			ResultSet rs = productTypeDao.productTypeList(con,
					new ProductType());

			if ("search".equals(type)) {

				productType = new ProductType();
				productType.setProductTypeName("请选择....");
				productType.setId(-1);
				this.s_jcbProductType.addItem(productType);
			}
			while (rs.next()) {
				productType = new ProductType();
				productType.setId(rs.getInt("id"));
				productType.setProductTypeName(rs.getString("productTypeName"));

				if ("search".equals(type)) {
					this.s_jcbProductType.addItem(productType);
				} else if ("putInto".equals(type)) {
					this.jcb_productType.addItem(productType);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void fillTable(Product product) {
		DefaultTableModel dtm = (DefaultTableModel) productTable.getModel();

		dtm.setRowCount(0);//要把前面的数据释放掉
		Connection con = null;
		try {
			con = dbutil.getCon();
			ResultSet rs = productDao.productList(con, product);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("productName"));
				v.add(rs.getString("productTime"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("productDesc"));
				v.add(rs.getString("productTypeName"));

				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void fillChosenTable() {
		DefaultTableModel dtm = (DefaultTableModel) ProductChosenTable
				.getModel();

		dtm.setRowCount(0);//要把前面的数据释放掉
		Connection con = null;
		try {
			con = dbutil.getCon();
			ResultSet rs = productDao.productChosenList(con);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getString("productName"));
				v.add(rs.getString("productTime"));
				v.add(rs.getFloat("price"));
				v.add(rs.getString("productDesc"));
				v.add(rs.getString("productTypeName"));

				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jDialog1 = new javax.swing.JDialog();
jPanel1 = new javax.swing.JPanel();
jLabel1 = new javax.swing.JLabel();
s_productNameTxt = new javax.swing.JTextField();
jLabel2 = new javax.swing.JLabel();
s_productTimeTxt = new javax.swing.JTextField();
jLabel3 = new javax.swing.JLabel();
s_jcbProductType = new javax.swing.JComboBox();
jb_search = new javax.swing.JButton();
jScrollPane1 = new javax.swing.JScrollPane();
productTable = new javax.swing.JTable();
jPanel2 = new javax.swing.JPanel();
jScrollPane3 = new javax.swing.JScrollPane();
ProductChosenTable = new javax.swing.JTable();
jb_delete = new javax.swing.JButton();
jb_cost = new javax.swing.JButton();
jb_putProductIntoCar = new javax.swing.JButton();
jScrollPane2 = new javax.swing.JScrollPane();
productDescTxt = new javax.swing.JTextArea();
jLabel9 = new javax.swing.JLabel();
jcb_productType = new javax.swing.JComboBox();
jLabel8 = new javax.swing.JLabel();
productTimeTxt = new javax.swing.JTextField();
jLabel7 = new javax.swing.JLabel();
priceTxt = new javax.swing.JTextField();
jLabel6 = new javax.swing.JLabel();
productNameTxt = new javax.swing.JTextField();
jLabel5 = new javax.swing.JLabel();
idTxt = new javax.swing.JTextField();
jLabel4 = new javax.swing.JLabel();

javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
jDialog1.getContentPane().setLayout(jDialog1Layout);
jDialog1Layout.setHorizontalGroup(
jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGap(0, 400, Short.MAX_VALUE)
);
jDialog1Layout.setVerticalGroup(
jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGap(0, 300, Short.MAX_VALUE)
);

setClosable(true);
setIconifiable(true);

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("\u6311\u9009\u5546\u54c1"));

jLabel1.setText("\u5546\u54c1\u540d\u79f0");

jLabel2.setText("\u751f\u4ea7\u65e5\u671f");

jLabel3.setText("\u5546\u54c1\u7c7b\u522b");


jb_search.setIcon(new javax.swing.ImageIcon("./images\\search.png")); // NOI18N
jb_search.setText("\u67e5\u8be2");
jb_search.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_searchActionPerformed(evt);
}
});

productTable.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"编号", "商品名称", "生产日期", "商品价格", "商品描述", "商品类别"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
productTable.addMouseListener(new java.awt.event.MouseAdapter() {
public void mousePressed(java.awt.event.MouseEvent evt) {
productTableMousePressed(evt);
}
});
jScrollPane1.setViewportView(productTable);

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGap(20, 20, 20)
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(s_productNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(s_productTimeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jLabel3)
.addGap(18, 18, 18)
.addComponent(s_jcbProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
.addComponent(jb_search)
.addGap(38, 38, 38))
.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(s_productNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel2)
.addComponent(s_productTimeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel3)
.addComponent(s_jcbProductType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jb_search))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
);

jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("\u5bf9\u5df2\u9009\u5546\u54c1\u64cd\u4f5c"));

ProductChosenTable.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"编号", "商品名称", "生产日期", "商品价格", "商品描述", "商品类别"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
ProductChosenTable.addMouseListener(new java.awt.event.MouseAdapter() {
public void mousePressed(java.awt.event.MouseEvent evt) {
ProductChosenTableMousePressed(evt);
}
});
jScrollPane3.setViewportView(ProductChosenTable);

jb_delete.setIcon(new javax.swing.ImageIcon("./images\\delete.png")); // NOI18N
jb_delete.setText("\u5220\u9664\u8be5\u5df2\u9009\u5546\u54c1");
jb_delete.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_deleteActionPerformed(evt);
}
});

jb_cost.setIcon(new javax.swing.ImageIcon("./images\\money.png")); // NOI18N
jb_cost.setText("\u7ed3\u7b97");
jb_cost.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_costActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
jPanel2.setLayout(jPanel2Layout);
jPanel2Layout.setHorizontalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
.addGap(118, 118, 118)
.addComponent(jb_delete)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
.addComponent(jb_cost)
.addGap(198, 198, 198))
.addGroup(jPanel2Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
.addContainerGap())
);
jPanel2Layout.setVerticalGroup(
jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel2Layout.createSequentialGroup()
.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jb_delete)
.addComponent(jb_cost))
.addContainerGap(36, Short.MAX_VALUE))
);

jb_putProductIntoCar.setText("\u628a\u8be5\u5546\u54c1\u52a0\u5165\u8d2d\u7269\u8f66");
jb_putProductIntoCar.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jb_putProductIntoCarActionPerformed(evt);
}
});

productDescTxt.setColumns(20);
productDescTxt.setEditable(false);
productDescTxt.setRows(5);
jScrollPane2.setViewportView(productDescTxt);

jLabel9.setText("\u5546\u54c1\u63cf\u8ff0");


jLabel8.setText("\u5546\u54c1\u7c7b\u522b");

productTimeTxt.setEditable(false);

jLabel7.setText("\u751f\u4ea7\u65e5\u671f");

priceTxt.setEditable(false);

jLabel6.setText("\u4ef7\u683c");

productNameTxt.setEditable(false);

jLabel5.setText("\u5546\u54c1\u540d\u79f0");

idTxt.setEditable(false);

jLabel4.setText("\u7f16\u53f7");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addContainerGap())
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGap(89, 89, 89)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addComponent(jLabel6)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel7)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(productTimeTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
.addGap(12, 12, 12)
.addComponent(jLabel8)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jcb_productType, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(21, 21, 21))
.addGroup(layout.createSequentialGroup()
.addComponent(jLabel4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel5)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(productNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(12, 12, 12)))
.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jb_putProductIntoCar)
.addGap(22, 22, 22)))
.addGap(64, 64, 64))
.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(15, 15, 15)
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel5)
.addComponent(productNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel9)
.addComponent(jLabel4))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jcb_productType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel8)
.addComponent(productTimeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel7)
.addComponent(priceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel6))))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jb_putProductIntoCar)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jb_costActionPerformed(java.awt.event.ActionEvent evt) {

		new CustomerLogOnFrm().setVisible(true);
	}

	private void ProductChosenTableMousePressed(java.awt.event.MouseEvent evt) {
		//获取选中的行
		int row = this.ProductChosenTable.getSelectedRow();
		this.idTxt
				.setText((Integer) ProductChosenTable.getValueAt(row, 0) + "");
		this.productNameTxt.setText((String) ProductChosenTable.getValueAt(row,
				1));
		this.productTimeTxt.setText((String) ProductChosenTable.getValueAt(row,
				2));
		this.priceTxt.setText((Float) ProductChosenTable.getValueAt(row, 3)
				+ "");
		this.productDescTxt.setText((String) ProductChosenTable.getValueAt(row,
				4));
		String productTypeName = (String) ProductChosenTable.getValueAt(row, 5);
		int n = this.jcb_productType.getItemCount();
		for (int i = 0; i < n; i++) {
			ProductType item = (ProductType) this.jcb_productType.getItemAt(i);
			if (item.getProductTypeName().equals(productTypeName)) {
				this.jcb_productType.setSelectedIndex(i);
			}
		}
	}

	private void jb_deleteActionPerformed(java.awt.event.ActionEvent evt) {
		//获取编号
		String id = this.idTxt.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的商品");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除这个商品吗？");
		if (n == 0) {
			Connection con = null;
			try {
				con = dbutil.getCon();
				int deleteNum = productDao.productChosenDelete(con, id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillChosenTable();
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			} finally {
				try {
					dbutil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void jb_putProductIntoCarActionPerformed(
			java.awt.event.ActionEvent evt) {

		String productName = this.productNameTxt.getText();
		String productTime = this.productTimeTxt.getText();
		String price = this.priceTxt.getText();
		String productDesc = this.productDescTxt.getText();
		//取值后要判空
		if (StringUtil.isEmpty(productName)) {
			JOptionPane.showMessageDialog(null, " 请选择商品!");
			return;
		}

		ProductType productType = (ProductType) this.jcb_productType
				.getSelectedItem();

		int productTypeId = productType.getId();

		Product product = new Product(productName, productTime, Float
				.parseFloat(price), productDesc, productTypeId);

		Connection con = null;
		try {
			con = dbutil.getCon();
			int addNum = productDao.productAddIntoCar(con, product);
			if (addNum == 1) {
				JOptionPane.showMessageDialog(null, "商品加入购物车成功");
				this.resetValue();
				this.fillChosenTable();

			} else {
				JOptionPane.showMessageDialog(null, "商品加入购物车失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "商品加入购物车失败");

		} finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValue() {
		this.idTxt.setText("");
		this.productNameTxt.setText("");
		this.productTimeTxt.setText("");
		this.priceTxt.setText("");
		if (this.jcb_productType.getItemCount() > 0) {

			this.jcb_productType.setSelectedIndex(0);
		}
		this.productDescTxt.setText("");
	}

	private void jb_searchActionPerformed(java.awt.event.ActionEvent evt) {
		String productName = this.s_productNameTxt.getText();
		String productTime = this.s_productTimeTxt.getText();
		ProductType productType = (ProductType) this.s_jcbProductType
				.getSelectedItem();
		int productTypeId = productType.getId();
		Product product = new Product(productName, productTime, productTypeId);
		this.fillTable(product);
	}

	private void productTableMousePressed(java.awt.event.MouseEvent evt) {
		//获取选中的行
		int row = this.productTable.getSelectedRow();
		this.idTxt.setText((Integer) productTable.getValueAt(row, 0) + "");
		this.productNameTxt.setText((String) productTable.getValueAt(row, 1));
		this.productTimeTxt.setText((String) productTable.getValueAt(row, 2));
		this.priceTxt.setText((Float) productTable.getValueAt(row, 3) + "");
		this.productDescTxt.setText((String) productTable.getValueAt(row, 4));
		String productTypeName = (String) productTable.getValueAt(row, 5);
		int n = this.jcb_productType.getItemCount();
		for (int i = 0; i < n; i++) {
			ProductType item = (ProductType) this.jcb_productType.getItemAt(i);
			if (item.getProductTypeName().equals(productTypeName)) {
				this.jcb_productType.setSelectedIndex(i);
			}
		}
		this.jcb_productType.setEnabled(false);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JTable ProductChosenTable;
	private javax.swing.JTextField idTxt;
	private javax.swing.JDialog jDialog1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JButton jb_cost;
	private javax.swing.JButton jb_delete;
	private javax.swing.JButton jb_putProductIntoCar;
	private javax.swing.JButton jb_search;
	private javax.swing.JComboBox jcb_productType;
	private javax.swing.JTextField priceTxt;
	private javax.swing.JTextArea productDescTxt;
	private javax.swing.JTextField productNameTxt;
	private javax.swing.JTable productTable;
	private javax.swing.JTextField productTimeTxt;
	private javax.swing.JComboBox s_jcbProductType;
	private javax.swing.JTextField s_productNameTxt;
	private javax.swing.JTextField s_productTimeTxt;
	// End of variables declaration//GEN-END:variables

}