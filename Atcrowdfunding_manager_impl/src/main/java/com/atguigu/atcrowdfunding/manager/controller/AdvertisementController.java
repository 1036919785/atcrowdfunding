package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.Consts;
import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.AdvertisementService;
import com.atguigu.atcrowdfunding.util.jsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

/**
 * @Author:jiege
 * @Date: 2019/6/12 20:11
 */
@Controller
@RequestMapping("/advert")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/index")
    public String index(){
        return "advert/index";
    }

    @RequestMapping("/add")
    public String add(){
        return "advert/add";
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(HttpServletRequest request, Advertisement advertisement, HttpSession session){
        jsonResult jsonresult = new jsonResult();

        try {
            MultipartHttpServletRequest mreq = (MultipartHttpServletRequest) request;
            MultipartFile mfile = mreq.getFile("advpicName");

            String name = mfile.getOriginalFilename();//得到文件名:java.jpg
            String extname = name.substring(name.lastIndexOf("."));//.jpg
            String iconpath = UUID.randomUUID().toString() + extname;//uuid.jpg

            ServletContext servletContext = session.getServletContext();
            String realPath = servletContext.getRealPath("/pics");
            String path = realPath + "\\adv\\" + iconpath;

            mfile.transferTo(new File(path));

            User user = (User) session.getAttribute(Consts.LOGIN_USER);
            advertisement.setUserid(user.getId());
            advertisement.setStatus("1");
            advertisement.setIconpath(iconpath);

            int count = advertisementService.insertAdvert(advertisement);
            jsonresult.setSuccessful(count==1);
            jsonresult.setMessage("保存成功!");
        } catch (Exception e) {
            jsonresult.setSuccessful(false);
            jsonresult.setMessage("保存失败!");
            e.printStackTrace();
        }

        return jsonresult;
    }

}
