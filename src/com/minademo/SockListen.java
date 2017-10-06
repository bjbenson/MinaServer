package com.minademo;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by benson on 17-1-24.
 */
public class SockListen {

    private IoAcceptor mAcceptor = null;
    private String          mServerIP;
    private int             mServerPort;

    public SockListen() {
        mServerIP ="127.0.0.1";
        mServerPort =10001;
    }

    public boolean startListen() {

        try {
            mAcceptor = new NioSocketAcceptor();
            //添加日志记录
            mAcceptor.getFilterChain().addLast("logger",new LoggingFilter());
            //添加编码解码器
           // mAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),)));
            //添加处理器(用于接收数据后处理处理数据逻辑)

            //新加的一行

            mAcceptor.setHandler( new SockHandler() );
            //设置读取数据缓存单位byte
            mAcceptor.getSessionConfig().setReadBufferSize(2048 );
            //设置多长时间后接收器开始空闲
            mAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10 );
            //绑定某个端口，作为数据入口
            mAcceptor.bind(new InetSocketAddress(mServerPort));

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void closeListen() {

    }
}
