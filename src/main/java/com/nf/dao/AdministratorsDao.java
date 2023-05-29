package com.nf.dao;

import com.nf.entity.AdministratorsEntity;

public interface AdministratorsDao {
    /**
     * 判断管理员账号密码是否正确
     * @param administratorsEntity 管理员账号
     * @return 用户账号密码输入错误则返回假
     */
    Long longin(AdministratorsEntity administratorsEntity);

    /**
     * 注册账号
     * @param administratorsEntity 注册管理员
     * @return 注册成功返回真
     */
    Long signUp(AdministratorsEntity administratorsEntity);
}
