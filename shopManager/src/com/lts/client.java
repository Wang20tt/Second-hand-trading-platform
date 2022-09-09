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
    //新建窗体并设置窗体标题
    public JFrame frame = new JFrame("卖家通讯");
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
    //构造方法
    public client()
    {
        //加载构造方法时初始化
        init();
        stop.setEnabled(false);
    }
    //get和set方法
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
        //创建布局管理器
		panel = new JPanel() {
			// 获取窗体所需组件
			public void paintComponent(Graphics g) 
			{
				// 设定背景图路径（图片的路径要更换成你自己的相对路径也可以，我这里用绝对路径）
				String imagePath = "src\\5.jpg";
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
        //将窗体设置为可视
        frame.setVisible(true);
        frame.setPreferredSize(new java.awt.Dimension(510, 639));
        //调整窗体大小
        frame.pack();
        frame.setSize(510, 639);
        //====================================================
        {
        	fsbutton = new JButton();
        	panel.add(fsbutton);
        	fsbutton.setText("\u53d1\u9001");
        	fsbutton.setBounds(289, 512, 83, 33);
        	fsbutton.setFont(new java.awt.Font("幼圆",1,18));
        	fsbutton.setForeground(Color.white);
        	fsbutton.setBackground(new Color(33, 184,229));
        	fsbutton.setBorder(null);
        	fsbutton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String s=sendmess.getText();//消息内容
					String s2=name.getText();//用户名
					if("".equals(s)){//不为空的那个来点equals
						JOptionPane.showMessageDialog(null, "输入内容不能为空", "友情提示", JOptionPane.WARNING_MESSAGE);
						sendmess.requestFocus();//获取焦点
					}else{
						sendmess(s,s2);
						sendmess.setText(null);// 发送后清空文本框内容
					}
				}
			});
        }
        {
        	sendmess = new JTextArea();
        	panel.add(sendmess);
        	sendmess.setBounds(28, 409, 344, 91);
        	sendmess.setFont(new java.awt.Font("幼圆",0,15));
        	 //按回车发送消息  
            sendmess.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){  
                    	String s=sendmess.getText();//
    					String s2=name.getText();
    					if("".equals(s)){//不为空的那个来点equals
    						JOptionPane.showMessageDialog(null, "输入内容不能为空", "友情提示", JOptionPane.WARNING_MESSAGE);
    						sendmess.requestFocus();//获取焦点
    					}else{
    						sendmess(s,s2);
    						sendmess.setText(null);// 发送后清空文本框内容
    					}
                    }  
                }  
            });  
        }
        {
        	showmess = new JTextArea();
        	panel.add(showmess);
        	showmess.setBounds(28, 68, 344, 295);
        	showmess.setFont(new java.awt.Font("幼圆",0,15));
        	showmess.setEditable(false);//设置文本区域不可编译
        	showmess.setLineWrap(true);//设置自动换行，之后则不需要设置水平滚动条
        	//设置滚动条
        	JScrollPane jsp=new JScrollPane(showmess);
        	//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
        	jsp.setBounds(28, 68, 344, 295);
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        	/*
        	//分别设置水平和垂直滚动条自动出现 
        	jsp.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
        	  
        	//分别设置水平和垂直滚动条总是出现   
        	jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);   
        	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);   
        	  
        	//分别设置水平和垂直滚动条总是隐藏  
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
        	dkh.setFont(new java.awt.Font("幼圆",1,18));
        	dkh.setForeground(Color.white);
        }
        {
        	showport = new JTextArea();
        	panel.add(showport);
        	showport.setText("6666");
        	showport.setBounds(404, 101, 54, 23);
        	showport.setFont(new java.awt.Font("方正兰亭超细黑简体",1,15));
        	showport.setForeground(Color.black);
        }
        {
        	address = new JLabel();
        	panel.add(address);
        	address.setText("\u5730\u5740");
        	address.setBounds(413, 130, 45, 24);
        	address.setFont(new java.awt.Font("幼圆",1,18));
        	address.setForeground(Color.white);
        }
        {
        	showaddress = new JTextArea();
        	panel.add(showaddress);
        	showaddress.setText("127.0.0.1");
        	showaddress.setBounds(392, 160, 83, 21);
        	showaddress.setFont(new java.awt.Font("方正兰亭超细黑简体",1,15));
        	showaddress.setForeground(Color.black);
        }
        {
        	link = new JButton();
        	panel.add(link);
        	link.setText("\u8fde\u63a5");
        	link.setBounds(392, 193, 83, 33);
        	link.setFont(new java.awt.Font("幼圆",1,18));
        	link.setForeground(Color.white);
        	link.setBackground(new Color(33, 184,229));
        	link.setBorder(null);
        	//-----------------------------利用匿名内部类实现-------------------==========================
			link.addActionListener(new ActionListener() {//连接按钮
				@Override
				public void actionPerformed(ActionEvent arg0) {
					link.setEnabled(false);
					getLink();//连接到服务器
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
        	name.setFont(new java.awt.Font("方正兰亭超细黑简体",1,15));
        	name.setForeground(Color.black);
        }
        //====================================================================
        
    }
    public static void main(String[] args) {
        //调用
        new client();
    }
  //===getLink方法===============================================================================
  	public BufferedWriter getLink() {// 连接到服务器，并向服务器发送自己的名字
  		try {
  			socket = new Socket("127.0.0.1", 6666);
  			stop.setEnabled(true);
  			OutputStream os = socket.getOutputStream();
  			bw = new BufferedWriter(new OutputStreamWriter(os));
  			String mess = name.getText()+"\n";
  			bw.write(mess);
  			bw.flush();
  			// 开启线程处理消息的接收
  			//连接成功之后就一直等着客户端发消息过来，客户端等着接收消息
  			recive=new ClinetRecive(socket,this.getShowmess());
  			recive.start();
  		} catch (UnknownHostException e) {
  			JOptionPane.showMessageDialog(null, "地址错误", "友情提示", JOptionPane.WARNING_MESSAGE);
  		} catch (IOException e) {
  			JOptionPane.showMessageDialog(null, "服务器未启动", "友情提示", JOptionPane.WARNING_MESSAGE);
  			link.setEnabled(true);
  		}
  		return bw;
  	}
  //==客户端发送消息方法==========================================================
  	public void sendmess(String s,String s2){//s2是用户名
  		try {
  			bw.write(s2+"//"+s+"\n"); //bw已经写好      **怎么解决某一条消息是某某发送的缺陷**
  			bw.flush();                      //发送的时候获取文本区域的名字把名字一起发送过去
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		System.out.println("消息已从客户端发送,转发到服务器及其他客户端");
  	}
  
  private JButton getStop() {
	  if(stop == null) {
		  stop = new JButton();
		  stop.setText("\u65ad\u5f00");
		  stop.setBounds(392, 237,83, 33);
          panel.add(stop);
          stop.setFont(new java.awt.Font("幼圆",1,18));
          stop.setForeground(Color.white);
          stop.setBackground(new Color(33, 184,229));
          stop.setBorder(null);
          stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//先关闭客户端后再关闭服务器
				//关闭输出输入流，线程，socket
				link.setEnabled(true);
				stop.setEnabled(false);
				String s2=name.getText();//获取用户名
				System.out.println(s2);
				disconnect(s2);
				//System.exit(0);
				//recive.stop();
//				try {
//					bw.write("$$$"+"\n");
//					bw.flush();
//					//bw.close();//关闭输出流
//				    //socket.close();//关闭socket
//				} catch (IOException e) {
//					System.out.println("关闭失败");
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
		bw.write(s2+"//"+"【断开连接】"+"\n");
		bw.flush();
		//要关闭用户端的输出流
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
  //======================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
