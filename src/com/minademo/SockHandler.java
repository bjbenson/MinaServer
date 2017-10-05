package com.minademo;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import java.util.Date;

/**
 * Created by benson on 17-1-24.
 */
public class SockHandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("服务端与客户端创建连接...");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务端与客户端连接打开...");
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("服务端与客户端连接关闭...");
        super.sessionClosed(session);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("服务端发送信息成功..."+message.toString());
        super.messageSent(session, message);
    }

    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("服务端发送异常..."+cause.getMessage());
        cause.printStackTrace();
    }

    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String strMsg = message.toString();
        System.out.println("服务端接收到的数据为: "+strMsg);
        if (strMsg.trim().equalsIgnoreCase("quit")) {
            session.close();
            return;
        }
        Date date = new Date();
        session.write(date.toString());
    }

    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        System.out.println("服务端进入空闲状态... " + session.getIdleCount(status));
    }
}
