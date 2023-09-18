package com.fishedee.util_boost.sample.api;

import com.fishedee.jpa_boost.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QueryRepository queryRepository;

    @Data
    public static class Filter implements CurdFilterable {
        private String remark;

        private String name;

        @Override
        public CurdFilter getFilter(){
            CurdFilterBuilder builder = new CurdFilterBuilder();
            if( remark != null){
                builder.like("remark","%"+remark+"%");
            }
            if( name != null){
                builder.like("name","%"+name+"%");
            }
            return builder;
        }
    }

    //GET请求 http://localhost:9191/category/search?data=%7B%22pageIndex%22%3A0%2C%22pageSize%22%3A10%7D
    @GetMapping("search")
    public Page<List<Category>> search(Filter filter, PageDTO dto){
        return this.queryRepository.findByFilter(Category.class,filter,dto.getPageable().withCount().withSort("createTime desc"));
    }

    //GET请求 http://localhost:9191/category/get?data=%7B%22id%22%3A%22CT2021090100000001%22%7D
    @GetMapping("get")
    public Category get(String id){
        return this.categoryRepository.get(id);
    }

    //POST请求 http://localhost:9191/category/add
    //{"name":"fish","remark":"a"}
    @PostMapping("add")
    @Transactional
    public void add(CategoryDTO dto){
        Category category = new Category(dto);
        this.categoryRepository.add(category);
    }

    //POST请求 http://localhost:9191/category/del
    //{"id":"CT2021090100000001"}
    @PostMapping("del")
    @Transactional
    public void del(String id){
        Category category = this.categoryRepository.get(id);
        this.categoryRepository.del(category);
    }

    @PostMapping("mod")
    @Transactional
    public void mod(String id,CategoryDTO dto){
        Category category = this.categoryRepository.get(id);
        category.mod(dto);
    }
}
