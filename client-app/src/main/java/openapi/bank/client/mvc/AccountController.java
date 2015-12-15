package openapi.bank.client.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

/**
 * @author chanwook
 */
@Controller
public class AccountController {

    @Autowired
    RestOperations template;

    @RequestMapping("/account/inquiry")
    public String inquiry(ModelMap model) {
        Account account =
                template.getForObject("http://localhost:9002/account/inquiry/123-456-789", Account.class);
        model.put("account", account);
        return "inquiry";
    }

    @RequestMapping("/account/transfer")
    public String transfer(ModelMap model) {
        return "transfer";
    }
}
