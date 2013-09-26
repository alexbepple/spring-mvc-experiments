package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GreetingControllerTest {
    @InjectMocks
    private GreetingController greetingController = new GreetingController();

    @Mock
    private DictatorDetector detector;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void refusesToGreetDictators() throws Exception {
        when(detector.isDictator(anyString())).thenReturn(true);

        ExtendedModelMap model = new ExtendedModelMap();
        greetingController.greeting("foo", model);
        assertTrue(model.containsKey("errorKey"));
    }

}
