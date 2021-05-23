package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class ProxyBusFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext context) {
        System.out.println("business filter invoked ...");
    }

}
