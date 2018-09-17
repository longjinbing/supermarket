package com.ljb.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询参数
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-14 23:15
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private Pageable pageable;

    private Sort sort;

    private Specification<T> specification;

    //当前页码
    private int offset;
    //每页条数
    private int limit = 10;

    private int pageNum;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.offset = params.containsKey("offset") ? Integer.parseInt(params.get("offset").toString()) : 0;
        this.limit = params.containsKey("limit") ? Integer.parseInt(params.get("limit").toString()) : 10;
        this.pageNum = offset / limit;
        this.put("page", (offset / limit));
        this.put("offset", offset);
        this.put("limit", limit);
        final String field = params.containsKey("searchField ") ? params.get("searchField ").toString() : "";
        final String text = params.containsKey("searchText") ? params.get("searchText").toString() : "";
        String sortField = params.containsKey("sort") ? params.get("sort").toString() : "";
        String order = params.containsKey("order") ? params.get("order").toString() : "";
        final String startTime = params.containsKey("startTime") ? params.get("startTime").toString() : "";
        final String endTime = params.containsKey("endTime") ? params.get("endTime").toString() : "";
        specification = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (!field.isEmpty()) {
                    query.where(criteriaBuilder.like(root.get(field), "%" + text + "%"));
                }
                if(!startTime.isEmpty()&&endTime.isEmpty()){
                        query.where(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"),Date.valueOf( startTime)));
                }
                if(startTime.isEmpty()&&!endTime.isEmpty()){
                    query.where(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), Date.valueOf(endTime)));
                }
                if (!startTime.isEmpty()&&!endTime.isEmpty()) {
                        query.where(criteriaBuilder.between(root.get("createTime"), Date.valueOf(startTime), Date.valueOf(endTime)));
                }
                return null;
            }
        };
        if (!sortField.isEmpty()) {
            sort = new Sort(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        } else {
            sort = new Sort(Sort.Direction.DESC, "id");
        }
        pageable = PageRequest.of(getPage(), getLimit(), getSort());
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public int getPage() {
        return pageNum;
    }

    public void setPage(int page) {
        this.pageNum = page;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Specification<T> getSpecification() {
        return specification;
    }

    public void setSpecification(Specification<T> specification) {
        this.specification = specification;
    }
}
