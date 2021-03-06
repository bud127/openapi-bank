package openapi.bank.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;

import java.util.Collection;

/**
 * {@inheritDoc https://github.com/spring-projects/spring-security-oauth/blob/master/samples/oauth2/sparklr/src/main/java/org/springframework/security/oauth/examples/sparklr/oauth/SparklrUserApprovalHandler.java} 코드를 복사해 테스트함
 *
 * @author chanwook
 */
public class DefaultUserApprovalHandler extends ApprovalStoreUserApprovalHandler {

    private ClientDetailsService clientDetailsService;

    private boolean useApprovalStore = true;

    @Override
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
        super.setClientDetailsService(clientDetailsService);
    }

    public void setUseApprovalStore(boolean useApprovalStore) {
        this.useApprovalStore = useApprovalStore;
    }

    @Override
    public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        //TODO 실제로 돌려보면서 변수값과 함께 확인 필요..
        boolean approved = false;
        // If we are allowed to check existing approvals this will short circuit the decision
        if (useApprovalStore) {
            authorizationRequest = super.checkForPreApproval(authorizationRequest, userAuthentication);
            approved = authorizationRequest.isApproved();
        } else {
            if (clientDetailsService != null) {
                Collection<String> requestedScopes = authorizationRequest.getScope();
                try {
                    ClientDetails client = clientDetailsService
                            .loadClientByClientId(authorizationRequest.getClientId());
                    for (String scope : requestedScopes) {
                        if (client.isAutoApprove(scope)) {
                            approved = true;
                            break;
                        }
                    }
                } catch (ClientRegistrationException e) {
                }
            }
        }
        authorizationRequest.setApproved(approved);

        return authorizationRequest;
    }

}
