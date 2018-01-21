
package whattoplay.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import whattoplay.config.rest.RESTLogoutSuccessHandler;
import whattoplay.services.persistance.UserAuthService;
import whattoplay.config.rest.RESTAuthenticationEntryPoint;
import whattoplay.config.rest.RESTAuthenticationFailureHandler;
import whattoplay.config.rest.RESTAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    private RESTAuthenticationFailureHandler authenticationFailureHandler;
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    private RESTLogoutSuccessHandler restLogoutSuccessHandler;
    private UserAuthService userAuthService;
    private static final String REALM="MY_TEST_REALM";

    @Autowired
    public SecurityConfig(RESTAuthenticationEntryPoint authenticationEntryPoint, RESTAuthenticationFailureHandler authenticationFailureHandler,
                          RESTAuthenticationSuccessHandler authenticationSuccessHandler, RESTLogoutSuccessHandler restLogoutSuccessHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.restLogoutSuccessHandler = restLogoutSuccessHandler;
    }

    @Autowired
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/home", "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/getGames").permitAll()
                .antMatchers("/addGame/**").hasRole("ADMIN")
                .antMatchers("/saveAllGames").hasRole("USER")
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .and()
                .logout()
                .logoutSuccessHandler(restLogoutSuccessHandler)
            .and()
                .httpBasic()
                .realmName(REALM)
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setPasswordEncoder(encoder());
            authProvider.setUserDetailsService(userAuthService);
            return authProvider;
        }

        @Bean
        public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
        }



}
