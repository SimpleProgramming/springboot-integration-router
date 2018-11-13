package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;

import com.springboot.model.Address;
import com.springboot.model.Student;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

	@Bean
	public MessageChannel recieverChannel() {
		return new DirectChannel();
	}

	// PayloadTypeRouter Example
	@ServiceActivator(inputChannel = "router.channel")
	@Bean
	public PayloadTypeRouter payloadRouter() {
		PayloadTypeRouter router = new PayloadTypeRouter();
		router.setChannelMapping(Student.class.getName(), "student.channel");
		router.setChannelMapping(Address.class.getName(), "address.channel");
		return router;
	}

	// Header Value Router
	/*@ServiceActivator(inputChannel = "header.payload.router.channel")
	@Bean
	public HeaderValueRouter headerRouter() {
		HeaderValueRouter router = new HeaderValueRouter("testHeader");
		router.setChannelMapping("student", "student.channel");
		router.setChannelMapping("address", "address.channel");
		return router;
	}*/

	// Recipient List Router
	/*@ServiceActivator(inputChannel = "recipient.payload.router.channel")
	@Bean
	public RecipientListRouter recipientListRouter() {
		RecipientListRouter router = new RecipientListRouter();
		router.setSendTimeout(1_234L);
		router.setIgnoreSendFailures(true);
		router.setApplySequence(true);
		router.addRecipient("student.channel");
		router.addRecipient("address.channel");
		router.addRecipient("channel3");
		return router;
	}*/
}