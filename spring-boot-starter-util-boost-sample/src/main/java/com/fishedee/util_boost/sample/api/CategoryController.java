package com.fishedee.util_boost.sample.api;

import com.fishedee.jpa_boost.CurdFilter;
import com.fishedee.jpa_boost.CurdFilterBuilder;
import com.fishedee.jpa_boost.CurdFilterable;
import com.fishedee.util_boost.web.CurdController;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/category")
public class CategoryController extends CurdController<Category,String,CategoryDTO,CategoryController.Filter> {
    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void init(){
        this.setCurdRepository(categoryRepository);
    }

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

    //GET请求 http://localhost:9191/category/get?data=%7B%22id%22%3A%22CT2021090100000001%22%7D

    //POST请求 http://localhost:9191/category/add
    //{"name":"fish","remark":"a"}

    //POST请求 http://localhost:9191/category/del
    //{"id":"CT2021090100000001"}
}
