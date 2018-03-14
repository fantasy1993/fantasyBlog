package com.fantasy.config;


import com.fantasy.shiro.MyShiroRealm;
import com.fantasy.user.model.UserPermission;
import com.fantasy.user.service.IUserPermissionService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fantasy on 2017/12/23.
 */
@Configuration
public class ShiroConfig {

    @Autowired(required = false)
    IUserPermissionService userPermissionService;
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置默认登录地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后跳转地址
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        List<UserPermission> userPermissionList = userPermissionService.selectAll();

        for (UserPermission userPermission : userPermissionList) {
            filterChainDefinitionMap.put(userPermission.getUrl(),
                    userPermission.getPermissionInit());
        }


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

}
