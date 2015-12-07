package com.ozstrategy.webapp.security;

import com.ozstrategy.model.user.Feature;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.user.FeatureManager;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.RoleCommand;
import com.ozstrategy.webapp.command.user.UserCommand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liuqian
 * Date: 13-7-3
 * Time: PM3:07
 * To change this template use File | Settings | File Templates.
 */
public class WebAuthenticationSuccessLoggerHandler extends WebAuthenticationLoggerHandler implements AuthenticationSuccessHandler {
    static final String ROLE_USER="ROLE_USER";
    static final String ROLE_ADMIN="ROLE_ADMIN";
    static final String ROLE_TENANT="ROLE_TENANT";
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response, Authentication authentication)
    throws IOException, ServletException {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      String platform=request.getParameter("platform");
      User user = (User)authentication.getPrincipal();
      if (user == null) {
          return;
      }
      UserCommand userCommand=new UserCommand(user);
      List<RoleCommand> roleCommands =  userCommand.getRoles();
      if(roleCommands!=null && roleCommands.size()>0){
          userCommand.setRoleName(roleCommands.get(0).getName());
          userCommand.setRoleDisplayName(roleCommands.get(0).getDisplayName());
      }
      if(StringUtils.equals(PLATFORM, platform)){
          request.getSession().setAttribute("userinfo",userCommand);
          String roleName=userCommand.getRoleName();
          if(ROLE_ADMIN.equals(roleName)){
              response.sendRedirect("html/home#user/index");
          }else if(ROLE_USER.equals(roleName)){
              response.sendRedirect("html/home#assess/org");
          }else if(ROLE_TENANT.equals(roleName)){
              response.sendRedirect("html/home#leader/lsurvey");
          }

          return;
      }

      String result=objectMapper.writeValueAsString(new JsonReaderSingleResponse<UserCommand>(userCommand));
      response.getWriter().print(result);
  }
}
