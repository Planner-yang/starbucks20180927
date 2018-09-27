package com.starbucks.api.controller.v1;

import com.starbucks.api.service.TbUserService;
import com.starbucks.commons.utils.IdUniqueUtils;
import com.starbucks.commons.utils.MapperUtils;
import com.starbucks.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v1")
public class TbUserControllerV1 {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 根据用户名或邮箱验证
     * @return
     */
    @RequestMapping(value = "/login/verify", method = RequestMethod.GET)
    public List<TbUser> login (HttpServletRequest request) {
        String name = request.getParameter("name");

        TbUser tbUser = tbUserService.get(name);
        List<TbUser> list = new ArrayList<>();
        list.add(tbUser);
        return list;
    }

    @RequestMapping(value = "/register/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request) throws Exception {

        String strTbUser = request.getParameter("tbUser");
        TbUser tbUser = MapperUtils.json2pojo(strTbUser, TbUser.class);
        tbUser.setId(IdUniqueUtils.generateId());
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUserService.add(tbUser);
    }
}
