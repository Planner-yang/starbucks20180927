package com.starbucks.ui.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;
import com.starbucks.commons.utils.CookieUtil;
import com.starbucks.domain.TbUser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String kaptcha = request.getParameter("kaptcha");
        String rememberMe = request.getParameter("rememberMe");

        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpResponse httpResponse = null;

        try {
            //创建HttpClient客户端
            CloseableHttpClient httpClient = HttpClients.createDefault();

            // 创建 HttpGet 请求
            HttpGet httpGet = new HttpGet("http://localhost:8081/v1/login/verify?name="+name);
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();

            String json = EntityUtils.toString(entity);

            //判断是否存在该用户
            String strJson = json.substring(1, json.length() - 1);
            if (!strJson.equals("null")) {
                JsonNode jsonNode = mapper.readTree(json);
                JsonNode password1 = jsonNode.findPath("password");

                //判断密码是否正确
                String strPassword = password1.toString().substring(1, password1.toString().length()-1);
                if ((DigestUtils.md5DigestAsHex(password.getBytes())).equals(strPassword)) {

                    //判断验证码是否正确
                    if (request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(kaptcha)) {

                        String substring = json.substring(1, json.length()-1);
                        TbUser tbUser = mapper.readValue(substring, TbUser.class);
                        request.getSession().setAttribute("tbUser",tbUser);

                        //判断是否勾上记住我
                        if ("on".equals(rememberMe)) {
                            CookieUtil.setCookie(request, response, "StarbucksUser", tbUser.getUsername()+":"+tbUser.getPassword(), 7*24*60*60);
                        }

                        //没有勾上记住我则清理cookie
                        else {
                            CookieUtil.deleteCookie(request,response, "StarbucksUser");
                        }

                        return "index";
                    }

                    else {
                        redirectAttributes.addFlashAttribute("failed","验证码错误！");
                    }

                }

                else {
                    redirectAttributes.addFlashAttribute("failed","密码错误！");
                }
            }

            else {
                redirectAttributes.addFlashAttribute("failed","用户名或者邮箱错误！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }
}
