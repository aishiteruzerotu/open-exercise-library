package com.nf.dao.impl;

import com.nf.SqlExecutorEx;
import com.nf.dao.AdministratorsDao;
import com.nf.entity.AdministratorsEntity;
import com.nf.handler.ScalarHandler;
import com.nf.util.DataSourceUtils;

public class AdministratorsDaoImpl implements AdministratorsDao {
    private final SqlExecutorEx executor = DataSourceUtils.getSqlExecutorEx();

    @Override
    public Long longin(AdministratorsEntity administratorsEntity) {
        String sql = "select count(*) from administrators where id=? and password=? or name=? and password=?";
        return executor.query(sql,new ScalarHandler<>(),
                administratorsEntity.getId(),administratorsEntity.getPassword(),
                administratorsEntity.getName(),administratorsEntity.getPassword());
    }

    @Override
    public int signUp(AdministratorsEntity administratorsEntity) {
        return executor.insertObject(administratorsEntity);
    }

    @Override
    public int update(AdministratorsEntity administratorsEntity) {
        String sql = "update student set name=?,password=? where id = ?";
        return executor.update(sql,administratorsEntity.getName(),
                                    administratorsEntity.getPassword(),
                                    administratorsEntity.getId());
    }

    @Override
    public int delete(int id) {
        return executor.delete(AdministratorsEntity.class,id);
    }
}
