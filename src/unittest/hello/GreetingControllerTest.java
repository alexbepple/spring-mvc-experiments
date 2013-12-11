package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GreetingControllerTest {
    @InjectMocks
    private GreetingController greetingController;

    @Mock
    private GreetingService greetingService;
	private final ExtendedModelMap model = new ExtendedModelMap();

	@Before
    public void setup() {
        initMocks(this);
    }

	@Test
	public void usesGreetingView() throws Exception {
		assertThat(greetingController.greet("foo", model), is("greeting"));
	}

	@Test
    public void passesOnGreeting() throws Exception {
		when(greetingService.greet("foo")).thenReturn("result");

		greetingController.greet("foo", model);
		assertThat(model.get("greeting").toString(), is("result"));
	}

}
