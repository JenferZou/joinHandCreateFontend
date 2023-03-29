package com.hr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.interceptor.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Resource
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    // 自定义的Jwt Token过滤器
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Autowired
    private UserDetailsService userDetailsService; // 注入所需要的实例

    @Bean // 如果要想使用密码，则必须配置有一个密码的编码器
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()// 自定义认证失败类
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义权限不足处理类
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginProcessingUrl("/login") // 登录的处理路径
                .usernameParameter("username").passwordParameter("password") // 认证的参数名称
                .permitAll()//自定义认证成功处理器
                .successHandler(jwtAuthenticationSuccessHandler)
                // 自定义失败拦截器
                .failureHandler(loginFailureHandler)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {// 注销成功之后返回的数据内容
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                       // Object principal = authentication.getPrincipal(); // 认证以及授权的内容
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_OK); // 响应200的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_OK); // 当前的登录状态
                        result.put("message", "用户注销成功");
                        result.put("principal", null); // 实际的开发中对于认证数据肯定要筛选
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                })
                .and()
                // 下面开始设置权限
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN") // ADMIN角色可以访问
                .antMatchers("/student/**").hasRole("USER")
                //.antMatchers("/message/**").access(
                //   "hasAnyRole('ADMIN') and hasRole('USER')")
                .anyRequest().authenticated() // 请求认证访问
                .and().addFilterBefore(this.authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(userDetailsService).cors(Customizer.withDefaults())
                .build();
    }


   /* @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()    // 进行授权访问请求的配置（那些路径与那些角色进行匹配）
                .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN角色可以访问
                .antMatchers("/student/**").access("hasAnyRole('USER') and hasAnyRole('ADMIN')") // ADMIN+USER角色可以访问
                //.antMatchers("/message/**").access(
                     //   "hasAnyRole('ADMIN') and hasRole('USER')")
                .anyRequest().authenticated() // 请求认证访问
                .and() // 继续连接后续的其他配置项
                .formLogin().loginProcessingUrl("/login") // 登录的处理路径
                .usernameParameter("username").passwordParameter("password") // 认证的参数名称
                .permitAll()
                .successHandler(new AuthenticationSuccessHandler() { // 实现认证成功之后的配置处理
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal(); // 认证以及授权的内容
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_OK); // 响应200的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_OK); // 当前的登录状态
                        result.put("message", "用户登录成功");
                        result.put("principal", principal); // 实际的开发中对于认证数据肯定要筛选
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_UNAUTHORIZED); // 当前的登录状态
                        result.put("principal", null); // 返回一个空的认证数据
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        if (exception instanceof LockedException) {
                            result.put("message", "账户被锁定，登录失败！");
                        } else if (exception instanceof BadCredentialsException) {
                            result.put("message", "账户名或密码输入错误，登录失败！");
                        } else if (exception instanceof DisabledException) {
                            result.put("message", "账户被禁用，登录失败！");
                        } else if (exception instanceof AccountExpiredException) {
                            result.put("message", "账户已过期，登录失败！");
                        } else if (exception instanceof CredentialsExpiredException) {
                            result.put("message", "密码已过期，登录失败！");
                        } else {
                            result.put("message", "未知原因，导致登录失败！");
                        }
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                }).and().logout().logoutUrl("/logout") // 注销处理
                .clearAuthentication(true) // 清除掉所有的认证信息
                .invalidateHttpSession(true) // 注销当前的Session
                .logoutSuccessHandler(new LogoutSuccessHandler() {// 注销成功之后返回的数据内容
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal(); // 认证以及授权的内容
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_OK); // 响应200的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_OK); // 当前的登录状态
                        result.put("message", "用户注销成功");
                        result.put("principal", null); // 实际的开发中对于认证数据肯定要筛选
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                })
                .and().csrf().disable().userDetailsService(userDetailsService).cors(Customizer.withDefaults()).build();
    }
*/
}
