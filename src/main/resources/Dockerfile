FROM openjdk:17-jdk-alpine
# 镜像是从 openjdk:8-jdk-alpin 继承而来的

VOLUME /root/tmp
# 表示挂载了 /root/tmp 目录到容器中

ARG JAR_FILE
# 构建参数和 ENV 的效果一样，都是设置环境变量。
#所不同的是，ARG 所设置的构建环境的环境变量，在将来容器运行时是不会存在这些环境变量的。
#但是不要因此就使用 ARG 保存密码之类的信息，因为 docker history 还是可以看到所有值的。

ADD libs/gs-spring-boot-docker-0.1.0.jar apprun.jar
# 将bootJar 添加到镜像中根目录下 命令为 apprun.jar

RUN java -version
RUN ls /
# 随便运行两个命令 给大家看下RUN的用法

ENTRYPOINT ["java","-jar","/apprun.jar"]
# ENTRYPOINT 在容器启动后执行 java 命令来运行程序


# ======= 其它的一些Dockerfile命令 ========== 这里我们没有用到不过还是提一下
#COPY package.json /usr/src/app/
#ADD 更高级的复制文件
#ADD 指令和 COPY 的格式和性质基本一致。但是在 COPY 基础上增加了一些功能。
#CMD 指令就是用于指定默认的容器主进程的启动命令的。
#ENV 设置环境变量
#HEALTHCHECK 健康检查
#EXPOSE 指令是声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
