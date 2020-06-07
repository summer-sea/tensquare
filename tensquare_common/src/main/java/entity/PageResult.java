package entity;

import java.util.List;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-20 14:13
 *
 * 分页对象
 **/

public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
