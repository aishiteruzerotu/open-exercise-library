package com.nf.web.controller;

import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.mvc.annotation.RequestParam;
import com.nf.service.ExerciseService;
import com.nf.vo.ExerciseVo;
import com.nf.vo.ResponseVO;

import static com.nf.mvc.handler.HandlerHelper.json;

@RequestController("/exercise")
public class ExerciseController {
    private  ExerciseService service ;

    /**
     * 随机获取一道题
     * @return JsonViewResult 数据对象
     */
    @RequestMapping("/exercise")
    public ViewResult getExercise(){

        return json(new ResponseVO(200,"查询成功",null));
    }

    @RequestMapping("/list/page")
    public ViewResult pagedList(@RequestParam("types") String types,
                                @RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "2") int pageSize) {

        return json(new ResponseVO(200, "ok", null));
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

    @RequestMapping("/delete")
    public ViewResult delete(@RequestParam("id") int id){

        return json(new ResponseVO(200,"删除成功",null));
    }

    @RequestMapping("/update")
    public ViewResult update(@RequestModel ExerciseVo exerciseVo){

        return json(new ResponseVO(200,"删除成功",null));
    }


}
