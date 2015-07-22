package NettyDemo.com.jandar.demo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import NettyDemo.com.jandar.demo.entity.UnixTime;

public class TimeServerHandler extends ChannelHandlerAdapter {
	
	    @Override
	    public void channelActive(ChannelHandlerContext ctx) {
	
	       ChannelFuture f = ctx.writeAndFlush(new UnixTime());
	       f.addListener(ChannelFutureListener.CLOSE);
	
	    }
	
	 
	
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	
	       cause.printStackTrace();
	
	       ctx.close();
	
	    }
	
}

