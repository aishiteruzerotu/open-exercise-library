package com.nf.web.controller;

import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.service.AdministratorsService;
import com.nf.vo.AdministratorsVo;
import com.nf.vo.ResponseVO;

import static com.nf.mvc.handler.HandlerHelper.json;

@RequestController("/admin")
public class AdministratorsController {
    private AdministratorsService service;

    @RequestMapping("/longin")
    public ViewResult longin(@RequestModel AdministratorsVo administratorsVo){

        return json(new ResponseVO(200,"登入成功",null));
    }

    @RequestMapping("/sign/up")
    public ViewResult signUp(@RequestModel AdministratorsVo administratorsVo){

        return json(new ResponseVO(200,"注册成功",null));
    }
}
