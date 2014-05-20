package cz.peinlich.exam.controllers.other;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller is here to test the infrastructure. I do not know what controllers I will need in exam.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 7:18
 */
@Controller
public class SimpleDataController {
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
