package cz.peinlich.exam.controllers.other;

import cz.peinlich.exam.entities.other.SimpleData;
import cz.peinlich.exam.repositories.other.SimpleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    SimpleDataRepository repository;

    @RequestMapping(value = "/simpleData/{dataId}", method = RequestMethod.GET)
    @ResponseBody
    SimpleData getSimpleData(@PathVariable("dataId") Long dataId) {
        return repository.findOne(dataId);
    }

    @RequestMapping(value = "/simpleData", method = RequestMethod.POST)
    @ResponseBody
    SimpleData getSimpleData(@RequestBody SimpleData data) {
        return repository.save(data);
    }
}
