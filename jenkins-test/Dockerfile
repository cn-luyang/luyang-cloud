# 使用基于 Alpine 的官方 Eclipse Temurin JRE 21 镜像作为基础镜像（容器内）
FROM registry.cn-hangzhou.aliyuncs.com/xx_blog/openjdk:21-jdk

# 设置镜像元数据 - 维护者信息（构建时）
# LABEL maintainer="cn_luyang@163.com"

# ========== 宿主机与容器交互操作 ==========
# 创建工作目录（容器内）
WORKDIR /app

# 从宿主机复制编译好的jar包到容器（构建时）
COPY target/*.jar /app.jar

# ========== 容器内操作 ==========
# 安装时区数据并配置上海时区（容器内）
# --no-cache 避免缓存，减小镜像体积
RUN apk add -U tzdata; \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime; \
    echo 'Asia/Shanghai' >/etc/timezone; \
    touch /app.jar;

# ========== 运行时环境配置 ==========
# 设置环境变量（可在运行时覆盖）
# - JAVA_OPTS: JVM 参数，默认设置内存限制
# - TZ: 时区环境变量（双保险，同时有系统时区配置）
ENV JAVA_OPTS="-Xms512m -Xmx512m" \
    TZ=Asia/Shanghai

# 声明容器运行时暴露的端口（元数据）
EXPOSE 8081

# ========== 容器启动配置 ==========
# 使用 ENTRYPOINT 允许通过 CMD 传递参数
# ${0} ${@} 可以接收 docker run 传递的所有参数
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS} -jar app.jar ${0} ${@}"]
