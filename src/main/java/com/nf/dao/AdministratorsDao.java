package com.nf.dao;

import com.nf.entity.AdministratorsEntity;

public interface AdministratorsDao {
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
    Long signUp(AdministratorsEntity administratorsEntity);

    /**
     * 修改账号
     * @param administratorsEntity 修改的账号
     * @return 被影响的行
     */
    Long update(AdministratorsEntity administratorsEntity);

    /**
     * 删除账号
     * @param id 被删除账号id
     * @return 被影响的行
     */
    Long delete(int id);
}
