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
│          gradle-wrapper.jar           --gradle-wrapper 主题功能
│          gradle-wrapper.properties    --Gradle当前配置的版本，以及从哪里获取
|---groovy-demo         ------------------Groovy基本语法
|   \---src
|       +---gvy01       ------------------
|       +---gvy03       ------------------
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
│  gradlew.bat          ------------------Gradle Wrapper
│  LICENSE              ------------------开源授权协议
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

## 5. 环境构建

### 5.1. JDK、Maven、Nexus版本要求

本工程运行环境对应的版本 `JDK`版本在 `1.8`、`Maven`版本 `3.6`、`Nexus 3.34+ ` 三者的安装不在本工程内容中，自行百度脑补。

### 5.2. Gradle环境配置

![官网](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907173500.png)

- Windows下配置Gradle环境配置，需要先从  [官网](https://gradle.org/releases/)  下载
- 将下载的压缩文件解压到一个路径下，例如：`D:\ProgramFiles\Gradle\gradle-7.2`
- 配置环境变量 `GRADLE_HOME` ，值为 `D:\ProgramFiles\Gradle\gradle-7.2`
- 将  `GRADLE_HOME` 添加到 path
- 再添加环境变量 `GRADLE_USER_HOME` ，这个是用户空间，指向磁盘中一个文件夹即可，例如：`E:\Repertory\RepositoryGradle\.gradle` 这个很重要
- `GRADLE_USER_HOME` 目录下我们新建两个文件，`gradle.properties`、`init.gradle`

#### 5.2.1. gradle.properties

`Gradle` 配置项，主要为了开启多线程模式，`Gradle` 运行时候效率更快一点

~~~properties
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.caching=true
~~~

#### 5.2.2. init.gradle

`Gradle` 全局配置，主要定义定义仓库

~~~gradle

allprojects{
   
    repositories {
   
        def ALIYUN_REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/groups/public'
        def ALIYUN_JCENTER_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
        all {
            ArtifactRepository repo ->
            if(repo instanceof MavenArtifactRepository){
   
                def url = repo.url.toString()
                if (url.startsWith('https://repo1.maven.org/maven2')) {
   
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                    remove repo
                }
                if (url.startsWith('https://jcenter.bintray.com/')) {
   
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                    remove repo
                }
            }
        }
        maven {
            allowInsecureProtocol = true
            url ALIYUN_REPOSITORY_URL
            url ALIYUN_JCENTER_URL
        }
    }
}
~~~

![演示](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907173930.png)

### 5.3. IDEA工具集成

我使用的IDEA版本是 `2021.1.2`。

![20210907170523](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907170523.png)

#### 5.3.1. IDEA配置Gradle

`IDEA` 集成 `Gradle`需要安装插件，否则无法使用，插件不论你在线安装还是离线安装都可以。

![20210907165919](https://abram.oss-cn-shanghai.aliyuncs.com/blog/sctel/20210907165919.png)

![20210907174102](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210907174102.png)

 `IDEA `中的 `Gradle user home` 后的选项内容将是配置的 `GRADLE_USER_HOME` 环境变量。 这个点非常会让人忽视的。

 #### 5.1.3.2. 创建和认识Java工程项目

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

上图中罗列的基本任务，可以先认识下，下来重点描述下这两个文件 `build.gradle`、`setting.gradle`，这是我们`Gradle`工程开发最核心的两个文件。

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

### 5.4. Gradle Wrapper

在 `Maven`中，开发者和项目服务器都需要配置有相同的 `Maven`版本，才能保证应用正常编译执行，`Gradle`提供一个 `Gradle Wrapper`概念，可以项目中自带Gradle的处理环境，`Gradle Wrapper`简化 `Gradle`本身的安装、部署，避免 `Gradle`版本不通带来的各种问题。

在任意目录中 执行 `gradle wrapper`

![20210908092359](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908092359.png)

切换版本

| 操作 | 描述 |
|--|--|
| 切换 6.8 版本 | gradle wrapper --gradle-version 6.8 |
| 切换 6.5 版本 | gradle wrapper --gradle-version 6.5 |

![20210908093052](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908093052.png)

执行上述命令， `gradle-wrapper.properties` 中 `distributionUrl` 后的 URL 变为 `https\://services.gradle.org/distributions/gradle-6.8-bin.zip` ，版本切换成功。

![20210908093313](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908093313.png)

### 5.5. 代码分析器

所有开发人员编写的代码，在程序上线之前都一定要进行性能的检测，而对于 `Gradle` 项目来讲，也需要清楚的知道，项目代码构建的情况，包括构建环境、依赖库以及程序性能上存在的问题，这样方便与人沟通。为了解决这样的构建问题，所以在 `Gradle` 中就提供了一个 **Build Scan** 代码扫描工具， 这个工具会自动的将本地构建的过程发送到指定服务器端，并且由服务器端上生成一个完整的报告。

这个分析器是 `gradle wrapper` 提供的

~~~cmd
gradlew build --scan
~~~

![20210908093948](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908093948.png)

输入邮箱，在邮箱中确认

![首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908094121.png)

![邮箱确认](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908094306.png)

![报告内容](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908094417.png)

## 6. Groovy

`Apache` 的`Groovy`是`Java`平台上设计的面向对象编程语言。运行于`Java`虚拟机上的`Java`字节码，并与其他`Java`代码和库进行互操作。由于其运行在JVM上的特性，Groovy可以使用其他Java语言编写的库。`Groovy`的语法与`Java`非常相似，大多数`Java`代码也符合`Groovy`的语法规则。

![20210908095332](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908095332.png)

### 6.1. 环境构建

官网下载 `Groovy`，当前最新版本 **3.0.7**， `Groovy`是一个压缩的工具包，对公工具包解压，并讲 `Groovy`下的bin路径，配置到本地操作系统环境 `path` 属性。

![20210908095718](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908095718.png)

![20210908095612](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908095612.png)

#### 6.1.1. groovysh

`Groovy` 提供一个交互式编程，`groovysh`

![20210908095756](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908095756.png)

#### 6.1.2. IDEA创建Groovy

这里在 `Gradle`项目中新建 `Groovy` 项目 模块， `Groovy libray` 选择 `Groovy` 的文件路径

![20210908100620](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908100620.png)

![20210908100716](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908100716.png)

- 创建包名为 `xyz.wongs.groovy`
- 编写脚本，这里要选择 `Groovy Script`

![20210908100908](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908100908.png)

编写我们第一个 `Groovy`代码，`Groovy` 与`Python`非常类似。

~~~groovy
package xyz.wongs.groovy

// python 3的语法结构
println('Hello Groovy')
~~~

![20210908101202](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908101202.png)

### 6.2. 语法结构

#### 6.2.1. 数据类型

![20210908104458](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210908104458.png)

#### 6.2.2. 数组

`Groovy` 数组由 `Java`中 `java.util.Arrays` 工具提供的，所有`Java`中对数组的操作，在Groov y中同样适用

![20210909152949](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909152949.png)

#### 6.2.3. 列表

列表是用于存储数据项集合的结构。在 `Groovy` 中，`List` 保存了一系列对象引用。List 中的对象引用占据序列中的位置，并通过整数索引来区分。列表文字表示为一系列用逗号分隔并用方括号括起来的对象。groovy 列表使用索引操作符 [] 索引。列表索引从 0 开始，指第一个元素。groovy 中的一个列表中的数据可以是任意类型。这` java` 下集合列表有些不同，java 下的列表是同种类型的数据集合。

![常用方法](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909153004.png)

~~~groovy
package gvy02

// 列表

def list = [1,'2',23]
println('【列表输出】'+list)
println('【列表转字符】'+list.toString())
println('【列表长度】'+list.last())

~~~

#### 6.2.4. 散列

也称为关联数组、映射、表，是对象引用的无序集合，类似Java中 Map，格式如： ['Key1'：'Sam'，'Key2'：'Abram'] 

![20210909153133](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909153133.png)

~~~groovy
package gvy02

// 字典 Map

def map = ['key1':'Grovy','key2':'Java']
println('【Map输出】'+map)
println('【Map转字符】'+map.toString())
println('【Map元素个数为】'+map.size())

for (String s:map.keySet()){
    println('【遍历】'+'Key='+s+' Value = '+map.get(s))
}

~~~

#### 6.2.5. 多线程

`Java`开发过程中通过`J.U.C`工具定义多线程，`Groovy`参考`Python`脚本并且对`Java`的`java.lang.Thread`进行扩展，简化了多线程的定义。 `Groovy`线程利用闭包进行定义的居多，闭包的方式主要有 `Thread.start` 和 `Thread.startDaemon`。当然`Groovy`中也有定时任务，分别是`Timer` 和 `TimerTask`，两种方式。
综述， `Groovy`的多线程开发， 仅仅是进行`Thread`结构的变化，其他结构均按照`Java`程序的方式定义。

##### 6.2.5.1. 定义

~~~groovy

package gvy05

/**
 * 1、多线程的定义
 */

println('【主线程】' + Thread.currentThread().getName())

// 1.1 start 模式
Thread.start {
    println('【子线程-start】' + Thread.currentThread().getName())
    'Groovy Project :https://gitee.com/rothschil/pippin'.each {
        print(it)
    }
    println('\n')
}

println('https://www.github.com/rothschil/pippin')

// 1.2 startDaemon 模式
Thread.startDaemon {
    println('【子线程-startDaemon】' + Thread.currentThread().getName())
    'Groovy Project :https://gitee.com/rothschil/'.each {
        print(it)
    }
    println('\n')
}

~~~

##### 6.2.5.2. 线程同步

~~~groovy
def ticket =10

def sale ={->
    synchronized (this){
        sleep(100)
        println('[Buy tickets]'+Thread.currentThread().getName()+ ' The remaining votes ' +(ticket--))
    }
}

for(x in 1..10){
    def st = Thread.start(sale)
    st.name='TicketSeller -'+x
}
~~~

##### 6.2.5.3. 定时任务

~~~groovy
package gvy05

// 定时任务
new Timer('[Timer]').runAfter(1000) {
    for (int i = 0; i < 10; i++) {
        println('【定时任务】' + Thread.currentThread().getName() + ' Groovy Project :https://gitee.com/rothschil/pippin')
    }
}
~~~

##### 6.2.5.4. J.U.C操作

~~~groovy

package gvy05

import java.util.concurrent.CountDownLatch

// JUC 定义两个线程，让线程1 必须要等待线程2 执行完毕后 才能执行
CountDownLatch countDownLatch = new CountDownLatch(1)

def firstThread = Thread.start {
    countDownLatch.await()
    println('[The First Thread] ' + Thread.currentThread().getName())
}

def secondThread = Thread.start {
    sleep(1000)
    println('[The Second Thread] ' + Thread.currentThread().getName())
    countDownLatch.countDown()
}
// 第一个线程先启动
firstThread
secondThread

~~~

##### 6.2.5.5. 线程池

~~~groovy
package gvy05

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

for (x in 1..20) {
    es.submit(() -> {
        println('[ThreadPool]' + Thread.currentThread().getName())
    })
}
~~~

#### 6.2.6. 更改类型

使用 def关键字定义的所有变量类型都是可以动态更改，这和Python类似，使用起来比较灵活。

~~~groovy
package gvy01

def msg = 'Groovy'
println("【1】 msg 对应的类型是 "+ msg.class)

msg = 3
println("【2】 msg 对应的类型是 "+ msg.class)

msg = 1.3
println("【3】 msg 对应的类型是 "+ msg.class)

~~~

![20210909155259](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909155259.png)

#### 6.2.7. 数据溢出

~~~groovy
package gvy01


def ix1 = Integer.MAX_VALUE+1

println("【1】 ix1 对应的类型是 "+ ix1.class +" 变量内容："+ix1)

def ix2 =  21474836499

println("【2】 ix2 对应的类型是 "+ ix2.class +" 变量内容："+ix2)

def ix3 =  2342546543634532424324233

println("【3】 ix3 对应的类型是 "+ ix3.class +" 变量内容："+ix3)
~~~

![20210909155415](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909155415.png)

#### 6.2.8. 异常

和Java一样的异常处理方式

- 检测异常：在编译时检查异常
- 未经检查的异常：未检查的异常在编译期不检查，而是在运行时检查，如ArrayIndexOutOfBoundsException
- 错误：程序永远不能恢复的错误，将导致程序崩溃，如OutOfMemoryError，VirtualMachineError，AssertionError

![20210909155510](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909155510.png)

## 7. Gradle详解

通过上面对Groovy语法的介绍，这一章节，通过以Groovy 来编写Gradle任务。

### 7.1. 任务创建

Gradle 中任务创建有四种方式，日常使用中比较多的是采用闭包的方式。

~~~gradle
// 1、原型创建
def task1 = task(task1)
task1.doLast {
    println '【1、原型创建】最后执行'
}

// 2、任务属性创建
def task2 = task([description: ' Is My Task2', group: 'xyz.wongs'],task2)
task2.doLast {
    println '【2、任务属性创建】最后执行'
}

// 3、闭包机制
task task3 {
    description(' Is My Task3')
    group('xyz.wongs')
    doLast {
        println '【3、闭包机制】最后执行'
    }
}

// 4、任务容器 TaskContainer
tasks.create('task4') {
    description(' Is My Task4')
    group('xyz.wongs')

    doLast {
        println '【4、任务容器 TaskContainer】最后执行'
    }
}
~~~

### 7.2. 属性

属性是Task在定义中说明的，在Task类中，我们可以看到提供这些属性，都可以通过外部访问。

![20210909160048](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909160048.png)

![20210909155934](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909155934.png)

~~~gradle
task taskPro {
    description('Properties Demo')
    group('xyz.wongs.gradle')
    doLast {
        println '【1、Propertie】属性设置'
    }
}
~~~


### 7.3. 继承

整个Gradle中的任务都可以被继承，只需要定义一个父类的任务，在其中编写自己的业务，同时再继承 DefaultTask 即可。

~~~gradle
// 二、继承
// 定义Java程序类
class CustomerTask extends DefaultTask {

    // TaskAction 任务执行注解
    @TaskAction
    def doSelf() {
        // 实现的主体
        println '【3、Extends-doSelf】 Operation Body'
    }

    @TaskAction
    def doLast() {
        println '【3、Extends-doLast】 doLast Operation'
    }
}

task taskExtends(type: CustomerTask) {
    description('Extends Demo')
    doFirst {
        println '【3、Extends】继承'
    }

    doLast {
        println '【3、Extends】继承'
    }
}
~~~

![20210909161301](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909161301.png)

### 7.4. 任务依赖

任务之间的依赖关系，为了方便控制任务执行过程中的优先级，如同我们Maven中，在运行jar任务之前，complie任务一定要执行过，也就是jar依赖于compile，在Gradle中，通过DependsOn控制依赖关系，DependsOn 是Task类的一个方法，可以接受多个依赖的任务作为参数

![20210909161417](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909161417.png)

~~~gradle

// 任务依赖

task taskDependsOnC() {
    group("xyz.wongs.gradle")
    doFirst {
        println '【5、DependsOnC】 doFirst'
    }
}

task taskDependsOnD() {
    // 任务禁用
    enabled = true
    group("xyz.wongs.gradle")
    doFirst {
        println '【5、DependsOnD】 doFirst'
    }
}

task taskDependsOnB(dependsOn: [taskDependsOnD, taskDependsOnC]) {
    group("xyz.wongs.gradle")
    doFirst {
        println '【5、DependsOnB】 doFirst'
    }
}

task taskDependsOnA(dependsOn: [taskDependsOnB]) {
    group("xyz.wongs.gradle")
    doFirst {
        println '【5、DependsOnA】 doFirst'
    }
}
~~~

### 7.5. 文件处理

编写多任务处理过程中需要用到对各类资源文件的控制，涉及到Gradle对文件操作，常用的对文件操作主要有：
- 本地文件：指定文件的相对路径或绝对路径来对文件的操作
- 文件集合：对一组文件的列表进行操作，但文件集合中的文件对象是延迟，任务调用才会创建
- 文件树：有层级结构的文件集合，一个文件树它可以代表一个目录结构或一 ZIP 压缩包中的内容结构。文件树是从文件集合继承过来的，所以文件树具有文件集合所有的功能。
- 文件拷贝：可以使用Copy任务来拷贝文件，通过它可以过虑指定拷贝内容，还能对文件进行重命名操作等。Copy任务必须指定一组需要拷贝的文件和拷贝到的目录
- 归档文件：通常一个项目会有很多的 Jar 包，将项目打包成一个 WAR、ZIP、TAR 包进行发布，可以使用Zip，Tar，Jar，War和Ear任务来实现

#### 7.5.1. 本地文件

~~~gradle
// 1、本地文件
def Task taskLocalFile = task(taskLocalFile) {
    doFirst {
        // 使用相对路径
        File configFile = file('src/main/resources/config.xml')
        configFile.createNewFile();

        def _path = 'D:/Code/98_Github/Gradle/grad/src/main/resources/config.xml'
        // 使用绝对路径
        configFile = file(_path)
        println('【1、本地文件  使用绝对路径】' + configFile.absolutePath)

        // 使用一个文件对象
        configFile = file(new File(_path))
        println('【1、本地文件  使用文件对象】' + configFile.exists())
    }
}
~~~

#### 7.5.2. 文件集合

~~~gradle
// 2、文件集合
def Task taskCollectionFile = task(taskCollectionFile) {
    doFirst {
        File _fileList = file('gvoovy-demo/src/gvy01')
        def FileCollection collection = files(_fileList.listFiles())    // 获取文件列表
        collection.each { println(it) }   // 遍历文件列表
        Set _set1 = collection.files        // 文件集合转为 Set
        Set _set2 = collection as Set
        List _list = collection as List     // 文件集合转为 List
        String _path = collection.asPath    // 文件集合转为 String
    }
}
~~~

#### 7.5.3. 文件树

~~~gradle
// 3、文件树
def Task taskFileTree = task(taskFileTree) {
    doFirst {
        FileTree _fileTree = fileTree('gvoovy-demo/src/gvy01')  // 1、指定目录创建文件树对象
        _fileTree.include '**/*.groovy'     // 添加包含指定文件
        _fileTree.exclude '**/del*'         // 添加排除指定文件
        // 2、使用路径创建文件树对象，同时指定包含的文件
        _fileTree = fileTree('gvy/src').include('**/*.groovy')

        _fileTree = fileTree('gvy/src') {   // 3、通过闭包创建文件树
            include '**/*.groovy'
        }

        _fileTree = fileTree(dir: 'gvoovy-demo/src/gvy01', include: '**/*.groovy')  // 通过map创建文件树
        _fileTree = fileTree(dir: 'gvoovy-demo/src/gvy01', includes: ['**/*.groovy', '**/*.xml'])
        _fileTree = fileTree(dir: 'gvoovy-demo/src/gvy01', include: '**/*.groovy', exclude: '**/*del*/**')

        _fileTree.each { File file ->        // 遍历文件树的所有文件
            println(file)
        }
    }
}
~~~

#### 7.5.4. 文件拷贝

~~~gradle
// 4、文件拷贝
task taskFileCopy(type: Copy) {
    from 'src/main/resources/config.log'
    // 从Zip压缩文件中拷贝内容
    from zipTree('src/main/resources/00.zip')
    into 'src/main/java/'
    // 添加过虑条件来指定包含或排除的文件
    include '**/*.png'
}
~~~

### 7.6. 依赖库范围

![20210909161903](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909161903.png)

### 7.7. 日志处理

项目开发过程中，所有重要数据内容都需要通过日志的形式输出，Gradle内部提供各种日志组件。

![20210909162056](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909162056.png)

### 7.8. 项目打包

一个完整的项目打包过程中，会包含三个 `*.jar` 文件：主程序 `projectName-version.jar`、源代码 `projectName-version-sources.jar`、文档 `projectName-version-javadoc.jar`，所以要想在 `Gradle`中生成上述文档，需要在项目定义相关的任务处理来完成这三个文件的输出。

~~~gradle
apply plugin: 'java'
group 'xyz.wongs.gradle'
version '1.0-SNAPSHOT'
def jdkVersion = 1.8
sourceCompatibility = jdkVersion
targetCompatibility = jdkVersion
repositories { mavenCentral() }
dependencies { compile('org.apache.poi:poi:5.0.0') }
test { useJUnitPlatform() }
//程序主类名称
def mainClass = 'xyz/wongs/gradle/GradleStart.java'
jar {
// Jar文件名称
    archiveBaseName = 'grad'
    manifestContentCharset = 'UTF-8'
    //文件编码
    metadataCharset('UTF-8')
    //内容编码
    manifest {
        //程序主版本号
        attributes 'Manifest-Version': getArchiveVersion().getOrNull(),
                'Main-Class': "$mainClass",
                'Implementation-Titile': 'grad-Tiltle',
                'Implementation-Version': archiveVersion     //版本编号
        // 所有第三方包放置到 lib文件夹下
    }
    into('lib') {
        //运行时组件
        from configurations.runtime
    }
}

~~~

### 7.9. 常用命令

![20210909162804](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909162804.png)

## 8. 进阶内容

### 8.1. 多环境

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

### 8.2. 插件

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

### 8.3. 项目发布

项目发布我们使用插件 `maven-publish`，将构建内容发布到`Apache Maven`存储库的功能。可以被 `Maven`、`Gradle` 和其他了解 `Maven` 存储库格式的工具使用

~~~gradle
apply plugin: 'maven-publish'
~~~

![20210909163335](https://abram.oss-cn-shanghai.aliyuncs.com/blog/gradle/20210909163335.png)