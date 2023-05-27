package com.nf.entity;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PaginationText {
    private final Pagination pagination;
    /**
     * 此参数确定首页附近的数字的数量，比如是个2的话，
     * 那么就应该1,2，如果值是3 --》1,2,3
     */
    private int firstCount = 2;
    private int lastCount = 2;
    private int prevCount = 2;
    private int nextCount = 2;
    private String skip = "...";

    public PaginationText(Pagination pagination) {
        this.pagination = pagination;
    }

    public PaginationText setFirstCount(int count) {
        this.firstCount = count;
        return this;
    }

    public PaginationText setLastCount(int count) {
        this.lastCount = count;
        return this;
    }

    public PaginationText setPrevCount(int count) {
        this.prevCount = count;
        return this;
    }

    public PaginationText setNextCount(int count) {
        this.nextCount = count;
        return this;
    }

    public PaginationText setSkip(String skipText) {
        this.skip = skipText;
        return this;
    }

    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 比如start = 5 ，count = 0--》一个数字都不生成
     * start=5 ,count = 1----> 5
     * start=5,count =2 ---> 5,6
     * start=5,count=3 ---->5,6,7
     *
     * @param start:表示从哪个数字开始生成数字,必须大于等于1
     * @param count：生成数字的总数量，大于等于0
     * @return IntStream 分页数据流
     */
    private IntStream generate(int start, int count) {
        return IntStream.range(start, start + count);//.collect(ArrayList::new, List::add, List::addAll)
    }

    /**
     * 生成分页流
     * @return 分页信息流
     */
    private Stream<Integer> getPagedNumbers() {
        IntStream listFirst = this.generate(1, firstCount);
        IntStream listPrev = this.generate(pagination.getPageNo() - prevCount + 1, prevCount);
        IntStream listNext = this.generate(pagination.getPageNo(), nextCount);
        IntStream listLast = this.generate(pagination.getLast() - lastCount + 1, lastCount);

        return Stream.of(listFirst, listPrev, listNext, listLast)
                .flatMap(IntStream::boxed)
//                .flatMapToInt(s->s) //转成 IntStream 流
                .filter(o -> o >= 1 && o <= pagination.getTotalPages())
                .distinct()
                .sorted();
    }

    /**
     * 获取分页信息
     * @return 分页信息
     */
    public List<String> getPagedText() {
        List<String> result = new ArrayList<>();
        Stream<Integer> numbers = this.getPagedNumbers();

        Optional<Integer> reduce = numbers.reduce((current, next) -> {
            result.add(current + "");
            if ((next - current) > 1) {
                result.add(this.skip);
            }
            return next;
        });

        result.add(reduce.orElse(pagination.getLast()) + "");
        return result;
    }

    /**
     * 获取省略符
     * @return 省略符
     */
    public String getSkip() {
        return skip;
    }

    public static void main(String[] args) {
        Pagination pagination = new Pagination(1, 10L);

        PaginationText paginationText = new PaginationText(pagination);
        List<String> pagedText = paginationText.getPagedText();
        for (String s : pagedText) {
            System.out.println("s = " + s);
        }
    }
}
