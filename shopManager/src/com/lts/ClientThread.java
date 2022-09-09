package com.lts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

public class ClientThread extends Thread   //处理消息群发的线程  专门处理消息群发
{
	//属性
	List<Socket> clients;//list集合
	String Mess;//消息
	BufferedReader br=null;
	JTextArea showmess;
	//构造方法
	public ClientThread(List<Socket> clients,String Mess,BufferedReader br,JTextArea showmess)
	{
		this.clients=clients;
		this.Mess=Mess;
		this.br=br;
		this.showmess=showmess;
		//=====构造方法只执行一次，初始化=只传送某某连接成功
		try {
			for (int i = 0; i < clients.size(); i++) 
			{ // 循环所有的连接，发送数据  **发送给客户端某某连接成功
				OutputStream os = clients.get(i).getOutputStream(); //发送给ClientRecive类
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(Mess + "\n");
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//============================================
	}
	//run方法
 	@Override
	public void run() 
 	{
		while (true) {//一直在接收消息
			try {
				String s = br.readLine();//按行读取  要加\n
				//System.out.println(s);
				//==========================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//==========================================================
				String s2[]=s.split("//");//定义一个数组，以*将字符串分割，以获得name
				String name = s2[0];//得到用户name
				String message=s2[1];
				//System.out.println(name+message);
				//==========================================================
				//做一个判断，如果消息中有“断开连接”，显示系统消息
				if(message.indexOf("【断开连接】")!=-1){
					showmess.append("系统消息"+" "+t+"\n"+" "+name+message+"\n");
				}else{
					showmess.append(name+" "+t+"\n"+" "+message+"\n");//服务器接收来自客户端的消息 显示到服务器文本区域上
				}
				//服务器接收到后在发给其他客户端
				for (int i = 0; i < clients.size(); i++) {
					OutputStream os = clients.get(i).getOutputStream(); // 发送给ClientRecive类
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
					bw.write(s + "\n");
					bw.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
	}
//==方法结束======
}

