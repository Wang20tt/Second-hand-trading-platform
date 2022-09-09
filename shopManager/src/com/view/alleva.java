package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.evaluation;
import com.util.Dbutil;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class alleva extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					alleva frame = new alleva();
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
	public alleva() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea t1 = new JTextArea();
		scrollPane.setViewportView(t1);
		Dbutil db=new Dbutil();
	    Connection con;
	    ResultSet rs;
        try {
			con=db.getCon();
			Statement sql=con.createStatement();
	        String sql1="SELECT * FROM   t_evaluation";
	        rs=sql.executeQuery(sql1);
	        StringBuilder sb=new StringBuilder();
	        sb.append("商品名称\t评价\t时间\n");
	        while(rs.next()){
	        	evaluation m=new evaluation();
	  
	       	  sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\n");
	        }
	        rs.close();
	        t1.setText(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
       
		
	}
}
	
   


