package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GreetingControllerForDictatorTest {

	@InjectMocks
	private GreetingController greetingController;

	@Mock
	private GreetingService __greetingService;
	private final ExtendedModelMap model = new ExtendedModelMap();

	@Mock
	private DictatorDetector dictatorDetector;

	@Mock
	private LogService __logService;

	@Before
	public void setup() {
		initMocks(this);

		when(dictatorDetector.isDictator("diktator")).thenReturn(true);
		greetingController.greet("diktator", model);
	}

	@Test
	public void putsNothingElseInModel() throws Exception {
		assertThat(model.size(), is(1));
	}

	@Test
	public void putsErrorKeyInModel() throws Exception {
		assertThat(model, hasKey("errorKey"));
	}
}
