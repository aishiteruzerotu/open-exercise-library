package com.nf.vo;

import com.nf.annotate.Auto;
import com.nf.annotate.PrimaryKey;

import java.util.Arrays;

public class ExerciseVo {
    @Auto
    @PrimaryKey
    private Integer id;//编号
    private String types;//题目的类型
    private Integer correct;//提名都正确数量
    private String topic;//题目
    private String[] toOptions;//选项
    private String[] answers;// 答案(解析)
    //默认为 0 是因为，未被答题时候应该是 0
    private Long total = 0L;//该题被答的次数
    private Long numberErrors = 0L;//被答错的次数

    /**
     * 错误率
     */
    public double errorRate() {
        return 100.0 * numberErrors / total;
    }

    public String getAccuracy() {
        long result = Math.round(100 - this.errorRate());
        return result + "%";
    }

    public String getErrorRate() {
        long result = Math.round(this.errorRate());
        return result + "%";
    }

    public String getErrorRateToText(){
        if (this.numberErrors>70){
            return "(易错题)";
        }
        return "";
    };

    @Override
    public String toString() {
        return "ExerciseVo{" +
                "id=" + id +
                ", types='" + types + '\'' +
                ", correct=" + correct +
                ", topic='" + topic + '\'' +
                ", toOption='" + Arrays.toString(toOptions) + '\'' +
                ", answer='" + Arrays.toString(answers) + '\'' +
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

    public String[] getToOptions() {
        return toOptions;
    }

    public void setToOptions(String[] toOptions) {
        this.toOptions = toOptions;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
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
