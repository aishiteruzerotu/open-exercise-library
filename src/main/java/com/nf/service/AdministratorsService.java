package com.nf.service;

import com.nf.entity.AdministratorsEntity;
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

    /**
     * 修改账号
     * @param administratorsVo 修改的账号
     * @return 是否修改成功
     */
    Long update(AdministratorsVo administratorsVo);

    /**
     * 删除账号
     * @param id 被删除账号id
     * @return 是否删除成功
     */
    Long delete(int id);
}
