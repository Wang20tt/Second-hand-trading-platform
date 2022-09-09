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

public class ClientThread extends Thread   //������ϢȺ�����߳�  ר�Ŵ�����ϢȺ��
{
	//����
	List<Socket> clients;//list����
	String Mess;//��Ϣ
	BufferedReader br=null;
	JTextArea showmess;
	//���췽��
	public ClientThread(List<Socket> clients,String Mess,BufferedReader br,JTextArea showmess)
	{
		this.clients=clients;
		this.Mess=Mess;
		this.br=br;
		this.showmess=showmess;
		//=====���췽��ִֻ��һ�Σ���ʼ��=ֻ����ĳĳ���ӳɹ�
		try {
			for (int i = 0; i < clients.size(); i++) 
			{ // ѭ�����е����ӣ���������  **���͸��ͻ���ĳĳ���ӳɹ�
				OutputStream os = clients.get(i).getOutputStream(); //���͸�ClientRecive��
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(Mess + "\n");
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//============================================
	}
	//run����
 	@Override
	public void run() 
 	{
		while (true) {//һֱ�ڽ�����Ϣ
			try {
				String s = br.readLine();//���ж�ȡ  Ҫ��\n
				//System.out.println(s);
				//==========================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//==========================================================
				String s2[]=s.split("//");//����һ�����飬��*���ַ����ָ�Ի��name
				String name = s2[0];//�õ��û�name
				String message=s2[1];
				//System.out.println(name+message);
				//==========================================================
				//��һ���жϣ������Ϣ���С��Ͽ����ӡ�����ʾϵͳ��Ϣ
				if(message.indexOf("���Ͽ����ӡ�")!=-1){
					showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+name+message+"\n");
				}else{
					showmess.append(name+" "+t+"\n"+" "+message+"\n");//�������������Կͻ��˵���Ϣ ��ʾ���������ı�������
				}
				//���������յ����ڷ��������ͻ���
				for (int i = 0; i < clients.size(); i++) {
					OutputStream os = clients.get(i).getOutputStream(); // ���͸�ClientRecive��
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
//==��������======
}

