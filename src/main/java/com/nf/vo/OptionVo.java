package com.nf.vo;

public class OptionVo {
    private String option;//选项 如 A B C D
    private boolean correct;//该选项是否正确
    private String answer;//该选项如果错误则赋值解析

    public OptionVo() {
    }

    public OptionVo(String option, boolean correct, String answer) {
        this.option = option;
        this.correct = correct;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "OptionVo{" +
                "option='" + option + '\'' +
                ", correct=" + correct +
                '}';
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
