package study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import study.service.KafkaProducerService;

@RestController
public class EventController {

	@Autowired
	private KafkaProducerService kafkaMessagePublisherService;
		
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message){
		
		try {
			kafkaMessagePublisherService.sendMessageToTopic(message);
			return ResponseEntity.ok("Message published successully......");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@GetMapping("/publishBulk/{message}")
	public ResponseEntity<?> publishBulkMessage(@PathVariable String message){
		
		try {
			
			for(int i=1; i<=10000; i++) {
				kafkaMessagePublisherService.sendBulkMessage(message+" : "+i);
			}
			kafkaMessagePublisherService.sendMessageToTopic(message);
			return ResponseEntity.ok("Message published successully......");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@GetMapping("/createTopic/{message}")
	public ResponseEntity<?> createTopicProgramatically(@PathVariable String message){
		
		try {
			kafkaMessagePublisherService.createTopicProgramatically(message);
			return ResponseEntity.ok("Message published successully......");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
}
