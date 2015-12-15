package openapi.bank.api.mvc;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
public class AccountApiEndpoint {

    @RequestMapping("/account/inquiry/{accountNumber}")
    public Account inquiry(@PathVariable String accountNumber) {
        return new Account(accountNumber, 100);
    }
}
