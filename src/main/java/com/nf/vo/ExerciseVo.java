package com.nf.vo;

import com.nf.annotate.Auto;
import com.nf.annotate.PrimaryKey;

public class ExerciseVo {
    @Auto
    @PrimaryKey
    private Integer id;//编号
    private String types;//题目的类型
    private Integer correct;//提名都正确数量
    private String topic;//题目
    private String toOption;//选项
    private String answer;// 答案(解析)
    //默认为 0 是因为，未被答题时候应该是 0
    private Long total = 0L;//该题被答的次数
    private Long numberErrors = 0L;//被答错的次数

    @Override
    public String toString() {
        return "ExerciseVo{" +
                "id=" + id +
                ", types='" + types + '\'' +
                ", correct=" + correct +
                ", topic='" + topic + '\'' +
                ", toOption='" + toOption + '\'' +
                ", answer='" + answer + '\'' +
                ", total=" + total +
                ", numberErrors=" + numberErrors +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToOption() {
        return toOption;
    }

    public void setToOption(String toOption) {
        this.toOption = toOption;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getNumberErrors() {
        return numberErrors;
    }

    public void setNumberErrors(Long numberErrors) {
        this.numberErrors = numberErrors;
    }
}
