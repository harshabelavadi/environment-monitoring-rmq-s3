package com.philips.dataprocessing.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philips.dataprocessing.models.PublishS3Data;
import com.philips.dataprocessing.Constants.StringConstants;


@RestController
@RequestMapping("/api/monitor")
public class Publisher {
	
	@Autowired
    private RabbitTemplate template;

    @PostMapping("/queue/")
    public void publishToQueue(@RequestBody PublishS3Data publishS3Data) {    
    	template.convertAndSend(StringConstants.EXCHANGE.get(), StringConstants.S3ROUTINGKEY.get(), publishS3Data);
    }
}