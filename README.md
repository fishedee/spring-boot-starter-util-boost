![Release](https://jitpack.io/v/fishedee/spring-boot-starter-util-boost.svg)
(https://jitpack.io/#fishedee/spring-boot-starter-util-boost)

# util_boost

SpringBoost的Util工具库，功能就比较杂乱了，包括有：

* IocHelper，在非Bean的类上自动注入bean
* JsonConfiguration，做Json的默认配置工作
* IdGeneratorLinter，配合id-generator做code linter检查
* ValidatorUtil，在非Bean的类上做校验工作

## 安装

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.fishedee</groupId>
    <artifactId>spring-boot-starter-util-boost</artifactId>
    <version>1.0</version>
</dependency>
```

在项目的pom.xml加入以上配置即可

## 使用

代码在[这里](https://github.com/fishedee/spring-boot-starter-util-boost/tree/master/spring-boot-starter-util-boost-sample)

```ini
# 开启web-boost
spring.util-boost.enable=true
```

初始化配置

看sample代码，没啥好说的