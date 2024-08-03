package study.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
	
	@Bean
	NewTopic createTopic() {
		return new NewTopic("TopicCreatedProgramatically", 5, (short) 1);
	}

}
