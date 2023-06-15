package com.nf.web.controller;

import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.mvc.annotation.RequestParam;
import com.nf.service.AdministratorsService;
import com.nf.service.impl.AdministratorsServiceImpl;
import com.nf.vo.AdministratorsVo;
import com.nf.vo.ResponseVO;

import java.util.Optional;

import static com.nf.mvc.handler.HandlerHelper.json;

@RequestController("/admin")
public class AdministratorsController {
    private final AdministratorsService service = new AdministratorsServiceImpl();

    @RequestMapping("/longin")
    public ViewResult longin(@RequestModel AdministratorsVo administratorsVo) {
        AdministratorsVo admin = Optional.of(service.getAdmin(administratorsVo.getId()))
                .orElse(service.getAdmin(administratorsVo.getName()));
        return service.longin(administratorsVo) ?
                json(new ResponseVO(200, "登入成功", admin)) :
                json(new ResponseVO(500, "登入失败", false));
    }

    @RequestMapping("/sign/up")
    public ViewResult signUp(@RequestModel AdministratorsVo administratorsVo) {

        return service.signUp(administratorsVo) ?
                json(new ResponseVO(200, "注册成功", administratorsVo)) :
                json(new ResponseVO(500, "注册失败", false));
    }

    @RequestMapping("/update")
    public ViewResult update(@RequestModel AdministratorsVo administratorsVo) {

        return service.update(administratorsVo) ?
                json(new ResponseVO(200, "修改成功", administratorsVo)) :
                json(new ResponseVO(500, "修改失败", false));
    }

    @RequestMapping("/delete")
    public ViewResult delete(@RequestParam("id") int id) {

        return service.delete(id) ?
                json(new ResponseVO(200, "删除成功", true)) :
                json(new ResponseVO(500, "删除成功", false));
    }
}
