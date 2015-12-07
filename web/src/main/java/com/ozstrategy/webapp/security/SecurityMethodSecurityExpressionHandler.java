package com.ozstrategy.webapp.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class SecurityMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private WebSecurityExpressionRoot root;

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }

    public void setRoot(WebSecurityExpressionRoot root) {
        this.root = root;
    }

    public WebSecurityExpressionRoot getRoot() {
        return root;
    }
} // end class DefaultWebSecurityExpressionHandler
