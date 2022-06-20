package com.remotedesktop.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SocketServer {
    @Resource
    private SocketInitializer socketInitializer;

    @Getter
    private ServerBootstrap serverBootstrap;
    /**
     * netty服务监听端口
     */
    @Value("${com.remotePCClient.netty.port:8088}")
    private int port;
    /**
     * 主线程组数量
     */
    @Value("${com.remotePCClient.netty.bossThread:1}")
    private int bossThread;

    /**
     * 启动netty服务器
     */

    public static Map<String, Channel> clients = new HashMap<>();
    public static Map<String, Boolean> clientState = new HashMap<>();

    public void start() {
        this.init();
        this.serverBootstrap.bind(this.port);
        log.info("Netty started on port: {} (TCP) with boss thread {}", this.port, this.bossThread);
    }

    /**
     * 初始化netty配置
     */
    private void init() {

        // 创建两个线程组，bossGroup为接收请求的线程组，一般1-2个就行
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(this.bossThread);
        // 实际工作的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
        this.serverBootstrap.group(bossGroup, workerGroup) // 两个线程组加入进来
                .channel(NioServerSocketChannel.class)  // 配置为nio类型
                .childHandler(this.socketInitializer)// 加入自己的初始化器
                .option(ChannelOption.SO_BACKLOG, 1024); // 设置tcp缓冲区

    }
}
