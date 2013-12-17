package hello;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasLength extends TypeSafeMatcher<String> {

	private final int expectedLength;

	public HasLength(int expectedLength) {
		this.expectedLength = expectedLength;
	}

	@Override
	protected boolean matchesSafely(String item) {
		return item.length() == expectedLength;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(String.format("hasLength(%s)", expectedLength));
	}
}
