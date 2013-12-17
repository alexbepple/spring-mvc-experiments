package hello;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

	@Autowired
	private DictatorDetector dictatorDetector;

	@Autowired
	private LogService logService;


    @RequestMapping("/greet")
    public String greet(
		@RequestParam(value = "name", required = false, defaultValue = "World") String name,
		Model model)
    {
		try {
			logService.logWhatever(RandomStringUtils.random(3));
		} catch (Exception e) {
		}

		if (dictatorDetector.isDictator(name)) {
			model.addAttribute("errorKey", "NO_GREETING_FOR_DICTATORS");
		} else {
			model.addAttribute("greeting", greetingService.greet(name));
		}
		return "greeting";
    }
}
