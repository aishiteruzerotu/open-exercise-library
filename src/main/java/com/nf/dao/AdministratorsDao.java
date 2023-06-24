package com.nf.dao;

import com.nf.entity.AdministratorsEntity;
import com.nf.entity.Pagination;

import java.util.List;

public interface AdministratorsDao {
    /**
     * 使用id获取登入账号
     * @param id 管理员id
     * @return 管理员实体
     */
    AdministratorsEntity getAdmin(int id);

    /**
     * 使用name获取登入账号
     * @param name 管理员 name
     * @return 管理员实体
     */
    AdministratorsEntity getAdmin(String name);

    /**
     * 判断管理员账号密码是否正确
     * @param administratorsEntity 管理员账号
     * @return 被影响的行
     */
    Long longin(AdministratorsEntity administratorsEntity);

    /**
     * 注册账号
     * @param administratorsEntity 注册管理员
     * @return 被影响的行
     */
    int signUp(AdministratorsEntity administratorsEntity);

    /**
     * 修改账号
     * @param administratorsEntity 修改的账号
     * @return 被影响的行
     */
    int update(AdministratorsEntity administratorsEntity);

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
    List<AdministratorsEntity> getAdmins(Pagination pagination);

    /**
     * 删除账号
     * @param id 被删除账号id
     * @return 被影响的行
     */
    int delete(int id);
}
