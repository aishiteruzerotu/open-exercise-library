package com.nf.dao.impl;

import com.nf.GenerateSQL;
import com.nf.GenerateSQLRealize;
import com.nf.SqlExecutorEx;
import com.nf.dao.ExerciseDao;
import com.nf.entity.ExerciseEntity;
import com.nf.entity.Pagination;
import com.nf.handler.BeanHandler;
import com.nf.handler.BeanListHandler;
import com.nf.handler.ScalarHandler;
import com.nf.util.DataSourceUtils;

import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {
    private final SqlExecutorEx executor = DataSourceUtils.getSqlExecutorEx();
    private final GenerateSQL generate = new GenerateSQLRealize();
    private final String COUNT_SQL = "select count(*) from exerciseLibrary ";

    @Override
    public List<ExerciseEntity> getExercises() {
        return executor.queryBeanList(ExerciseEntity.class);
    }

    @Override
    public ExerciseEntity getExercise() {
        String sql = "select t1.id,types,correct,topic,toOption,answer,total,numberErrors from exerciseLibrary AS t1 " +
                " JOIN (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM exerciseLibrary)-(SELECT MIN(id) FROM exerciseLibrary)) " +
                "+(SELECT MIN(id) FROM exerciseLibrary)) AS id) AS t2 " +
                " WHERE t1.id >= t2.id " +
                " ORDER BY t1.id LIMIT 1";
        return executor.queryBean(sql,ExerciseEntity.class);
    }

    @Override
    public synchronized ExerciseEntity getExercise(int id) {
        String sql = generate.generateSelect(ExerciseEntity.class) + " where id=? ";
        return executor.queryBean(sql, ExerciseEntity.class, id);
    }

    @Override
    public ExerciseEntity getExercise(int id, String types) {
        String sql = generate.generateSelect(ExerciseEntity.class) + " where id=? and types=?";
        return executor.queryBean(sql, ExerciseEntity.class, id, types);
    }

    @Override
    public Long count() {
        return executor.query(COUNT_SQL, new ScalarHandler<>());
    }

    @Override
    public List<ExerciseEntity> getPageExercises(Pagination pagination) {
        String sql = generate.generateSelect(ExerciseEntity.class);
        sql += " limit ?,?";
        return executor.query(sql, new BeanListHandler<>(ExerciseEntity.class),
                pagination.getStart(), pagination.getCount());
    }

    @Override
    public Long typesCount(String types) {
        String sql = COUNT_SQL + " where types=?";
        return executor.query(sql, new ScalarHandler<>(), types);
    }

    @Override
    public List<ExerciseEntity> getTypesPageExercises(String types, Pagination pagination) {
        String sql = generate.generateSelect(ExerciseEntity.class);
        sql += " where types=? limit ?,?";
        return executor.query(sql, new BeanListHandler<>(ExerciseEntity.class), types,
                pagination.getStart(), pagination.getCount());
    }

    @Override
    public int delete(int id) {
        return executor.delete(ExerciseEntity.class, id);
    }

    @Override
    public int insert(ExerciseEntity exercise) {
        return executor.insertObject(exercise);
    }

    @Override
    public int update(ExerciseEntity exercise) {
        String sql = "update exerciseLibrary set types=?,correct=?,topic=?,toOption=?,answer=? where id=?";
        return executor.update(sql, exercise.getTypes(),
                exercise.getCorrect(), exercise.getTopic(),
                exercise.getToOption(), exercise.getAnswer(),
                exercise.getId());
    }

    @Override
    public synchronized void answered(int id, boolean isCorrectness) {
        ExerciseEntity exercise = this.getExercise(id);
        if (exercise == null) {
            return;
        }
        if (isCorrectness) {
            String sql = "update exerciseLibrary set total=? where id=?";
            executor.update(sql, (exercise.getTotal() + 1), exercise.getId());
        } else {
            String sql = "update exerciseLibrary set total=?,numberErrors=? where id=?";
            executor.update(sql, (exercise.getTotal() + 1), (exercise.getNumberErrors() + 1), exercise.getId());
        }
    }
}
