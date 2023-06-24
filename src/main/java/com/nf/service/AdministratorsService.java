package com.nf.service;

import com.nf.entity.Pagination;
import com.nf.vo.AdministratorsVo;

import java.util.List;

public interface AdministratorsService {
    /**
     * 使用id获取登入账号
     * @param id 管理员id
     * @return 管理员实体
     */
    AdministratorsVo getAdmin(int id);

    /**
     * 使用name获取登入账号
     * @param name 管理员 name
     * @return 管理员实体
     */
    AdministratorsVo getAdmin(String name);

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
    boolean update(AdministratorsVo administratorsVo);

    /**
     * 获取全部题目数量
     * @return 题目数量
     */
    Long count();

    /**
     * 分页获取管理员
     * @param pagination 分页方法
     * @return 管理员集合
     */
    List<AdministratorsVo> getAdmins(Pagination pagination);

    /**
     * 删除账号
     * @param id 被删除账号id
     * @return 是否删除成功
     */
    boolean delete(int id);
}
