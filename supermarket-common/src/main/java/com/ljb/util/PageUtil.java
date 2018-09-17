package com.ljb.util;


import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<?> rows;
    private Long offset;
    private Integer limit;


    public PageUtil(Page page){
		this.setRows(page.getContent());
		this.setTotal(page.getTotalElements());
		this.setOffset(page.getPageable().getOffset());
		this.setLimit(page.getSize());
	}

	public PageUtil(List<?> list,Long total,Long offset,Integer limit){
		this.setRows(list);
		this.setTotal(total);
		this.setOffset(offset);
		this.setLimit(limit);
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}


	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getTotal() {
		return total;
	}
}
