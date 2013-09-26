package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GreetingControllerSpringTest {

    @InjectMocks
    private GreetingController greetingController;

    @Mock
    private EndearmentService endearment;

    @Mock
    private DictatorDetector __detector;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(greetingController).build();
    }
    @Test
    public void providesDefaultForName() throws Exception {
        mockMvc.perform(get("/greet"));
        verify(endearment).decorate(notNull(String.class));
    }
}
