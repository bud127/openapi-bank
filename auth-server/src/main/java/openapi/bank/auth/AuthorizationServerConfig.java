package openapi.bank.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author chanwook
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    UserApprovalHandler approvalHandler;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO 텍스트로 설정 구성에 대한 개선 방법 정리해보기. 그리고 실제 API와 클라이언트, SCOPE 간의 관계도..
        clients.inMemory()
            .withClient("client-app")
                .resourceIds("account")
                .authorizedGrantTypes("authorization_code")
                .authorities("ROLE_USER")
                .scopes("ACCOUNT_INQUIRY", "ACCOUNT_TRANSFER")
                .secret("_client_app_secret")
                .redirectUris("http://localhost:9000/main.app")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).userApprovalHandler(approvalHandler);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm("auth/client");
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Bean
    @Lazy
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public DefaultUserApprovalHandler userApprovalHandler(ApprovalStore approvalStore) throws Exception {
        DefaultUserApprovalHandler handler = new DefaultUserApprovalHandler();
        handler.setApprovalStore(approvalStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        handler.setUseApprovalStore(true);
        return handler;
    }

}
