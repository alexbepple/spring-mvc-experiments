package hello;

public class OurHamcrestMatchers {

	public static HasLength hasLength(int expectedLength) {
		return new HasLength(expectedLength);
	}
}
