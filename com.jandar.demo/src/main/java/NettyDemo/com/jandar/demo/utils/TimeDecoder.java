package NettyDemo.com.jandar.demo.utils;

import java.util.List;

import NettyDemo.com.jandar.demo.entity.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
         List<Object>out) throws Exception {

	     if (in.readableBytes()< 4) {
	        return;
	     }

	      out.add(new UnixTime(in.readInt()));
	   }

}
