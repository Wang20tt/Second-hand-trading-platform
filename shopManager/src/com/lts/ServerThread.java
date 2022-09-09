package com.lts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

public class ServerThread extends Thread 
{
	//属性
	ServerSocket  server;
	JTextArea showmess;
	JTextArea showuser;
	String sendMess;
	List<Socket> clients=new ArrayList<Socket>();
	//get和set方法        **可以不用get和set方法**
	public List<Socket> getClients() {
		return clients;
	}
	public void setClients(List<Socket> clients) {
		this.clients = clients;
	}
	//构造方法
	public ServerThread(ServerSocket  server,JTextArea showmess,JTextArea showuser){
		this.server=server;
		this.showmess=showmess;
		this.showuser=showuser;
	}
	//run方法
	@Override
	public void run() 
	{
		while (true) //需要不断执行,等待客户端的连接
		{ 
			try 
			{
				//客户端连接服务端向服务端发送了一条语句，语句格式为name+@，服务端读取消息内容
				Socket socket = server.accept();//等待客户端连接
				InputStream is = socket.getInputStream();//数据来了 输入流读数据 客户端发来的 -socket转字节流
				BufferedReader br = new BufferedReader(new InputStreamReader(is));//字节流转字符缓冲流
				String s = br.readLine();//定义s接收数据
				sendMess =s+"连接成功";// 重新组装消息，准备数据
				//============================================================
				clients.add(socket);//将所有的连接保存到list里，以便完成群发消息                    **只要客户端上线就加入list集合
				
				//断开时要移除
				
				//======================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//========================================================
				showmess.append(t+"\n"+" "+sendMess+"\n");//将用户已经上线的消息显示到客户端文本区域中
				showuser.append(s+"\n");//显示到在线用户列表中
				//
				ClientThread client=new ClientThread(clients,sendMess,br,this.showmess);//一个客户端创建一个线程来完成消息的群发
				client.start();
			} catch (IOException e) {
				System.out.println("错误");
			}

		}
	}
//结束
}
