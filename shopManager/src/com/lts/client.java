package com.lts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import com.lts.ClinetRecive;



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
public class client{
    //�½����岢���ô������
    public JFrame frame = new JFrame("����ͨѶ");
    public JPanel panel = null;
    private JButton stop;
    private JTextArea showaddress;
    private JTextArea name;
	private JButton link;
    private JLabel address;
    private JTextArea showport;
    private JLabel dkh;
    private JTextArea showmess;
	private JTextArea sendmess;
    private JButton fsbutton;
    BufferedWriter bw;
    ClinetRecive recive;
    Socket socket;
    //���췽��
    public client()
    {
        //���ع��췽��ʱ��ʼ��
        init();
        stop.setEnabled(false);
    }
    //get��set����
    public JTextArea getShowmess() {
 		return showmess;
 	}
 	public void setShowmess(JTextArea showmess) {
 		this.showmess = showmess;
 	}
 	public JTextArea getName() {
		return name;
	}
	public void setName(JTextArea name) {
		this.name = name;
	}
    //
    @SuppressWarnings("serial")
    public void init(){
        //�������ֹ�����
		panel = new JPanel() {
			// ��ȡ�����������
			public void paintComponent(Graphics g) 
			{
				// �趨����ͼ·����ͼƬ��·��Ҫ���������Լ������·��Ҳ���ԣ��������þ���·����
				String imagePath = "src\\5.jpg";
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
        //����������Ϊ����
        frame.setVisible(true);
        frame.setPreferredSize(new java.awt.Dimension(510, 639));
        //���������С
        frame.pack();
        frame.setSize(510, 639);
        //====================================================
        {
        	fsbutton = new JButton();
        	panel.add(fsbutton);
        	fsbutton.setText("\u53d1\u9001");
        	fsbutton.setBounds(289, 512, 83, 33);
        	fsbutton.setFont(new java.awt.Font("��Բ",1,18));
        	fsbutton.setForeground(Color.white);
        	fsbutton.setBackground(new Color(33, 184,229));
        	fsbutton.setBorder(null);
        	fsbutton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String s=sendmess.getText();//��Ϣ����
					String s2=name.getText();//�û���
					if("".equals(s)){//��Ϊ�յ��Ǹ�����equals
						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
						sendmess.requestFocus();//��ȡ����
					}else{
						sendmess(s,s2);
						sendmess.setText(null);// ���ͺ�����ı�������
					}
				}
			});
        }
        {
        	sendmess = new JTextArea();
        	panel.add(sendmess);
        	sendmess.setBounds(28, 409, 344, 91);
        	sendmess.setFont(new java.awt.Font("��Բ",0,15));
        	 //���س�������Ϣ  
            sendmess.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){  
                    	String s=sendmess.getText();//
    					String s2=name.getText();
    					if("".equals(s)){//��Ϊ�յ��Ǹ�����equals
    						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
    						sendmess.requestFocus();//��ȡ����
    					}else{
    						sendmess(s,s2);
    						sendmess.setText(null);// ���ͺ�����ı�������
    					}
                    }  
                }  
            });  
        }
        {
        	showmess = new JTextArea();
        	panel.add(showmess);
        	showmess.setBounds(28, 68, 344, 295);
        	showmess.setFont(new java.awt.Font("��Բ",0,15));
        	showmess.setEditable(false);//�����ı����򲻿ɱ���
        	showmess.setLineWrap(true);//�����Զ����У�֮������Ҫ����ˮƽ������
        	//���ù�����
        	JScrollPane jsp=new JScrollPane(showmess);
        	//���þ��δ�С.��������Ϊ(�������ϽǺ�����x,�������Ͻ�������y�����γ��ȣ����ο��)
        	jsp.setBounds(28, 68, 344, 295);
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	/*
        	//�ֱ�����ˮƽ�ʹ�ֱ�������Զ����� 
        	jsp.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
        	  
        	//�ֱ�����ˮƽ�ʹ�ֱ���������ǳ���   
        	jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);   
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
        	  
        	//�ֱ�����ˮƽ�ʹ�ֱ��������������  
        	jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);   
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        	*/
        	panel.add(jsp);
        }
        {
        	dkh = new JLabel();
        	panel.add(dkh);
        	dkh.setText("\u7aef\u53e3\u53f7");
        	dkh.setBounds(404, 74, 62, 21);
        	dkh.setFont(new java.awt.Font("��Բ",1,18));
        	dkh.setForeground(Color.white);
        }
        {
        	showport = new JTextArea();
        	panel.add(showport);
        	showport.setText("6666");
        	showport.setBounds(404, 101, 54, 23);
        	showport.setFont(new java.awt.Font("������ͤ��ϸ�ڼ���",1,15));
        	showport.setForeground(Color.black);
        }
        {
        	address = new JLabel();
        	panel.add(address);
        	address.setText("\u5730\u5740");
        	address.setBounds(413, 130, 45, 24);
        	address.setFont(new java.awt.Font("��Բ",1,18));
        	address.setForeground(Color.white);
        }
        {
        	showaddress = new JTextArea();
        	panel.add(showaddress);
        	showaddress.setText("127.0.0.1");
        	showaddress.setBounds(392, 160, 83, 21);
        	showaddress.setFont(new java.awt.Font("������ͤ��ϸ�ڼ���",1,15));
        	showaddress.setForeground(Color.black);
        }
        {
        	link = new JButton();
        	panel.add(link);
        	link.setText("\u8fde\u63a5");
        	link.setBounds(392, 193, 83, 33);
        	link.setFont(new java.awt.Font("��Բ",1,18));
        	link.setForeground(Color.white);
        	link.setBackground(new Color(33, 184,229));
        	link.setBorder(null);
        	//-----------------------------���������ڲ���ʵ��-------------------==========================
			link.addActionListener(new ActionListener() {//���Ӱ�ť
				@Override
				public void actionPerformed(ActionEvent arg0) {
					link.setEnabled(false);
					getLink();//���ӵ�������
				}
			});
            //======================================================================================
        }
        {
        	name = new JTextArea();
        	panel.add(name);
        	panel.add(getStop());
        	name.setText("\u9a6c\u4e91");
        	name.setBounds(28, 25, 64, 24);
        	name.setFont(new java.awt.Font("������ͤ��ϸ�ڼ���",1,15));
        	name.setForeground(Color.black);
        }
        //====================================================================
        
    }
    public static void main(String[] args) {
        //����
        new client();
    }
  //===getLink����===============================================================================
  	public BufferedWriter getLink() {// ���ӵ�����������������������Լ�������
  		try {
  			socket = new Socket("127.0.0.1", 6666);
  			stop.setEnabled(true);
  			OutputStream os = socket.getOutputStream();
  			bw = new BufferedWriter(new OutputStreamWriter(os));
  			String mess = name.getText()+"\n";
  			bw.write(mess);
  			bw.flush();
  			// �����̴߳�����Ϣ�Ľ���
  			//���ӳɹ�֮���һֱ���ſͻ��˷���Ϣ�������ͻ��˵��Ž�����Ϣ
  			recive=new ClinetRecive(socket,this.getShowmess());
  			recive.start();
  		} catch (UnknownHostException e) {
  			JOptionPane.showMessageDialog(null, "��ַ����", "������ʾ", JOptionPane.WARNING_MESSAGE);
  		} catch (IOException e) {
  			JOptionPane.showMessageDialog(null, "������δ����", "������ʾ", JOptionPane.WARNING_MESSAGE);
  			link.setEnabled(true);
  		}
  		return bw;
  	}
  //==�ͻ��˷�����Ϣ����==========================================================
  	public void sendmess(String s,String s2){//s2���û���
  		try {
  			bw.write(s2+"//"+s+"\n"); //bw�Ѿ�д��      **��ô���ĳһ����Ϣ��ĳĳ���͵�ȱ��**
  			bw.flush();                      //���͵�ʱ���ȡ�ı���������ְ�����һ���͹�ȥ
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		System.out.println("��Ϣ�Ѵӿͻ��˷���,ת�����������������ͻ���");
  	}
  
  private JButton getStop() {
	  if(stop == null) {
		  stop = new JButton();
		  stop.setText("\u65ad\u5f00");
		  stop.setBounds(392, 237,83, 33);
          panel.add(stop);
          stop.setFont(new java.awt.Font("��Բ",1,18));
          stop.setForeground(Color.white);
          stop.setBackground(new Color(33, 184,229));
          stop.setBorder(null);
          stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//�ȹرտͻ��˺��ٹرշ�����
				//�ر�������������̣߳�socket
				link.setEnabled(true);
				stop.setEnabled(false);
				String s2=name.getText();//��ȡ�û���
				System.out.println(s2);
				disconnect(s2);
				//System.exit(0);
				//recive.stop();
//				try {
//					bw.write("$$$"+"\n");
//					bw.flush();
//					//bw.close();//�ر������
//				    //socket.close();//�ر�socket
//				} catch (IOException e) {
//					System.out.println("�ر�ʧ��");
//					e.printStackTrace();
//				}
				//=============
			}
		});
	  }
	  return stop;
  }
  //==========================================>>>>>>>>>>>>>>>>>>>>>>>>
  public void disconnect(String s2){
	  try {
		bw.write(s2+"//"+"���Ͽ����ӡ�"+"\n");
		bw.flush();
		//Ҫ�ر��û��˵������
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  //======================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
