package com.nf.service.impl;

import com.nf.Convert;
import com.nf.dao.ExerciseDao;
import com.nf.dao.impl.ExerciseDaoImpl;
import com.nf.entity.ExerciseEntity;
import com.nf.entity.Pagination;
import com.nf.service.ExerciseService;
import com.nf.vo.ExerciseVo;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseDao dao = new ExerciseDaoImpl();

    @Override
    public List<ExerciseVo> getExercises() {
        return null;
    }

    @Override
    public ExerciseVo getExercise() {
        return null;
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public List<ExerciseVo> getPageExercises(Pagination pagination) {
        return null;
    }

    @Override
    public Long typesCount(String types) {
        return dao.typesCount(types);
    }

    @Override
    public List<ExerciseVo> getTypesPageExercises(String types, Pagination pagination) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return 1 == dao.delete(id);
    }

    @Override
    public boolean insert(ExerciseVo exercise) {
        return 1 == dao.insert(Convert.toBean(ExerciseEntity.class, exercise));
    }

    @Override
    public boolean update(ExerciseVo exercise) {
        return 1 == dao.update(Convert.toBean(ExerciseEntity.class, exercise));
    }

    @Override
    public void answered(int id, boolean isCorrectness) {
        dao.answered(id, isCorrectness);
    }
}
