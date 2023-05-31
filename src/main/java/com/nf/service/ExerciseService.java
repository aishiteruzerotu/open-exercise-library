package com.nf.service;

import com.nf.vo.ExerciseVo;
import com.nf.entity.Pagination;

import java.util.List;

public interface ExerciseService {
    /**
     * 获取全部的题目信息
     * @return  全部题目
     */
    List<ExerciseVo> getExercises();

    /**
     * 用户答的题
     * @return 单个题
     */
    ExerciseVo getExercise();

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
    List<ExerciseVo> getPageExercises(Pagination pagination);

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
    List<ExerciseVo> getTypesPageExercises(String types, Pagination pagination);

    /**
     * 根据题目 id 删除题目
     * @param id 题目编号
     * @return 是否删除成功
     */
    boolean delete(int id);

    /**
     * 添加题目
     * @param exercise 题目信息
     * @return 是否添加成功
     */
    boolean insert(ExerciseVo exercise);

    /**
     * 修改题目
     * @param exercise 题目信息
     * @return 是否修改成功
     */
    boolean update(ExerciseVo exercise);

    /**
     * 用户回答了 id 这个题后，对这个题的答题数量做增加操作，同时传入的 isCorrectness 是判断该题是否是用户答对的题，是输入真
     * @param id 题号
     * @param isCorrectness 该题是否被用户答对
     */
    void answered(int id,boolean isCorrectness);
}
