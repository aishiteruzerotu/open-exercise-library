package com.nf.service;

import com.nf.Convert;
import com.nf.dao.AdministratorsDao;
import com.nf.dao.impl.AdministratorsDaoImpl;
import com.nf.entity.AdministratorsEntity;
import com.nf.vo.AdministratorsVo;

public class AdministratorsServiceImpl implements AdministratorsService{
    private final AdministratorsDao dao = new AdministratorsDaoImpl();

    @Override
    public boolean longin(AdministratorsVo administratorsVo) {
        AdministratorsEntity administratorsEntity = Convert.toBean(AdministratorsEntity.class, administratorsVo);
        return dao.longin(administratorsEntity)==1;
    }

    @Override
    public boolean signUp(AdministratorsVo administratorsVo) {
        AdministratorsEntity administratorsEntity = Convert.toBean(AdministratorsEntity.class, administratorsVo);
        return dao.signUp(administratorsEntity)==1;
    }

    @Override
    public boolean update(AdministratorsVo administratorsVo) {
        AdministratorsEntity administratorsEntity = Convert.toBean(AdministratorsEntity.class, administratorsVo);
        return dao.update(administratorsEntity)==1;
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id)==1;
    }
}
