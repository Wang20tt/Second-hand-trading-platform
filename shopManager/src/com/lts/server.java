package com.lts;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.lts.ServerThread;





/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class server{
    //�½����岢���ô������
    public JFrame frame = new JFrame("��ϵ����");
    public JPanel panel = null;
    private JTextArea showmess;
	private JTextField smess;
    private JButton send;
    private JTextArea showuser;
	private JLabel user;
    private JButton closeserver;
    private JButton startserver;
    private JTextArea showmax;
    private JLabel maxnum;
    private JTextArea showdkh;
    private JLabel dkh;
    
    ServerSocket server;
    static server inst;
    ServerThread sthread;
    //get��set����
    public JTextArea getShowmess() {
		return showmess;
	}
	public void setShowmess(JTextArea showmess) {
		this.showmess = showmess;
	}
	public JTextArea getShowuser() {
		return showuser;
	}
	public void setShowuser(JTextArea showuser) {
		this.showuser = showuser;
	}
    //���췽��
    public server()
    {
        //���ع��췽��ʱ��ʼ��
        init();
    }
    //
    @SuppressWarnings("serial")
    public void init(){
        //�������ֹ�����
		panel = new JPanel() {
			// ��ȡ�����������
			public void paintComponent(Graphics g) {
				// �趨����ͼ·����ͼƬ��·��Ҫ���������Լ������·��Ҳ���ԣ��������þ���·����
				String imagePath ="src/4.jpg";
				// ����ͼƬ��
				ImageIcon icon = new ImageIcon(imagePath);
				// ����ͼƬ���ȡͼƬ
				Image image = icon.getImage();
				// �滭
				g.drawImage(image, 0, 0, icon.getIconWidth(),icon.getIconHeight(), icon.getImageObserver());
				// ��ȡͼƬ�����Ϊ������
				frame.setSize(icon.getIconWidth(), icon.getIconHeight());
			}
		};
        //�Ѳ����趨��������
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
        //����������Ϊ����
        frame.setPreferredSize(new java.awt.Dimension(794, 482));
        //���������С
        frame.pack();
        frame.setSize(794, 482);
        //==================================================================
        {
        	dkh = new JLabel();
        	panel.add(dkh);
        	dkh.setText("\u7aef\u53e3\u53f7");
        	dkh.setBounds(12, 45, 58, 26);
        	dkh.setFont(new java.awt.Font("��Բ",1,18));
        	dkh.setForeground(Color.white);
        }
        {
        	showdkh = new JTextArea();
        	panel.add(showdkh);
        	showdkh.setText("6666");
        	showdkh.setBounds(80, 45, 54, 23);
        	showdkh.setFont(new java.awt.Font("������ͤ��ϸ�ڼ���",1,15));
        	showdkh.setForeground(Color.black);
        }
        {
        	maxnum = new JLabel();
        	panel.add(maxnum);
        	maxnum.setText("\u6700\u5927\u4eba\u6570");
        	maxnum.setBounds(146, 49, 77, 16);
        	maxnum.setFont(new java.awt.Font("��Բ",1,18));
        	maxnum.setForeground(Color.white);
        }
        {
        	showmax = new JTextArea();
        	panel.add(showmax);
        	showmax.setText("30");
        	showmax.setBounds(235, 45, 27, 24);
        	showmax.setFont(new java.awt.Font("������ͤ��ϸ�ڼ���",1,15));
        	showmax.setForeground(Color.black);
        }
        {
        	startserver = new JButton();
        	panel.add(startserver);
        	startserver.setText("\u542f\u52a8\u670d\u52a1\u5668");
        	startserver.setBounds(451, 45, 119, 38);
        	startserver.setFont(new java.awt.Font("��Բ",1,18));
        	startserver.setForeground(Color.white);
        	startserver.setBackground(new Color(33, 184,229));
        	startserver.setBorder(null);
        	//���������� ������====================================
        	startserver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					startserver.setEnabled(false);
					closeserver.setEnabled(true);
					System.out.println("����������");
					//�����������
					int num = Integer.parseInt(showmax.getText());
					//�˿�
					int port = Integer.parseInt(showdkh.getText());
					//startserver����
					startServer(num, port);
					JOptionPane.showMessageDialog(null, "������������", "��ʾ",JOptionPane.WARNING_MESSAGE);
				}
			});
        	//======================================================
        }
        {
        	closeserver = new JButton();
        	panel.add(closeserver);
        	closeserver.setText("\u5173\u95ed\u670d\u52a1\u5668");
        	closeserver.setBounds(596, 45, 119, 38);
        	closeserver.setFont(new java.awt.Font("��Բ",1,18));
        	closeserver.setForeground(Color.white);
        	closeserver.setBackground(new Color(33, 184,229));
        	closeserver.setBorder(null);
        	closeserver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//System.exit(0);
					sthread.stop();
					
				}
			});
        }
        {
        	user = new JLabel();
        	panel.add(user);
        	user.setText("\u5728\u7ebf\u7528\u6237");
        	user.setBounds(12, 93, 115, 18);
        	user.setFont(new java.awt.Font("��Բ",1,18));
        	user.setForeground(Color.white);
        }
        {
        	showuser = new JTextArea();
        	panel.add(showuser);
        	showuser.setBounds(12, 125, 136, 272);
        	showuser.setFont(new java.awt.Font("��Բ",1,15));
        	showuser.setEditable(false);
        }
        {
        	send = new JButton();
        	panel.add(send);
        	send.setText("\u53d1\u9001");
        	send.setBounds(633, 364, 82, 34);
        	send.setFont(new java.awt.Font("��Բ",1,18));
        	send.setForeground(Color.white);
        	send.setBackground(new Color(33, 184,229));
        	send.setBorder(null);
        	//=======������Ϣ��ť===============================
			send.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String s1=smess.getText();
					if("".equals(s1)){//��Ϊ�յ��Ǹ�����equals
						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
						smess.requestFocus();
					}else{
						send(s1);
						//=========================
						Date d=new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String t=sdf.format(d);
						//����д��ѭ������
						showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+s1+"\n");//������������Ϣ��ʾ������������
						//================
						smess.setText(null);// ���ͺ�����ı�������
					}
				}
			});
            //===================================================
        }
        {
        	smess = new JTextField();
        	panel.add(smess);
        	smess.setBounds(172, 364, 449, 33);
        	smess.setFont(new java.awt.Font("��Բ",0,15));
            smess.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){  
                    	String s1=smess.getText();
    					if("".equals(s1)){//��Ϊ�յ��Ǹ�����equals
    						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
    						smess.requestFocus();
    					}else{
    						send(s1);
    						//=========================
    						Date d=new Date();
    						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    						String t=sdf.format(d);
    						//����д��ѭ������
    						showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+s1+"\n");//������������Ϣ��ʾ������������
    						//================
    						smess.setText(null);// ���ͺ�����ı�������
    					}  
                    }  
                }  
            });  
        }
        {
        	showmess = new JTextArea();
        	panel.add(showmess);
        	showmess.setBounds(172, 125, 543, 211);
        	showmess.setFont(new java.awt.Font("��Բ",0,15));
        	showmess.setEditable(false);//������ʾ���򲻿�����
        	showmess.setLineWrap(true);//�����Զ����У�֮������Ҫ����ˮƽ������
        	//���ù�����
        	JScrollPane jsp=new JScrollPane(showmess);
        	//���þ��δ�С.��������Ϊ(�������ϽǺ�����x,�������Ͻ�������y�����γ��ȣ����ο��)
        	jsp.setBounds(172, 125, 543, 211);
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	panel.add(jsp);
        }
        //==============================================================
    }
    public static void main(String[] args) {
        //����
        new server();
    }
    //
  	public void startServer(int num, int port)
  	{
  		try {
  			server = new ServerSocket(port);
  			sthread = new ServerThread(server,this.getShowmess(),this.getShowuser());//��������ʱ����һ���߳���ר�Ŵ�������
  			sthread.start();
  		} catch (IOException e) {
  			JOptionPane.showMessageDialog(null, "�����������쳣", "��ʾ", JOptionPane.WARNING_MESSAGE);
  		}
  	}
  	//������������Ϣ�ķ���
  	public void send(String mess) 
	{
		List<Socket> list = sthread.getClients();//Ҫ�Ľ����룡����
		for (int i=0;i<list.size();i++) {
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(list.get(i).getOutputStream()));
				bw.write(mess + "#"+"\n");//д����Ϣ
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
  	//=================================
  	//���ӹ����� �������Ͽ� �ͻ��˶Ͽ�
}

