
package sg.edu.ntu.taskflowapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ChatClient is Spring AI's interface for communicating
// with an AI chat model.
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskflowApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskflowApiApplication.class, args);
    }

    // Create a ChatClient bean using the Spring AI builder.
    // Spring will manage this object and allow us
    // to inject it into our controller.
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
}
