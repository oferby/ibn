package bayesian;

import com.huawei.ibn.knowledgebase.r.RAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
public class TestSimpleBayesianNetwork {


    @Test
    public void testSimpleNet() throws IOException {

        RAdapter adapter = new RAdapter();

        adapter.eval("load(\"c:\\\\temp\\\\r.dat\")");
        String[] rexp = adapter.evalStringArray("w[[2]]$nodes$Bit.5$mb");

        assert adapter != null;

    }

}
