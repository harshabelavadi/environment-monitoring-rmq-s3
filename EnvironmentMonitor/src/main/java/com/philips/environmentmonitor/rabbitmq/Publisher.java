package com.philips.environmentmonitor.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philips.environmentmonitor.Constants.StringConstants;
import com.philips.environmentmonitor.models.PublishMessage;

@RestController
@RequestMapping("/api/monitor")
public class Publisher {
	
	@Autowired
    private RabbitTemplate template;

    @PostMapping("/queue/")
    public void publishToQueue(@RequestBody PublishMessage publishMessage) {    
    	template.convertAndSend(StringConstants.EXCHANGE.get(), StringConstants.ROUTINGKEY.get(), publishMessage);
    }
}
