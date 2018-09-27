package com.starbucks.ui.controller;


import com.starbucks.commons.utils.HttpUtils;
import com.starbucks.commons.utils.MapperUtils;
import com.starbucks.domain.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(TbUser tbUser) throws Exception {



        String strTbUser = MapperUtils.obj2jsonIgnoreNull(tbUser);

        Map<String, String> param = new HashMap<>();
        param.put("tbUser", strTbUser);
        HttpUtils.doPost("http://localhost:8081/v1/register/save", param);

        return "login";
    }
}
