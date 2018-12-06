package project.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Import(SecurityConfiguration.class)
public class SecurityOAuth2Configuration extends AuthorizationServerConfigurerAdapter   implements EnvironmentAware{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static String REALM = "CRM_REALM";
    private static final int ONE_DAY = 60 * 60 * 24;
    private static final int THIRTY_DAYS = 60 * 60 * 24 * 30;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.realm(REALM);
//        oauthServer.checkTokenAccess("permitAll()");
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    
    public enum Authorities {

        ROLE_ANONYMOUS,
        ROLE_USER,
        ROLE_ADMIN

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("jnan")
//                .secret("jnanv123")
//                .authorizedGrantTypes("password", "refresh_token")
//                .authorities("ROLE_USER")
//                .scopes("read", "write", "trust")
//                .accessTokenValiditySeconds(2000)
//                .refreshTokenValiditySeconds(THIRTY_DAYS);
        clients.jdbc(dataSource)/*.withClient(propertyResolver.getProperty(PROP_CLIENTID))
        .scopes("read", "write")
        .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
        .authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
        .secret(propertyResolver.getProperty(PROP_SECRET))
        .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800))*/;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        endpoints.tokenStore(tokenStore).approvalStoreDisabled()
                .authenticationManager(authenticationManager);
    }
    
    private static final String ENV_OAUTH = "authentication.oauth.";
    private static final String PROP_CLIENTID = "clientid";
    private static final String PROP_SECRET = "secret";
    private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";

    private RelaxedPropertyResolver propertyResolver;
    
    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
    }
}
