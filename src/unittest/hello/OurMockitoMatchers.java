package hello;

import static org.mockito.Matchers.argThat;

public class OurMockitoMatchers {

	public static String hasLength(int length) {
		return argThat(OurHamcrestMatchers.hasLength(length));
	}
}
