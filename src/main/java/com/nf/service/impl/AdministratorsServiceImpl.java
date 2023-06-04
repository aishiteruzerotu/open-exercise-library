package com.nf.service.impl;

import com.nf.Convert;
import com.nf.dao.AdministratorsDao;
import com.nf.dao.impl.AdministratorsDaoImpl;
import com.nf.entity.AdministratorsEntity;
import com.nf.service.AdministratorsService;
import com.nf.vo.AdministratorsVo;

public class AdministratorsServiceImpl implements AdministratorsService {
    private final AdministratorsDao dao = new AdministratorsDaoImpl();

    @Override
    public boolean longin(AdministratorsVo administratorsVo) {
        return 1 == dao.longin(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public boolean signUp(AdministratorsVo administratorsVo) {
        return 1 == dao.signUp(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public boolean update(AdministratorsVo administratorsVo) {
        return 1 == dao.update(Convert.toBean(AdministratorsEntity.class, administratorsVo));
    }

    @Override
    public boolean delete(int id) {
        return 1 == dao.delete(id);
    }
}
