package com.nf.dao.impl;

import com.nf.GenerateSQLRealize;
import com.nf.SqlExecutorEx;
import com.nf.dao.AdministratorsDao;
import com.nf.entity.AdministratorsEntity;
import com.nf.entity.Pagination;
import com.nf.handler.BeanHandler;
import com.nf.handler.BeanListHandler;
import com.nf.handler.ScalarHandler;
import com.nf.util.DataSourceUtils;

import java.util.List;

public class AdministratorsDaoImpl implements AdministratorsDao {
    private final SqlExecutorEx executor = DataSourceUtils.getSqlExecutorEx();
    private final String SQL_SELECT = new GenerateSQLRealize().generateSelect(AdministratorsEntity.class);

    @Override
    public AdministratorsEntity getAdmin(int id) {
        String sql = SQL_SELECT + " where id=?";
        return executor.query(sql, new BeanHandler<>(AdministratorsEntity.class), id);
    }

    @Override
    public AdministratorsEntity getAdmin(String name) {
        String sql = SQL_SELECT + " where name=?";
        return executor.query(sql, new BeanHandler<>(AdministratorsEntity.class), name);
    }

    @Override
    public Long longin(AdministratorsEntity administratorsEntity) {
        String sql = "select count(*) from administrators where id=? and password=? or name=? and password=?";
        return executor.query(sql, new ScalarHandler<>(),
                administratorsEntity.getId(), administratorsEntity.getPassword(),
                administratorsEntity.getName(), administratorsEntity.getPassword());
    }

    @Override
    public int signUp(AdministratorsEntity administratorsEntity) {
        return executor.insertObject(administratorsEntity);
    }

    @Override
    public int update(AdministratorsEntity administratorsEntity) {
        String sql = "update administrators set name=?,password=? where id = ?";
        return executor.update(sql, administratorsEntity.getName(),
                administratorsEntity.getPassword(),
                administratorsEntity.getId());
    }

    @Override
    public Long count() {
        String sql = "select count(*) from administrators ";
        return executor.query(sql,new ScalarHandler<>());
    }

    @Override
    public List<AdministratorsEntity> getAdmins(Pagination pagination) {
        String sql = new GenerateSQLRealize().generateSelect(AdministratorsEntity.class);
        sql += " limit ?,?";
        return executor.query(sql, new BeanListHandler<>(AdministratorsEntity.class),
                pagination.getStart(), pagination.getCount());
    }

    @Override
    public int delete(int id) {
        return executor.delete(AdministratorsEntity.class, id);
    }
}
