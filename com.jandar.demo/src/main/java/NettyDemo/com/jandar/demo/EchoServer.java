package NettyDemo.com.jandar.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

	private int port;//端口 号
	
	public EchoServer(int port){
		this.port = port;
	}
	
	public void run() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() { // (4)

                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline().addLast(new EchoServerHandler());

                }
           }) 
           .option(ChannelOption.SO_BACKLOG, 128)          // (5)
           .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
			
			// Bind andstart to accept incoming connections.
			
			ChannelFuture f = bootstrap.bind(port).sync(); // (7)
			
			// Wait untilthe server socket is closed.

	        // In this example,this does not happen, but you can do that to gracefully

	        // shut downyour server.
			
			f.channel().closeFuture().sync();
			
		}finally{
			workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) throws Exception {

	       int port;

	       if (args.length > 0) {

	           port = Integer.parseInt(args[0]);

	       } else {

	           port = 8089;

	       }

	       new EchoServer(port).run();

	    }
}
