package openapi.bank.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class AccountController {

    @RequestMapping("/account/inquiry")
    public String inquiry(ModelMap model) {
        return "inquiry";
    }

    @RequestMapping("/account/transfer")
    public String transfer(ModelMap model) {
        return "transfer";
    }
}
