package com.fishedee.util_boost.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fishedee.id_generator.IdGenerator;
import com.fishedee.util_boost.ioc.IocHelper;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fish on 2021/4/29.
 */
@MappedSuperclass
@Getter
@ToString
public class BaseEntityType {
    //由JPA能生成时间戳，这样对不同数据库兼容性最好，而且不会产生再一次的select操作
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modifyTime;

    @Transient
    @JsonIgnore
    @Autowired
    protected IdGenerator idGenerator;

    {
        IocHelper.autoInject(this);
    }
}
