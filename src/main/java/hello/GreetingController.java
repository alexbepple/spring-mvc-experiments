package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @RequestMapping("/greet")
    public String greet(
		@RequestParam(value = "name", required = false, defaultValue = "World") String name,
		Model model)
    {
		model.addAttribute("greeting", greetingService.greet(name));
        return "greeting";
    }
}
