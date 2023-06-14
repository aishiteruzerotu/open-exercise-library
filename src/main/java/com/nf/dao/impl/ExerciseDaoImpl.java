package com.nf.dao.impl;

import com.nf.GenerateSQL;
import com.nf.GenerateSQLRealize;
import com.nf.SqlExecutorEx;
import com.nf.dao.ExerciseDao;
import com.nf.entity.ExerciseEntity;
import com.nf.entity.Pagination;
import com.nf.handler.BeanListHandler;
import com.nf.handler.ScalarHandler;
import com.nf.util.DataSourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
        Random random = new Random();
        int id = random.nextInt(this.count().intValue()) + 1;
        return this.getExercise(id);
    }

    public synchronized ExerciseEntity getExercise(int id) {
        String sql = generate.generateSelect(ExerciseEntity.class) + " where id=? ";
        return executor.queryBean(sql, ExerciseEntity.class, id);
    }

    /**
     * 测试随机抓取数据
     *
     * @param args 入口
     */
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i1 = 0; i1 < 40000; i1++) {
            list.add(random.nextInt(new ExerciseDaoImpl().count().intValue()) + 1);
        }

        Map<Integer, List<Integer>> map = list.stream().collect(Collectors.groupingBy(integer -> integer));

        map.forEach((key, val) -> {
            System.out.println("" + key + " --- " + (long) val.size());
        });
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
        if (isCorrectness){
            String sql = "update exerciseLibrary set total=? where id=?";
            executor.update(sql,(exercise.getTotal()+1),exercise.getId());
        }else {
            String sql = "update exerciseLibrary set total=?,numberErrors=? where id=?";
            executor.update(sql,(exercise.getTotal()+1),(exercise.getNumberErrors()+1),exercise.getId());
        }
    }
}
