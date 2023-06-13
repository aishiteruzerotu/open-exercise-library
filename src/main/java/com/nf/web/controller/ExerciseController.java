package com.nf.web.controller;

import com.nf.entity.Pagination;
import com.nf.entity.PaginationText;
import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.mvc.annotation.RequestParam;
import com.nf.service.ExerciseService;
import com.nf.service.impl.ExerciseServiceImpl;
import com.nf.vo.ExerciseVo;
import com.nf.vo.PagedVO;
import com.nf.vo.ResponseVO;

import java.util.List;
import java.util.Optional;

import static com.nf.mvc.handler.HandlerHelper.json;

@RequestController("/exercise")
public class ExerciseController {
    private final ExerciseService service = new ExerciseServiceImpl();

    /**
     * 随机获取一道题
     *
     * @return JsonViewResult 数据对象
     */
    @RequestMapping("/exercise")
    public ViewResult getExercise() {

        return json(new ResponseVO(200, "ok", service.getExercise()));
    }

    @RequestMapping("/list/page")
    public ViewResult pagedList(@RequestParam(defaultValue = "") String types,
                                @RequestParam(defaultValue = "1") int pageNo,
                                @RequestParam(defaultValue = "2") int pageSize) {

        boolean isNotNull = types==null && types.isEmpty();

        Long count = isNotNull ? service.typesCount(types) : service.count();

        Pagination pagination = new Pagination(pageSize, pageNo, count);
        List<ExerciseVo> pageExercises = isNotNull ? service.getTypesPageExercises(types, pagination) : service.getPageExercises(pagination);
        
        PagedVO<ExerciseVo> pagedVO = new PagedVO<>(new PaginationText(pagination), pageExercises);
        return json(new ResponseVO(200, "ok", pagedVO));
    }

    @RequestMapping("/add")
    public ViewResult insert(@RequestModel ExerciseVo exerciseVo) {
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
        return service.insert(exerciseVo) ?
                json(new ResponseVO(200, "添加成功", true)) :
                json(new ResponseVO(500, "添加失败", false));
    }

    @RequestMapping("/delete")
    public ViewResult delete(@RequestParam("id") int id) {

        return service.delete(id) ?
                json(new ResponseVO(200, "删除成功", true)) :
                json(new ResponseVO(500, "删除失败", false));
    }

    @RequestMapping("/update")
    public ViewResult update(@RequestModel ExerciseVo exerciseVo) {

        return service.update(exerciseVo) ?
                json(new ResponseVO(200, "修改成功", true)) :
                json(new ResponseVO(500, "修改失败", false));
    }


}
