package com.nf.service.impl;

import com.nf.Convert;
import com.nf.dao.AdministratorsDao;
import com.nf.dao.impl.AdministratorsDaoImpl;
import com.nf.entity.AdministratorsEntity;
import com.nf.entity.Pagination;
import com.nf.service.AdministratorsService;
import com.nf.vo.AdministratorsVo;

import java.util.List;

public class AdministratorsServiceImpl implements AdministratorsService {
    private final AdministratorsDao dao = new AdministratorsDaoImpl();

    @Override
    public AdministratorsVo getAdmin(int id) {
        AdministratorsEntity admin = dao.getAdmin(id);
        return admin == null ? null : Convert.toBean(AdministratorsVo.class, dao.getAdmin(id));
    }

    @Override
    public AdministratorsVo getAdmin(String name) {
        AdministratorsEntity admin = dao.getAdmin(name);
        return admin == null ? null : Convert.toBean(AdministratorsVo.class, dao.getAdmin(name));
    }

    @Override
    public boolean longin(AdministratorsVo administratorsVo) {
        return 1 == dao.longin(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public boolean signUp(AdministratorsVo administratorsVo) {
        AdministratorsEntity admin = dao.getAdmin(administratorsVo.getName());
        if (admin!=null){
            throw new RuntimeException("用户名已被占用");
        }

        return 1 == dao.signUp(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public boolean update(AdministratorsVo administratorsVo) {
        return 1 == dao.update(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public List<AdministratorsVo> getAdmins(Pagination pagination) {
        return dao.getAdmins(pagination).stream().map(administratorsEntity -> Convert.toBean(AdministratorsVo.class, administratorsEntity)).toList();
    }

    @Override
    public boolean delete(int id) {
        AdministratorsEntity admin = dao.getAdmin(id);
        if (admin.getAuthority()){
            return false;
        }
        return 1 == dao.delete(id);
    }
}
