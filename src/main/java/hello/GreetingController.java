package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Autowired
    private EndearmentService endearment;

    @Autowired
    private DictatorDetector detector;

    @RequestMapping("/greet")
    public String greeting(
        @RequestParam(value = "name", required = false, defaultValue = "World") String name,
        Model model)
    {
        if (detector.isDictator(name))
            model.addAttribute("errorKey", "NO_GREETING_FOR_DICTATORS");
        else
            model.addAttribute("name", endearment.decorate(name));

        return "greeting";
    }
}
