package com.fishedee.util_boost.sample.api;

import com.fishedee.jpa_boost.CurdRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepository extends CurdRepository<Category,String> {
}
