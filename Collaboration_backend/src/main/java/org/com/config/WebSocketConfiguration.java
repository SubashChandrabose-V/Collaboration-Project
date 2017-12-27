package org.com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker	//enable broker based stomp messaging- auto decteting annotation @messagemapping
@ComponentScan(basePackages="com.niit")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureClientInboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue/","/topic/"); // server to broker to client
		// queue is for chat message
		//topic is for newely joined user
		registry.setApplicationDestinationPrefixes("/app"); // from client to server
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/portfolio").withSockJS();	//to connect with web socket
		
	}
	
	
}
