package nlu;

import com.huawei.ibn.nlu.model.UserInputRepository;
import com.huawei.ibn.nlu.rasa.RasaNLUEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RasaTestConfig {




    @Bean
    public RasaNLUEngine getRasaNLUEngine() {
        return new RasaNLUEngine();
    }

    @Bean
    public UserInputRepository getUserInputRepository() {
        return new MockUserInputRepository();
    }


}
