package com.philips.dataprocessing.rabbitmq;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.philips.dataprocessing.Constants.StringConstants;
import com.philips.dataprocessing.interfaces.ILogs;
import com.philips.dataprocessing.models.PublishedMessage;
import com.philips.dataprocessing.services.Logs;
import com.philips.dataprocessing.services.ProcessData;

@Component
public class Consumer {
	
	private static ProcessData processData = new ProcessData();
	private ILogs log = new Logs(StringConstants.LOGSPATH.get());
	
	@RabbitListener(queues = "monitorQueue")
    public void consumeMessageFromQueue(PublishedMessage publishedMessage) {
		log.logToConsole(String.format(StringConstants.CONSUMEDMESSAGE.get(), publishedMessage.getMonitorDataId(), new Date()));
        processData.processFileData(publishedMessage);
	}
}
