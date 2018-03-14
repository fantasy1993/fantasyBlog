package com.fantasy.shiro.service;

import com.fantasy.user.model.UserPermission;
import com.fantasy.user.service.IUserPermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限修改后进行更新
 * Created by fantasy on 2018/1/20.
 */
public class shiroService {

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    IUserPermissionService userPermissionService;
    /**
     * 初始化权限
     * @return
     */
    public Map<String,String> loadFilterChainDefinitions() {
        Map<String,String> filerChainDefinitionMap = new LinkedHashMap<String,String>();
        List<UserPermission> userPermissionList= userPermissionService.selectAll();
        userPermissionList.stream().forEach(userPermission->{
            filerChainDefinitionMap.put(userPermission.getUrl(),userPermission.getPermissionInit());
        });
        return filerChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission(){
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter =null;
            try{
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            System.out.println("更新权限成功！！");
        }
    }

}
