package nlu;

import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.nlu.rasa.RasaNLUEngine;
import openstack.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = RasaTestConfig.class)
public class TestRasaNlu {

    @Autowired
    private RasaNLUEngine rasaNLUEngine;

    @Test
    public void testScore() {

        assert rasaNLUEngine != null;

        IntentMessage intent = rasaNLUEngine.getIntent("create bms");

        assert intent !=null;



    }


}
