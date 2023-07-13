package RabbitMQ.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MessagingConfig {

    private String QUEUE;

    public Queue queue(){
        return new Queue(QUEUE);
    }

    public TopicExchange exchange() {
        return new TopicExchange("rich_notch");
    }
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("topNotch_routingeKey");
    }
}
