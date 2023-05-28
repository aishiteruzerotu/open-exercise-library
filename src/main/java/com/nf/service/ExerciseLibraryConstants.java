package com.nf.service;

/**
 * 项目服务常量
 */
public interface ExerciseLibraryConstants {
    /**
     * 中间截断符，处理 选项 答案 字符串数据的解析以及生成其字符串的截断符
     * 尽可能的不与数据重复的同时简短
     */
    String truncation = "<>BCT<>";

    /**
     * 选项字母常量
     */
    String[] optionLetters = {"A", "B", "C", "D", "E", "F", "G", "L"};
}
