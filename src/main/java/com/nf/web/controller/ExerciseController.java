package com.nf.web.controller;

import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.service.ExerciseService;
import com.nf.vo.ExerciseVo;
import com.nf.vo.OptionVo;
import com.nf.vo.ResponseVO;

import static com.nf.mvc.handler.HandlerHelper.json;

@RequestController("/exercise")
public class ExerciseController {
    private  ExerciseService service ;

    @RequestMapping("/list")
    public ViewResult getExercises(){

        return json(new ResponseVO(200,"查询成功",null));
    }

    @RequestMapping("/add")
    public ViewResult insert(@RequestModel ExerciseVo exerciseVo){
//        exerciseVo.setOptions(new OptionVo[]{
//                new OptionVo(
//                        exerciseVo.getToOptions()[0],
//                        true,""
//                ),
//                new OptionVo(
//                        exerciseVo.getToOptions()[1],
//                        false,exerciseVo.getAnswers()[1]
//                )
//        });
        System.out.println("exerciseVo = " + exerciseVo);
        return json(new ResponseVO(200,"插入成功",exerciseVo));
    }
}
