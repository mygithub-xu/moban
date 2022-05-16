package com.dhlg.utils.Parameter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;



@Data
public class QueryEntity<T> {
    /**
     * 当前页
     */
    private Integer number = 1;
    /**
     * 当前页数据量大小
     */
    private Integer size = 10;

    private T condition;

    public Page getPage() {
        return new Page(this.number, this.size);
    }
}
