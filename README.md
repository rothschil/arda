
![gradle](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/gradle.png)

## 1. 概述

   皮瑞格林·图克，他的朋友称他为皮平，是托尔金魔幻小说的角色，霍比特人。他是佛罗多·巴金斯最年轻和亲爱的朋友。 皮瑞格林是帕拉丁二世·图克及其妻爱格拉庭‧河岸的唯一儿子，所以帕拉丁图克二世于第四世纪13年去世后，皮瑞格林继承了夏尔主帅的职位。他有三个姐姐：波尔·图克、平珀诺·图克及波纹卡·图克。

<div style="text-align: center;">

![皮平](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/Pippinprintscreen.jpg)

</div>

 本工程主要是针对 `Gradle`的学习入门类项目，也是借助于托尔金指环王的人物命名的工程，在学习本工程之前，对学习者有如下要求：

- 面向`Java`语言的项目开发者，至少一年半以上开发类的经验
- 掌握 `Maven`在项目开发中的基本运用，具体如下：
    - 熟练掌握 `Nexus` 私服，创建和维护私服
    - 掌握 `Maven`中环境构建、依赖管理、编译、打包、部署，并且可以基本运用
    - 了解 `Maven`中对插件的运用，如`maven-compiler-plugin`、`maven-resources-plugin`等
    - 了解 `Maven`中各类仓库的概念，中央仓库、本地仓库
- 了解项目中常用的版本命名规则

如果不满足上述要求，建议先去补充 `Maven`有关的知识，因为 `Gradle`和 `Maven`还是有很多类似的地方。

通过本工程的学习，会掌握 `Gradle`常见用法以及利用 `Groovy`编写任务来完成自身项目开发需要，当然本工程集成`SpringBoot`。也可以用来当作 `Gradle`项目改造的模板来使用。

## 2. 友情关联

[Gitee链接：https://gitee.com/rothschil/pippin](https://gitee.com/rothschil/pippin)

[Github链接：https://github.com/rothschil/pippin.git](https://github.com/rothschil/pippin.git)

## 3. 目录结构

~~~
|---.gradle
|---gradle              ------------------Gradle配置文件
│  └─wrapper
│          gradle-wrapper.jar
│          gradle-wrapper.properties    --Gradle当前配置内容
|---groovy-demo         ------------------Groovy基本语法
|   \---src
|       +---gvy01       ------------------
|       \---gvy02       ------------------
|---pippin-common       ------------------通用模块部分
|   \---common-utils    ------------------常用工具包，集成通用基类，供其他模块引入
│      │  build.gradle  ------------------工具包的构建文件
│      │  dependencies.gradle   ----------配置
|---pippin-service      ------------------样例服务，这里用的一个JWT做个演示
|    └─src
|       ├─main
|       │  └─resources
|       │     ├─application.yml
|       │       ├─config.properties
|       │  ├─config
|       │  │  ├─dev
|       │  │  │ ├─application.prperties ----开发环境配置
|       │  │  │      
|       │  │  └─prod
|       │  │    ├─application.prperties ----生产环境配置
|   │  build.gradle     ------------------构建文件
|   │  dependencies-dev.gradle  ----------开发环境配置
|   │  dependencies-prod.gradle ----------生产环境配置
|   │  dependencies-test.gradle ----------测试环境配置
│  .gitignore           ------------------配置git忽略索要文件
│  build.gradle         ------------------根目录的构建核心文件
│  gradle.properties    ------------------根目录的属性文件，这是固定命名
│  gradlew              
│  gradlew.bat
│  README.md            ------------------项目描述
│  settings.gradle      ------------------Gradle模块 配置文件

~~~

## 4. Maven与Gradle比较

## 5. 内容

### 5.1. 环境构建

#### 5.1.1. JDK、Maven、Nexus配置

不在本工程内容中，自行百度补充。

#### 5.1.2. Gradle环境配置

Windows下配置Gradle环境配置

#### 5.1.3. IDEA工具集成

##### 5.1.3.1. IDEA配置Gradle

##### 5.1.3.2. 创建Java工程项目

build.gradle、setting.gradle

### 5.2. Groovy

#### 5.2.1. 环境构建

#### 5.2.2. 语法结构

### 5.3. Gradle详解

#### 5.3.1. 任务创建、属性、继承
#### 5.3.2. 任务依赖
#### 5.3.3. 文件处理
#### 5.3.4. 日志处理
#### 5.3.5. 项目打包
jar、source.jar、doc.jar

#### 5.3.6. 常用命令
