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
    //新建窗体并设置窗体标题
    public JFrame frame = new JFrame("联系卖家");
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
    //get和set方法
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
    //构造方法
    public server()
    {
        //加载构造方法时初始化
        init();
    }
    //
    @SuppressWarnings("serial")
    public void init(){
        //创建布局管理器
		panel = new JPanel() {
			// 获取窗体所需组件
			public void paintComponent(Graphics g) {
				// 设定背景图路径（图片的路径要更换成你自己的相对路径也可以，我这里用绝对路径）
				String imagePath ="src/4.jpg";
				// 创建图片类
				ImageIcon icon = new ImageIcon(imagePath);
				// 创建图片类获取图片
				Image image = icon.getImage();
				// 绘画
				g.drawImage(image, 0, 0, icon.getIconWidth(),icon.getIconHeight(), icon.getImageObserver());
				// 获取图片宽高作为窗体宽高
				frame.setSize(icon.getIconWidth(), icon.getIconHeight());
			}
		};
        //把布局设定进窗体中
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        frame.setVisible(true);
        //将窗体设置为可视
        frame.setPreferredSize(new java.awt.Dimension(794, 482));
        //调整窗体大小
        frame.pack();
        frame.setSize(794, 482);
        //==================================================================
        {
        	dkh = new JLabel();
        	panel.add(dkh);
        	dkh.setText("\u7aef\u53e3\u53f7");
        	dkh.setBounds(12, 45, 58, 26);
        	dkh.setFont(new java.awt.Font("幼圆",1,18));
        	dkh.setForeground(Color.white);
        }
        {
        	showdkh = new JTextArea();
        	panel.add(showdkh);
        	showdkh.setText("6666");
        	showdkh.setBounds(80, 45, 54, 23);
        	showdkh.setFont(new java.awt.Font("方正兰亭超细黑简体",1,15));
        	showdkh.setForeground(Color.black);
        }
        {
        	maxnum = new JLabel();
        	panel.add(maxnum);
        	maxnum.setText("\u6700\u5927\u4eba\u6570");
        	maxnum.setBounds(146, 49, 77, 16);
        	maxnum.setFont(new java.awt.Font("幼圆",1,18));
        	maxnum.setForeground(Color.white);
        }
        {
        	showmax = new JTextArea();
        	panel.add(showmax);
        	showmax.setText("30");
        	showmax.setBounds(235, 45, 27, 24);
        	showmax.setFont(new java.awt.Font("方正兰亭超细黑简体",1,15));
        	showmax.setForeground(Color.black);
        }
        {
        	startserver = new JButton();
        	panel.add(startserver);
        	startserver.setText("\u542f\u52a8\u670d\u52a1\u5668");
        	startserver.setBounds(451, 45, 119, 38);
        	startserver.setFont(new java.awt.Font("幼圆",1,18));
        	startserver.setForeground(Color.white);
        	startserver.setBackground(new Color(33, 184,229));
        	startserver.setBorder(null);
        	//启动服务器 监听器====================================
        	startserver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					startserver.setEnabled(false);
					closeserver.setEnabled(true);
					System.out.println("启动服务器");
					//最大连接人数
					int num = Integer.parseInt(showmax.getText());
					//端口
					int port = Integer.parseInt(showdkh.getText());
					//startserver方法
					startServer(num, port);
					JOptionPane.showMessageDialog(null, "服务器已启动", "提示",JOptionPane.WARNING_MESSAGE);
				}
			});
        	//======================================================
        }
        {
        	closeserver = new JButton();
        	panel.add(closeserver);
        	closeserver.setText("\u5173\u95ed\u670d\u52a1\u5668");
        	closeserver.setBounds(596, 45, 119, 38);
        	closeserver.setFont(new java.awt.Font("幼圆",1,18));
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
        	user.setFont(new java.awt.Font("幼圆",1,18));
        	user.setForeground(Color.white);
        }
        {
        	showuser = new JTextArea();
        	panel.add(showuser);
        	showuser.setBounds(12, 125, 136, 272);
        	showuser.setFont(new java.awt.Font("幼圆",1,15));
        	showuser.setEditable(false);
        }
        {
        	send = new JButton();
        	panel.add(send);
        	send.setText("\u53d1\u9001");
        	send.setBounds(633, 364, 82, 34);
        	send.setFont(new java.awt.Font("幼圆",1,18));
        	send.setForeground(Color.white);
        	send.setBackground(new Color(33, 184,229));
        	send.setBorder(null);
        	//=======发送信息按钮===============================
			send.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String s1=smess.getText();
					if("".equals(s1)){//不为空的那个来点equals
						JOptionPane.showMessageDialog(null, "输入内容不能为空", "友情提示", JOptionPane.WARNING_MESSAGE);
						smess.requestFocus();
					}else{
						send(s1);
						//=========================
						Date d=new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
						String t=sdf.format(d);
						//不能写在循环里面
						showmess.append("系统消息"+" "+t+"\n"+" "+s1+"\n");//服务器发送消息显示到服务器界面
						//================
						smess.setText(null);// 发送后清空文本框内容
					}
				}
			});
            //===================================================
        }
        {
        	smess = new JTextField();
        	panel.add(smess);
        	smess.setBounds(172, 364, 449, 33);
        	smess.setFont(new java.awt.Font("幼圆",0,15));
            smess.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){  
                    	String s1=smess.getText();
    					if("".equals(s1)){//不为空的那个来点equals
    						JOptionPane.showMessageDialog(null, "输入内容不能为空", "友情提示", JOptionPane.WARNING_MESSAGE);
    						smess.requestFocus();
    					}else{
    						send(s1);
    						//=========================
    						Date d=new Date();
    						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    						String t=sdf.format(d);
    						//不能写在循环里面
    						showmess.append("系统消息"+" "+t+"\n"+" "+s1+"\n");//服务器发送消息显示到服务器界面
    						//================
    						smess.setText(null);// 发送后清空文本框内容
    					}  
                    }  
                }  
            });  
        }
        {
        	showmess = new JTextArea();
        	panel.add(showmess);
        	showmess.setBounds(172, 125, 543, 211);
        	showmess.setFont(new java.awt.Font("幼圆",0,15));
        	showmess.setEditable(false);//设置显示区域不可输入
        	showmess.setLineWrap(true);//设置自动换行，之后则不需要设置水平滚动条
        	//设置滚动条
        	JScrollPane jsp=new JScrollPane(showmess);
        	//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
        	jsp.setBounds(172, 125, 543, 211);
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	panel.add(jsp);
        }
        //==============================================================
    }
    public static void main(String[] args) {
        //调用
        new server();
    }
    //
  	public void startServer(int num, int port)
  	{
  		try {
  			server = new ServerSocket(port);
  			sthread = new ServerThread(server,this.getShowmess(),this.getShowuser());//启动服务时创建一个线程来专门处理连接
  			sthread.start();
  		} catch (IOException e) {
  			JOptionPane.showMessageDialog(null, "启动服务器异常", "提示", JOptionPane.WARNING_MESSAGE);
  		}
  	}
  	//服务器发送消息的方法
  	public void send(String mess) 
	{
		List<Socket> list = sthread.getClients();//要改进代码！！！
		for (int i=0;i<list.size();i++) {
			try {
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(list.get(i).getOutputStream()));
				bw.write(mess + "#"+"\n");//写入消息
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
  	//=================================
  	//增加滚动条 服务器断开 客户端断开
}

