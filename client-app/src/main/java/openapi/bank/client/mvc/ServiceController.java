package openapi.bank.client.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class ServiceController {

    @RequestMapping("/login.app")
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping("/index.app")
    public String index(ModelMap model) {
        return "index";
    }
}
