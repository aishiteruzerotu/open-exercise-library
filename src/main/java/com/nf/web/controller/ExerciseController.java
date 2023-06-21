package com.nf.web.controller;

import com.nf.Convert;
import com.nf.entity.Pagination;
import com.nf.entity.PaginationText;
import com.nf.mvc.ViewResult;
import com.nf.mvc.annotation.RequestController;
import com.nf.mvc.annotation.RequestMapping;
import com.nf.mvc.annotation.RequestModel;
import com.nf.mvc.annotation.RequestParam;
import com.nf.mvc.view.JsonViewResult;
import com.nf.service.ExerciseService;
import com.nf.service.impl.ExerciseServiceImpl;
import com.nf.vo.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    public ViewResult pagedList(@RequestModel PageVo pageVo) {

        GetExerciseVo getExerciseVo = Convert.toBean(GetExerciseVo.class, pageVo);
        getExerciseVo.setId(0);

        return this.getViewResult(getExerciseVo);
    }

    @RequestMapping("/get_exercise")
    public ViewResult getExercise(@RequestModel GetExerciseVo getExerciseVo) {
        return this.getViewResult(getExerciseVo);
    }

    @NotNull
    private JsonViewResult getViewResult(GetExerciseVo getExerciseVo) {
        String types = getExerciseVo.getTypes();
        boolean isNotNull = !(types == null || types.isEmpty());

        Long count = isNotNull ? service.typesCount(types) : service.count();

        Pagination pagination = new Pagination(getExerciseVo.getPageSize(), getExerciseVo.getPageNo(), count);
        List<ExerciseVo> pageExercises = isNotNull ? service.getTypesPageExercises(types, pagination) : service.getPageExercises(pagination);

        if (getExerciseVo.getId() != 0) {
            pageExercises = new ArrayList<>();

            ExerciseVo exerciseVo = isNotNull ?
                    service.getExercise(getExerciseVo.getId(), getExerciseVo.getTypes()) :
                    service.getExercise(getExerciseVo.getId());

            if (exerciseVo != null){
                pageExercises.add(exerciseVo);
                pagination = new Pagination(getExerciseVo.getPageSize(), getExerciseVo.getPageNo(), 1L);
            }
        }

        PagedVO<ExerciseVo> pagedVO = new PagedVO<>(new PaginationText(pagination), pageExercises);
        return json(new ResponseVO(200, "ok", pagedVO));
    }

    @RequestMapping("/add")
    public ViewResult insert(@RequestModel ExerciseVo exerciseVo) {
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

    @RequestMapping("/answered")
    public ViewResult answered(@RequestParam(value = "id", defaultValue = "0") int id,
                               @RequestParam(value = "isCorrectness", defaultValue = "true") boolean isCorrectness) {

        service.answered(id, isCorrectness);
        return json(new ResponseVO(200, "执行成功", true));
    }
}
