package com.fishedee.util_boost.sample.api;

import com.fishedee.id_generator.IdGeneratorKey;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
@Getter
//忽略这句会报错
@IdGeneratorKey("user.user")
public class User {
    public enum Role{
        ADMIN,
        NORMAL,
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //忽略这句会报错
    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
}
