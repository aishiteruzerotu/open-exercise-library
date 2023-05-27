package com.nf.web.controller;

import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.vo.AdministratorsVo;

@RequestController("/admin")
public class AdministratorsController {
    @RequestMapping("/longin")
    public ViewResult longin(AdministratorsVo administratorsVo){

        return null;
    }

    @RequestMapping("/sign/up")
    public ViewResult signUp(AdministratorsVo administratorsVo){

        return null;
    }
}
