package com.lts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class ClinetRecive extends Thread  // 客户端消息接收线程，处理消息的接收
{
	//属性
	Socket socket;
	String mess;
	client client;
	JTextArea showmess;
	//构造方法
	public ClinetRecive(Socket socket,JTextArea showmess)   //Client类给你传的一个socket（来自服务端的socket）
	{
		this.socket = socket;
		this.showmess=showmess;
	}
	//run方法
	@Override
	public void run() 
	{
		while (true) // 不知道什么时候需要接收消息，不断循环等待
		{
//			System.out.println("客户端接收消息");
			try {
				//数据来了 请接收
				InputStream is = socket.getInputStream();
				//字节流转字符缓冲流
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				//读取数据
				mess=br.readLine();
				//===========================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//============================================================
				//做一个判断，是服务器还是客户端发来的
				if(mess.indexOf("#")!=-1){
					//如果是服务器发过来的消息
					String s1[] = mess.split("#");//如果含有#号就以#分割
					String message = s1[0];
					showmess.append("系统消息"+" "+t+"\n"+" "+message+"\n");//两个接收-客户端发送来的接收-服务器发送来的接收         显示到客户端的文本区域上
				}else{
					//判断消息的类型
					if(mess.indexOf("//")!=-1){
						//如果是客户端发过来的消息  就含有"//"
						String s3[]=mess.split("//");
						String name = s3[0];
						String message=s3[1];
						//判断断开连接
						if(message.indexOf("【断开连接】")!=-1){
							showmess.append("系统消息"+" "+t+"\n"+" "+name+message+"\n");
							//关闭用户端的输入流、socket、clientrecive线程
						}else{
							showmess.append(name+" "+t+"\n"+" "+message+"\n");
						}
					}else{//如果是某某连接成功消息发过来    **要做一个判断**
						showmess.append("系统消息"+" "+t+"\n"+" "+mess+"\n");
					}                                                    
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//结束	
}
