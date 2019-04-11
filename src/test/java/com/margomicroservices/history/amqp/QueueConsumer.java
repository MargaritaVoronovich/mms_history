package com.margomicroservices.history.amqp;

import com.margomicroservices.history.containerinitializer.RabbitMqInitializer;
import com.margomicroservices.history.containerinitializer.microservice.DeliveryServiceInitializer;
import com.margomicroservices.history.containerinitializer.microservice.OrderServiceInitializer;
import com.margomicroservices.history.containerinitializer.postgres.PostgresDeliveryInitializer;
import com.margomicroservices.history.containerinitializer.postgres.PostgresHistoryInitializer;
import com.margomicroservices.history.containerinitializer.postgres.PostgresOrderInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@ContextConfiguration(initializers = {
		RabbitMqInitializer.class,
		PostgresOrderInitializer.class,
		PostgresDeliveryInitializer.class,
		PostgresHistoryInitializer.class,
		OrderServiceInitializer.class,
		DeliveryServiceInitializer.class
})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueueConsumer {

	@Test
	public void testReceiveMessage() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
	}

}
