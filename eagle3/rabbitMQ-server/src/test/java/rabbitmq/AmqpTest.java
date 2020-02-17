package rabbitmq;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AmqpTest {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	@Test
	public void contextLoads() {
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "我是张飞");
		map.put("data", Arrays.asList("qwew"));
		rabbitTemplate.convertAndSend("exchange1", "myQueue", map);
		
		
	}
	
	
}
