package NettyDemo.com.jandar.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import NettyDemo.com.jandar.demo.entity.UnixTime;

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

   @Override
   protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) throws Exception {
      out.writeInt(msg.value());
   }

}