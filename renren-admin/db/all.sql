-- 仅包括自建的表 sys_menu的sql语句在db/mysql.sql中

-- Active: 1663837633862@@127.0.0.1@3306@renren_security
-- 学生信息
CREATE Table student(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    student_name VARCHAR(50) COMMENT '姓名',
    classs VARCHAR(50) COMMENT '班级',
    school VARCHAR(50) COMMENT '学院',
    major VARCHAR(50) COMMENT '专业',
    password VARCHAR(100) COMMENT '密码',
    listen_num INT COMMENT '认真听课' DEFAULT 0,
    interaction_num INT COMMENT '课堂互动' DEFAULT 0,
    flip_num INT COMMENT '翻转课堂' DEFAULT 0,
    after_class_num INT COMMENT '课外学习' DEFAULT 0,
    teamwork_num INT COMMENT '小组作业' DEFAULT 0,
    political_num INT COMMENT '课程思政' DEFAULT 0,
    other_num INT COMMENT '其他数' DEFAULT 0,
    judge VARCHAR(100) COMMENT '教师评价',
    course_id BIGINT COMMENT '课程id',
    PRIMARY KEY (id),
    UNIQUE KEY uk_student_id (student_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息';
-- 课程管理
CREATE TABLE course(
    id BIGINT NOT NULL COMMENT 'id',
    course_id BIGINT NOT NULL COMMENT 'course_id',
    course_name VARCHAR(50) COMMENT '课程名',
    teacher_id BIGINT COMMENT '教师id',
    term INT COMMENT '学期',
    credit INT COMMENT '学分',
    PRIMARY KEY (id),
    UNIQUE KEY uk_course_id (course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程管理';

-- Active: 1663837633862@@127.0.0.1@3306@renren_security

-- 教师贴 88
CREATE Table record_teacher(
                              id BIGINT NOT NULL COMMENT 'id',
                              student_id INT NOT NULL COMMENT '学号',
                              session_time DATE COMMENT '上课日期',
                              upload_time DATETIME COMMENT '提交日期',
                              details VARCHAR(300) COMMENT '描述',
                              likes INT COMMENT '点赞' DEFAULT 0,
                              type TINYINT COMMENT '类型' DEFAULT 88,
                              PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师贴';


-- 认真听课 0
CREATE Table record_listen(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 0,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认真听课';

-- 课堂互动 1
CREATE Table  record_interaction(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 1,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课堂互动';

-- 翻转课堂 2
CREATE Table  record_flip(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 2,
    file_url CHAR(200) COMMENT '图片路径',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='翻转课堂';

-- 课外学习
CREATE Table  record_afterclass(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 3,
    file_url CHAR(200) COMMENT '图片路径',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课外学习';

-- 小组作业
CREATE Table  record_teamwok(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 4,
    file_url CHAR(200) COMMENT '图片路径',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小组作业';

-- 课程思政
CREATE Table  record_political(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 5,
    file_url CHAR(200) COMMENT '图片路径',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程思政';

-- 其他
CREATE Table  record_other(
    id BIGINT NOT NULL COMMENT 'id',
    student_id INT NOT NULL COMMENT '学号',
    session_time DATE COMMENT '上课日期',
    upload_time DATETIME COMMENT '提交日期',
    details VARCHAR(300) COMMENT '描述',
    likes INT COMMENT '点赞' DEFAULT 0,
    type TINYINT COMMENT '类型' DEFAULT 6,
    file_url CHAR(200) COMMENT '图片路径',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='其他';

-- 教师贴 88
CREATE Table record_teacher(
                              id BIGINT NOT NULL COMMENT 'id',
                              student_id INT NOT NULL COMMENT '学号',
                              session_time DATE COMMENT '上课日期',
                              upload_time DATETIME COMMENT '提交日期',
                              details VARCHAR(300) COMMENT '描述',
                              likes INT COMMENT '点赞' DEFAULT 0,
                              type TINYINT COMMENT '类型' DEFAULT 88,
                              PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师贴';

