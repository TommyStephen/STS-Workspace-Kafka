package study.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
	
	@Autowired
	private KafkaTemplate<String, String> template;
	
	public void sendMessageToTopic(String message) {
		
		CompletableFuture<SendResult<String, String>> future = template.send("FirstTopic", message);
		future.whenComplete((result, ex)->{
			if(ex==null) {
				System.out.println("Sent Message = ["+message
						+ "] with offset = [" +result.getRecordMetadata().offset()+"]");		
			}else {
				System.out.println("Unable to Sent Message = ["+message
						+ "] due to = [" +ex.getMessage()+"]");
			}
		});
	}
	
	public void sendBulkMessage(String message) {
		CompletableFuture<SendResult<String, String>> future = template.send("SecondTopic", "KeyTwo", message);
		future.whenComplete((result, ex)->{
			if(ex==null) {
				System.out.println("Sent Message = ["+message
						+ "] with offset = [" +result.getRecordMetadata().offset()+"]");		
			}else {
				System.out.println("Unable to Sent Message = ["+message
						+ "] due to = [" +ex.getMessage()+"]");
			}
		});
	}
	
	public void createTopicProgramatically(String message) {
		CompletableFuture<SendResult<String, String>> future = template.send("TopicCreatedProgramatically",  message);
		future.whenComplete((result, ex)->{
			if(ex==null) {
				System.out.println("Sent Message = ["+message
						+ "] with offset = [" +result.getRecordMetadata().offset()+"]");		
			}else {
				System.out.println("Unable to Sent Message = ["+message
						+ "] due to = [" +ex.getMessage()+"]");
			}
		});
	}
}
