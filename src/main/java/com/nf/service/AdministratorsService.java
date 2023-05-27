package com.nf.service;

import com.nf.vo.AdministratorsVo;

public interface AdministratorsService {
    /**
     * 判断管理员账号密码是否正确
     * @param administratorsVo 管理员账号
     * @return 用户账号密码输入错误则返回假
     */
    boolean longin(AdministratorsVo administratorsVo);

    /**
     * 注册账号
     * @param administratorsVo 注册管理员
     * @return 注册成功返回真
     */
    boolean signUp(AdministratorsVo administratorsVo);
}
