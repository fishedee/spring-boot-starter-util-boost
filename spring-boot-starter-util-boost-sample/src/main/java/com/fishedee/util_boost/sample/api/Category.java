package com.fishedee.util_boost.sample.api;

import com.fishedee.id_generator.IdGeneratorKey;
import com.fishedee.util_boost.utils.BaseEntityType;
import com.fishedee.util_boost.utils.ValidatorUtil;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@IdGeneratorKey("category.category")
@Slf4j
public class Category extends BaseEntityType {
    @Id
    private String id;

    private String name;

    private String remark;

    protected Category(){

    }

    public Category(CategoryDTO dto){
        ValidatorUtil.check(dto);
        this.update(dto);
        this.id = idGenerator.next(this);
    }

    private void update(CategoryDTO dto){
        this.name = dto.getName();
        this.remark = dto.getRemark();
    }

    public void mod(CategoryDTO dto){
        ValidatorUtil.check(dto);
        this.update(dto);
    }

}
