package com.philips.receiver.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.philips.receiver.models.PublishedS3Data;
import com.philips.receiver.services.S3Utility;

@Component
public class Consumer {
	private S3Utility s3Utility;
	
	@RabbitListener(queues = "s3Queue")
    public void consumeMessageFromQueue(PublishedS3Data publishedS3Data) {
       s3Utility = new S3Utility();
       s3Utility.Authorize(publishedS3Data.getBucketName());
       s3Utility.getS3ObjectData(publishedS3Data.getBucketName(), publishedS3Data.getUuid());
	}
}
