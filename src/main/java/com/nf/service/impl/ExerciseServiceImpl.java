package com.nf.service.impl;

import com.nf.Convert;
import com.nf.dao.ExerciseDao;
import com.nf.dao.impl.ExerciseDaoImpl;
import com.nf.entity.ExerciseEntity;
import com.nf.entity.Pagination;
import com.nf.service.ExerciseLibraryConstants;
import com.nf.service.ExerciseService;
import com.nf.vo.ExerciseVo;
import com.nf.vo.OptionVo;

import java.util.*;

public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseDao dao = new ExerciseDaoImpl();

    @Override
    public List<ExerciseVo> getExercises() {
        return this.copyList(dao.getExercises());
    }

    @Override
    public ExerciseVo getExercise() {
        return ExerciseServiceImpl.copyExerciseVo(dao.getExercise());
    }

    @Override
    public ExerciseVo getExercise(int id) {
        return ExerciseServiceImpl.copyExerciseVo(dao.getExercise(id));
    }

    @Override
    public ExerciseVo getExercise(int id, String types) {
        return ExerciseServiceImpl.copyExerciseVo(dao.getExercise(id, types));
    }

    @Override
    public Long count() {
        return dao.count();
    }

    @Override
    public List<ExerciseVo> getPageExercises(Pagination pagination) {
        return this.copyList(dao.getPageExercises(pagination));
    }

    @Override
    public Long typesCount(String types) {
        return dao.typesCount(types);
    }

    @Override
    public List<ExerciseVo> getTypesPageExercises(String types, Pagination pagination) {
        return this.copyList(dao.getTypesPageExercises(types, pagination));
    }

    @Override
    public boolean delete(int id) {
        return 1 == dao.delete(id);
    }

    @Override
    public boolean insert(ExerciseVo exercise) {
        return 1 == dao.insert(this.copyExerciseEntity(exercise));
    }

    @Override
    public boolean update(ExerciseVo exercise) {
        return 1 == dao.update(this.copyExerciseEntity(exercise));
    }

    @Override
    public void answered(int id, boolean isCorrectness) {
        dao.answered(id, isCorrectness);
    }

    /**
     * 将 web 传入数据转为 数据库数据
     *
     * @param exercise web vo
     * @return 数据库实体类
     */
    private ExerciseEntity copyExerciseEntity(ExerciseVo exercise) {
        ExerciseEntity entity = Convert.toBean(ExerciseEntity.class, exercise);

        entity.setToOption(ExerciseServiceImpl.reduce(exercise.getToOptions()));
        entity.setAnswer(ExerciseServiceImpl.reduce(exercise.getAnswers()));

        return entity;
    }

    /**
     * 将数组按固定格式转成字符串
     *
     * @param arr String 数组
     * @return 固定格式的字符串
     */
    private static String reduce(String[] arr) {
        return Arrays.stream(arr).reduce((i, j) -> {
            return i + ExerciseLibraryConstants.truncation + j;
        }).orElse("");
    }

    /**
     * 把数据库中获取到的数据集合转化为响应数据集合
     *
     * @param list 数据库取出数据
     * @return web 响应数据集合
     */
    private List<ExerciseVo> copyList(List<ExerciseEntity> list) {
        return list.stream()
                .map(ExerciseServiceImpl::copyExerciseVo)
                .toList();
    }

    /**
     * 实体对象转为响应对象
     *
     * @param entity 实体
     * @return web 响应对象
     */
    private static ExerciseVo copyExerciseVo(ExerciseEntity entity) {
        if (entity == null) {
            return null;
        }

        ExerciseVo exerciseVo = Convert.toBean(ExerciseVo.class, entity);

        exerciseVo.setToOptions(ExerciseServiceImpl.split(entity.getToOption()));
        exerciseVo.setAnswers(ExerciseServiceImpl.split(entity.getAnswer()));

        exerciseVo.setOptions(ExerciseServiceImpl.generateOptionVos(exerciseVo));

        return exerciseVo;
    }

    /**
     * 把字符串按设定方式分割为字符串数组
     *
     * @param str 原字符串
     * @return 分割后的字符串数组
     */
    private static String[] split(String str) {
        return str.split(ExerciseLibraryConstants.truncation);
    }

    /**
     * 选项数组
     *
     * @param exerciseVo 题目信息
     * @return 随机的选项数组
     */
    private static OptionVo[] generateOptionVos(ExerciseVo exerciseVo) {
        String[] toOptions = exerciseVo.getToOptions();
        String[] answers = exerciseVo.getAnswers();
        Integer correct = exerciseVo.getCorrect();

        int length = toOptions.length;
        List<OptionVo> optionVoList = new ArrayList<>();

        //赋信息信息
        for (int i = 0; i < length; i++) {
            String optionValue = toOptions[i];

            boolean isCorrect = i < correct;

            String answer = isCorrect ? "" : answers[i - correct];

            optionVoList.add(ExerciseServiceImpl.generateOptionVo(optionValue, isCorrect, answer));
        }

        //随机排序
        Collections.shuffle(optionVoList);

        //赋值选项
        for (int i = 0; i < optionVoList.size(); i++) {
            ExerciseServiceImpl.setOptionVo(optionVoList.get(i), ExerciseLibraryConstants.optionLetters[i]);
        }

        return optionVoList.toArray(new OptionVo[length]);
    }

    /**
     * 生成 OptionVo 对象，没有 String toOption 值
     *
     * @return 生成对象
     */
    private static OptionVo generateOptionVo(String optionValue, boolean correct, String answer) {
        return new OptionVo(optionValue, correct, answer);
    }

    /**
     * 给 OptionVo 对象赋值 toOption
     *
     * @param optionVo OptionVo 对象
     * @param toOption 值
     */
    private static void setOptionVo(OptionVo optionVo, String toOption) {
        optionVo.setOption(toOption);
    }
}
