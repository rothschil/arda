
![gradle](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/gradle.png)

## 1. 概述

`Pippin`, was a `Hobbit` of the Shire, and one of `Frodo Baggins'` youngest, but closest friends. He was a member of the Fellowship of the Ring and later became the thirty-second Thain of the Shire。 

`Gradle` 是一个基于`Apache Ant`和 `Apache Maven`概念的项目自动化构建工具，它使用`Groovy`语言来声明项目设置，抛弃了`XML`的各类繁琐配置。面向Java应用为主，当前支持的语言有`Java`、`Groovy`、`Kotlin`、`Scala`、`C++`、`Swift`、`JavaScript`，计划未来支持更多的语言。

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
|       +---gvy02       ------------------
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

- 灵活性：二者都提供约定优于配置。但 `Maven` 显得非常僵化，没法定制，一切都是 Maven给定的，不适用于许多自动化问题。另一方面，`Gradle` 是在考虑到授权和负责任的用户的情况下构建的。
- 性能： `Gradle` 特有的增量机制，所以相对来说缩短构建所需的时间
- 用户体验：`Gradle` 提供了一个基于 Web 的交互式 UI 用于调试和优化构建：构建扫描。这些也可以在本地托管，以允许组织收集构建历史并进行趋势分析、比较构建以进行调试或优化构建时间。
- 依赖管理：`Gradle` 允许生产者声明`api`和`implementation`依赖项，以防止不需要的库泄漏到使用者的类路径中。`Maven` 允许发布者通过可选的依赖项提供元数据。

具体在性能上，`Gradle` 具备三个明显特征：

- 增量性：`Gradle` 通过跟踪任务的输入和输出并只运行必要的内容来避免工作，并且只在可能的情况下处理更改的文件。
- 构建缓存：使用相同的输入重用任何其他 `Gradle` 构建的构建输出，包括在机器之间。
- `Gradle` 守护进程：一个长期存在的进程，它使构建信息在内存中保持“热”状态

![Gradle与Maven性能比较](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/20210907161922.png)

综述，`Maven` 更加标准，`Gradle` 更加简洁灵活。


## 5. 内容

### 5.1. 环境构建

#### 5.1.1. JDK、Maven、Nexus配置

不在本工程内容中，自行百度脑补。

#### 5.1.2. Gradle环境配置

![官网](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907173500.png)

- Windows下配置Gradle环境配置，需要先从  [官网](https://gradle.org/releases/)  下载
- 将下载的压缩文件解压到一个路径下，例如：`D:\ProgramFiles\Gradle\gradle-7.2`
- 配置环境变量 `GRADLE_HOME` ，值为 `D:\ProgramFiles\Gradle\gradle-7.2`
- 将  `GRADLE_HOME` 添加到 path
- 再添加环境变量 `GRADLE_USER_HOME` ，这个是用户空间，指向磁盘中一个文件夹即可，例如：`E:\Repertory\RepositoryGradle\.gradle` 这个很重要

![演示](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907173930.png)

#### 5.1.3. IDEA工具集成

![20210907170523](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907170523.png)

![20210907174102](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907174102.png)

 `IDEA `中的 `Gradle user home` 后的选项内容将是配置的 `GRADLE_USER_HOME` 环境变量。 这个点非常会让人忽视的。
 
`IDEA` 集成 `Gradle`需要安装插件，否则无法使用，插件不论你在线安装还是离线安装都可以，我使用的IDEA版本是 `2021.1.2`。

![20210907165919](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/20210907165919.png)

##### 5.1.3.1. IDEA配置Gradle

##### 5.1.3.2. 创建和认识Java工程项目

![New Project_1](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/20210907165803.png)

![New Project_2](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/20210907165727.png)

- Name：项目名称
- Location：所在的路径
- GroupId、ArtifactId、Version

这些都与Maven雷同。

![New Project_3](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907172055.png)

运行 `gradle build` 的输出结果

~~~cmd
17:21:31: Executing task 'build'...

> Task :compileJava NO-SOURCE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE
> Task :jar UP-TO-DATE
> Task :assemble UP-TO-DATE
> Task :compileTestJava NO-SOURCE
> Task :processTestResources NO-SOURCE
> Task :testClasses UP-TO-DATE
> Task :test NO-SOURCE
> Task :check UP-TO-DATE
> Task :build UP-TO-DATE

BUILD SUCCESSFUL in 131ms
1 actionable task: 1 up-to-date
17:21:31: Task execution finished 'build'.
~~~

![基本常用任务](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907172205.png)

- build.gradle

这个文件非常重要， `Gradle` 的核心配置文件，相当于 `Maven` 中的 `POM` ，此后工程开发都围绕着这个文件来编写。

~~~gradle
plugins { //插件
    id 'java'
}

group 'xyz.wongs.gradle'    // group 分组
version '1.0.0-SNAPSHOT'    // 版本

repositories {  // 仓库
    mavenCentral()
}

dependencies {  // 依赖
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}

test {  // 测试
    useJUnitPlatform()
}
~~~

- setting.gradle

这是针对模块的引入配置文件，可以在此管理每个模块激活状态，一般在添加新模块的过程中使用。

~~~gradle
rootProject.name = 'demo'
~~~

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

### 5.4. 进阶内容

#### 5.4.1. 多环境

目开发过程中，有开发环境、测试环境、生产环境，每个环境的配置也相同，与`Maven`项目类似，`Gradle`配置多环境用在环境属性文件和依赖配置 两个地方，实现可分为以下步骤：
- 通过约定规则，编写多环境信息
- 在 `build.gradle`文件中添加获取

~~~gradle
def env=System.getProperty('env')?:'dev'
~~~

- 最后在执行过程中添加参数：

~~~cmd
-D{属性名称}={内容}
~~~


#### 5.4.2. 插件

| 名称 | 描述 |
|--|--|
| Java插件 | 为Java提供编辑、测试和打包的能力 |
| java-library插件 | 构建本地依赖的插件 |
| maven-publish插件 | 项目发布到仓库的插件 |
| Springboot插件 | 提供Spring Boot支持，允许打包可执行jar或war |
| War插件 | 继承Java插件，为Web应用提供War包的功能 |
| IDEA插件 | 生成IDEA的工程文件，使得工程快速导入IDEA工程中 |
| Jetty插件 | 继承War插件，为应用程序添加三个主要任务，把应用部署到Jetty Web容器 |
| Application插件 | 创建可执行JVM应用，直接打成包含启动脚本Tar和Zip包 |
| CheckStyle插件 | 为工程添加CheckStyle质量检测 |
| FindBugs插件 | 为工程添加FindBugs质量检测 |
| PMD插件 | 为工程添加PMD质量检测 |
| JaCoCo插件 | 分析单元测试覆盖率的工具 |
| Sonar插件 | 分析检查代码质量 |

#### 5.4.3. 项目发布
