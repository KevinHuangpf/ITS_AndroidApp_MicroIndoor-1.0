package com.kevinhuang.its_app_20;

import android.os.Bundle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketActivity extends Thread {
    Socket socket;

//    public void run(){
//        setContentView(R.layout.activity_main);
//        try {
//            socket = new Socket("10.0.2.2", 12345);//10.0.2.2这个是模拟器的ip，如果是真机，要改成真机的ip，12345是端口号
//            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());//数据输出流
//            dos.writeUTF(edt.getText().toString());
//            DataInputStream dis = new DataInputStream(socket.getInputStream());//数据输入流，获得服务器返回的数据
//            tv.append(dis.readUTF());
//            socket.close();
//            dos.close();
//            dis.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
