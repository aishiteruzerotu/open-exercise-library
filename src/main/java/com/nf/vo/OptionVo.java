package com.nf.vo;

public class OptionVo {
    private String option;//选项 如 A B C D
    private String optionValue;//选项值
    private boolean correct;//该选项是否正确
    private String answer;//该选项如果错误则赋值解析

    public OptionVo() {
    }

    public OptionVo(String optionValue, boolean correct, String answer) {
        this.optionValue = optionValue;
        this.correct = correct;
        this.answer = answer;
    }

    public OptionVo(String option, String optionValue, boolean correct, String answer) {
        this.option = option;
        this.optionValue = optionValue;
        this.correct = correct;
        this.answer = answer;
    }

    public boolean getChecked(){
        //前端确定该选项是没有被预先选择的
        return false;
    }

    public String getOptionText() {
        return option + "." + optionValue;
    }

    @Override
    public String toString() {
        return "OptionVo{" +
                "option='" + option + '\'' +
                ", optionValue='" + optionValue + '\'' +
                ", correct=" + correct +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
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
