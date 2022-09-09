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
	//����
	ServerSocket  server;
	JTextArea showmess;
	JTextArea showuser;
	String sendMess;
	List<Socket> clients=new ArrayList<Socket>();
	//get��set����        **���Բ���get��set����**
	public List<Socket> getClients() {
		return clients;
	}
	public void setClients(List<Socket> clients) {
		this.clients = clients;
	}
	//���췽��
	public ServerThread(ServerSocket  server,JTextArea showmess,JTextArea showuser){
		this.server=server;
		this.showmess=showmess;
		this.showuser=showuser;
	}
	//run����
	@Override
	public void run() 
	{
		while (true) //��Ҫ����ִ��,�ȴ��ͻ��˵�����
		{ 
			try 
			{
				//�ͻ������ӷ���������˷�����һ����䣬����ʽΪname+@������˶�ȡ��Ϣ����
				Socket socket = server.accept();//�ȴ��ͻ�������
				InputStream is = socket.getInputStream();//�������� ������������ �ͻ��˷����� -socketת�ֽ���
				BufferedReader br = new BufferedReader(new InputStreamReader(is));//�ֽ���ת�ַ�������
				String s = br.readLine();//����s��������
				sendMess =s+"���ӳɹ�";// ������װ��Ϣ��׼������
				//============================================================
				clients.add(socket);//�����е����ӱ��浽list��Ա����Ⱥ����Ϣ                    **ֻҪ�ͻ������߾ͼ���list����
				
				//�Ͽ�ʱҪ�Ƴ�
				
				//======================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//========================================================
				showmess.append(t+"\n"+" "+sendMess+"\n");//���û��Ѿ����ߵ���Ϣ��ʾ���ͻ����ı�������
				showuser.append(s+"\n");//��ʾ�������û��б���
				//
				ClientThread client=new ClientThread(clients,sendMess,br,this.showmess);//һ���ͻ��˴���һ���߳��������Ϣ��Ⱥ��
				client.start();
			} catch (IOException e) {
				System.out.println("����");
			}

		}
	}
//����
}
