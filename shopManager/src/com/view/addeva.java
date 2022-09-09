package com.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.Dbutil;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class addeva extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addeva frame = new addeva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public addeva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
			 
	
	
		         
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(127, 31, 156, 21);
		contentPane.add(comboBox);
		
	     Connection con;
	     Dbutil db=new Dbutil();
	    ResultSet rs = null;
         try {
			con=db.getCon();
			Statement sql=con.createStatement();
	         String sql1="SELECT * FROM   t_product";
	          rs=sql.executeQuery(sql1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
         try {
			while(rs.next()){
				 comboBox.addItem(rs.getString(2).toString());
				
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u5546\u54C1");
		lblNewLabel.setBounds(51, 34, 54, 15);
		contentPane.add(lblNewLabel);
		
		final JTextArea t1 = new JTextArea();
		t1.setBounds(127, 86, 161, 74);
		contentPane.add(t1);
		
		JLabel label_1 = new JLabel("\u6211\u7684\u8BC4\u4EF7");
		label_1.setBounds(51, 122, 54, 15);
		contentPane.add(label_1);
		
		JButton button = new JButton("\u7ACB\u5373\u8BC4\u4EF7");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboBox.getSelectedItem().toString();
				Date date = new Date();
				SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
				System.out.println(dateFormat.format(date));
			     Connection con;
			     Dbutil db=new Dbutil();
			     int rs = 0;
				 try {
					con=db.getCon();
					Statement sql=con.createStatement();
			         String sql1="INSERT INTO `t_evaluation`  VALUES ('"+comboBox.getSelectedItem().toString()+"','"+t1.getText().trim()+"', '"+dateFormat.format(date)+"');";
		
			        rs=sql.executeUpdate(sql1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
          if(rs!=0)
          {
       	   JOptionPane.showMessageDialog(null,"Ìí¼Ó³É¹¦£¡","", JOptionPane.INFORMATION_MESSAGE);
          }
				
				   
			}
		});
		button.setBounds(151, 207, 93, 23);
		contentPane.add(button);
	}
}
