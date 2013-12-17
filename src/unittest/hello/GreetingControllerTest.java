package hello;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;

import static hello.OurMockitoMatchers.hasLength;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GreetingControllerTest {
    @InjectMocks
    private GreetingController greetingController;

    @Mock
    private GreetingService greetingService;
	private final ExtendedModelMap model = new ExtendedModelMap();

	@Mock
	private DictatorDetector dictatorDetector;

	@Mock
	private LogService logService;

	@Before
    public void setup() {
        initMocks(this);
    }

	@Test
	public void usesGreetingView() throws Exception {
		assertThat(greetingController.greet("foo", model), is("greeting"));
	}

	/**
	 * Demonstrates the use of Mockito for stubs.
	 */
	@Test
    public void passesOnGreetingFromGreetingService() throws Exception {
		when(greetingService.greet("foo")).thenReturn("result");

		greetingController.greet("foo", model);
		assertThat(model.get("greeting").toString(), is("result"));
		assertThat(model.size(), is(1));
	}

	@Test
	public void putsErrorKeyIntoModelWhenItDetectsDictator() throws Exception {
		when(dictatorDetector.isDictator("diktator")).thenReturn(true);

		greetingController.greet("diktator", model);

		assertThat(model, hasKey("errorKey"));
		assertThat(model.size(), is(1));
	}

	/**
	 * The following tests are for an imaginary story:
	 * 		Every time when someone is greeted, please log a random 3-character string.
	 */

	/**
	 * Demonstrates the use of Mockito for mocks.
	 */
	@Test
	public void logsSomething() throws Exception {
		greetingController.greet(null, model);
		verify(logService).logWhatever(anyString());
	}

	/**
	 * Demonstrates the use of an ArgumentCaptor for when Mockito matchers are not enough.
	 */
	@Test
	public void logs3CharacterString() throws Exception {
		greetingController.greet(null, model);

		ArgumentCaptor<String> loggedString = ArgumentCaptor.forClass(String.class);
		verify(logService).logWhatever(loggedString.capture());
		assertThat(loggedString.getValue().length(), is(3));
	}

	/**
	 * In many cases a custom matcher (here: hasLength) is much more expressive
	 * than an ArgumentCaptor.
	 */
	@Test
	public void logs3CharacterString_betterVersion() throws Exception {
		greetingController.greet(null, model);
		verify(logService).logWhatever(hasLength(3));
	}

	/**
	 * Of the three tests,
	 * 	 logsSomething
	 * 	 logs3CharacterString,
	 * 	 logs3CharacterString_betterVersion,
	 * naturally, one would only keep one.
	 * If the story really was to log "a random 3-character string",
	 *   logs3CharacterString_betterVersion
	 * is the one I would keep.
	 */


	/**
	 * Demonstrates a good use case for ArgumentCaptor.
	 */
	@Test
	public void generatesNewStringOnEachRequest() throws Exception {
		greetingController.greet(null, model);

		ArgumentCaptor<String> firstLoggedString = ArgumentCaptor.forClass(String.class);
		verify(logService).logWhatever(firstLoggedString.capture());

		greetingController.greet(null, model);
		verify(logService).logWhatever(argThat(not(is(firstLoggedString.getValue()))));
	}

	/**
	 * Demonstrates testing of error conditions.
	 */
	@Test
	public void proceedsSilentlyWhenLogServiceFails() throws Exception {
		doThrow(new RuntimeException()).when(logService).logWhatever(anyString());
		greetingController.greet("foo", model);
	}

}
