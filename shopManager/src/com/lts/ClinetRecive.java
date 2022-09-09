package com.lts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class ClinetRecive extends Thread  // �ͻ�����Ϣ�����̣߳�������Ϣ�Ľ���
{
	//����
	Socket socket;
	String mess;
	client client;
	JTextArea showmess;
	//���췽��
	public ClinetRecive(Socket socket,JTextArea showmess)   //Client����㴫��һ��socket�����Է���˵�socket��
	{
		this.socket = socket;
		this.showmess=showmess;
	}
	//run����
	@Override
	public void run() 
	{
		while (true) // ��֪��ʲôʱ����Ҫ������Ϣ������ѭ���ȴ�
		{
//			System.out.println("�ͻ��˽�����Ϣ");
			try {
				//�������� �����
				InputStream is = socket.getInputStream();
				//�ֽ���ת�ַ�������
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				//��ȡ����
				mess=br.readLine();
				//===========================================================
				Date d=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String t=sdf.format(d);
				//============================================================
				//��һ���жϣ��Ƿ��������ǿͻ��˷�����
				if(mess.indexOf("#")!=-1){
					//����Ƿ���������������Ϣ
					String s1[] = mess.split("#");//�������#�ž���#�ָ�
					String message = s1[0];
					showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+message+"\n");//��������-�ͻ��˷������Ľ���-�������������Ľ���         ��ʾ���ͻ��˵��ı�������
				}else{
					//�ж���Ϣ������
					if(mess.indexOf("//")!=-1){
						//����ǿͻ��˷���������Ϣ  �ͺ���"//"
						String s3[]=mess.split("//");
						String name = s3[0];
						String message=s3[1];
						//�ж϶Ͽ�����
						if(message.indexOf("���Ͽ����ӡ�")!=-1){
							showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+name+message+"\n");
							//�ر��û��˵���������socket��clientrecive�߳�
						}else{
							showmess.append(name+" "+t+"\n"+" "+message+"\n");
						}
					}else{//�����ĳĳ���ӳɹ���Ϣ������    **Ҫ��һ���ж�**
						showmess.append("ϵͳ��Ϣ"+" "+t+"\n"+" "+mess+"\n");
					}                                                    
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//����	
}
