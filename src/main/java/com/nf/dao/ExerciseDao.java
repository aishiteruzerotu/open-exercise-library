package com.nf.dao;

import com.nf.entity.ExerciseEntity;
import com.nf.entity.Pagination;

import java.util.List;

public interface ExerciseDao {
    /**
     * 获取全部的题目信息
     * @return  全部题目
     */
    List<ExerciseEntity> getExercises();

    /**
     * 用户答的题
     * @return 单个题
     */
    ExerciseEntity getExercise();

    /**
     * 获取全部题目数量
     * @return 题目数量
     */
    Long count();

    /**
     * 已分页的形式获取到数据
     * @param pagination 分页依据对象
     * @return 分页的数据信息
     */
    List<ExerciseEntity> getPageExercises(Pagination pagination);

    /**
     * 根据题目类型获取到对应的数量
     * @param types 题目类型
     * @return 题目数量
     */
    Long typesCount(String types);

    /**
     * 根据题目类型获取到对应的分页后的数据
     * @param types 题目类型
     * @param pagination 分页依据
     * @return 分页的数据信息
     */
    List<ExerciseEntity> getTypesPageExercises(String types,Pagination pagination);

    /**
     * 根据题目 id 删除题目
     * @param id 题目编号
     * @return 是否删除成功
     */
    Long delete(int id);

    /**
     * 添加题目
     * @param exercise 题目信息
     * @return 是否添加成功
     */
    Long insert(ExerciseEntity exercise);

    /**
     * 修改题目
     * @param exercise 题目信息
     * @return 是否修改成功
     */
    Long update(ExerciseEntity exercise);
}
