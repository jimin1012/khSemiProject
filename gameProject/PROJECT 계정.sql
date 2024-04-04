-- 2024.01.23(화) DB수정
DROP TABLE BOARDLIKE;
DROP TABLE RANK;
DROP TABLE REPLY;
DROP TABLE REPORT;
DROP TABLE GAME;
DROP TABLE BOARD;
DROP TABLE MEMBER;
DROP TABLE CATEGORY;


DROP TABLE "MEMBER";
DROP TABLE "BOARD";
DROP TABLE "REPLY";
DROP TABLE "GAME";
DROP TABLE "CATEGORY";
DROP TABLE "REPORT";
DROP TABLE "BOARDLIKE";
DROP TABLE "RANK";
DROP TABLE "BOARD_IMG";


CREATE TABLE "MEMBER" (
   "MEMBER_NO"   NUMBER PRIMARY KEY,
   "MEMBER_NAME"   VARCHAR2(20)      NOT NULL,
   "MEMBER_ID"   VARCHAR2(50)      NOT NULL,
   "MEMBER_EMAIL"   VARCHAR2(50)      NULL,
   "MEMBER_PROFILE"   VARCHAR2(1000),
   "MEMBER_PW"   VARCHAR2(50)      NOT NULL,
   "MANAGER_YN"   CHAR(1)   DEFAULT 'N'   NOT NULL,
   "STATUS_YN"   CHAR(1)   DEFAULT 'N'   NOT NULL,
    "MEMBER_BIRTHDAY" VARCHAR2(12) NOT NULL
);

COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원번호(시퀀스)';

COMMENT ON COLUMN "MEMBER"."MEMBER_NAME" IS '회원이름';

COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원아이디';

COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원이메일';

COMMENT ON COLUMN "MEMBER"."MEMBER_PROFILE" IS '회원의 프로필 이미지 경로';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원비밀번호';

COMMENT ON COLUMN "MEMBER"."MANAGER_YN" IS '관리자 여부 (기본N)';

COMMENT ON COLUMN "MEMBER"."STATUS_YN" IS '탈퇴여부(탈퇴Y, 미탈퇴N)';

COMMENT ON COLUMN "MEMBER"."MEMBER_BIRTHDAY" IS '회원생년월일';

CREATE TABLE "BOARD" (
   "BOARD_NO"   NUMBER   PRIMARY KEY,
   "BOARD_TITLE"   VARCHAR2(60)      NOT NULL,
   "BOARD_CONTENT"   VARCHAR2(4000)      NOT NULL,
   "BOARD_DATE"   DATE   DEFAULT SYSDATE   NOT NULL,
   "CATEGORY_NO"   NUMBER      NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL,
    "BOARD_STATUS"   CHAR(1)   DEFAULT 'N'   NOT NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글번호(시퀀스)';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';

COMMENT ON COLUMN "BOARD"."BOARD_DATE" IS '게시글 작성 일자';

COMMENT ON COLUMN "BOARD"."CATEGORY_NO" IS '번호(시퀀스)';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원번호(시퀀스)';

COMMENT ON COLUMN "BOARD"."BOARD_STATUS" IS '게시글 신고 상태 여부';

CREATE TABLE "REPLY" (
   "REPLY_NO"   NUMBER   PRIMARY KEY,
   "REPLY_CONTENT"   VARCHAR2(2000)      NOT NULL,
   "REPLY_DATE"   DATE   DEFAULT SYSDATE   NOT NULL,
   "BOARD_NO"   NUMBER      NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "REPLY"."REPLY_NO" IS '댓글번호(시퀀스)';

COMMENT ON COLUMN "REPLY"."REPLY_CONTENT" IS '댓글내용';

COMMENT ON COLUMN "REPLY"."REPLY_DATE" IS '댓글(작성)등록일';

COMMENT ON COLUMN "REPLY"."BOARD_NO" IS '게시글번호(시퀀스)';

COMMENT ON COLUMN "REPLY"."MEMBER_NO" IS '회원번호(시퀀스)';



CREATE TABLE "GAME" (
   "GAME_NO"   NUMBER PRIMARY KEY,
   "GAME_NAME"   VARCHAR2(50)      NOT NULL,
    "GAME_IMG"   VARCHAR2(1000)      NULL
);

COMMENT ON COLUMN "GAME"."GAME_NO" IS '게임번호(시퀀스X)';

COMMENT ON COLUMN "GAME"."GAME_NAME" IS '게임이름';

COMMENT ON COLUMN "GAME"."GAME_IMG" IS '게임 이미지 경로';

CREATE TABLE "CATEGORY" (
   "CATEGORY_NO"   NUMBER      PRIMARY KEY,
   "CATEGORY_NAME"   VARCHAR2(50)      NOT NULL
);

COMMENT ON COLUMN "CATEGORY"."CATEGORY_NO" IS '번호(시퀀스)';

COMMENT ON COLUMN "CATEGORY"."CATEGORY_NAME" IS '카테고리이름';



CREATE TABLE "REPORT" (
   "REPORT_NO"   NUMBER      PRIMARY KEY,
   "REPORT_CONTENT"   VARCHAR2(4000)      NOT NULL,
   "REPORT_YN"   CHAR(1)   DEFAULT 'N'   NOT NULL,
   "BOARD_NO"   NUMBER      NOT NULL
   
);

COMMENT ON COLUMN "REPORT"."REPORT_NO" IS '신고번호(시퀀스)';

COMMENT ON COLUMN "REPORT"."REPORT_CONTENT" IS '신고된 내용';

COMMENT ON COLUMN "REPORT"."REPORT_YN" IS '신고처리여부(N:기본 , Y:반려)';

COMMENT ON COLUMN "REPORT"."BOARD_NO" IS '게시글번호(시퀀스)';




CREATE TABLE "BOARDLIKE" (
   "BOARD_NO"   NUMBER   ,
   "MEMBER_NO"   NUMBER   ,
    PRIMARY KEY("BOARD_NO","MEMBER_NO")
);

COMMENT ON COLUMN "BOARDLIKE"."BOARD_NO" IS '게시글번호(시퀀스)';

COMMENT ON COLUMN "BOARDLIKE"."MEMBER_NO" IS '회원번호(시퀀스)';



CREATE TABLE "RANK" (
   "SCORE"   NUMBER      NOT NULL,
   "MEMBER_NO"   NUMBER      NOT NULL,
   "GAME_NO"   NUMBER      NOT NULL,
    PRIMARY KEY("SCORE","MEMBER_NO","GAME_NO")
);


COMMENT ON COLUMN "RANK"."SCORE" IS '게임점수';

COMMENT ON COLUMN "RANK"."MEMBER_NO" IS '회원번호(시퀀스)';

COMMENT ON COLUMN "RANK"."GAME_NO" IS '게임번호(시퀀스X)';


CREATE TABLE "BOARD_IMG" (
   "IMAGE_NO"   NUMBER   PRIMARY KEY,
   "IMAGE_PATH"   VARCHAR2(1000),
   "BOARD_NO"   NUMBER      NOT NULL
);

COMMENT ON COLUMN "BOARD_IMG"."IMAGE_NO" IS '이미지번호(시퀀스)';

COMMENT ON COLUMN "BOARD_IMG"."IMAGE_PATH" IS '이미지경로';

COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '게시글번호(시퀀스)';

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_CATEGORY_TO_BOARD_1" FOREIGN KEY ("CATEGORY_NO")
REFERENCES "CATEGORY" ("CATEGORY_NO");

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY ("MEMBER_NO")
REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "REPLY" ADD CONSTRAINT "FK_BOARD_TO_REPLY_1" FOREIGN KEY ("BOARD_NO")
REFERENCES "BOARD" ("BOARD_NO");

ALTER TABLE "REPLY" ADD CONSTRAINT "FK_MEMBER_TO_REPLY_1" FOREIGN KEY ("MEMBER_NO")
REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "REPORT" ADD CONSTRAINT "FK_BOARD_TO_REPORT_1" FOREIGN KEY ("BOARD_NO")
REFERENCES "BOARD" ("BOARD_NO");


ALTER TABLE "RANK" ADD CONSTRAINT "FK_MEMBER_TO_RANK_1" FOREIGN KEY ("MEMBER_NO")
REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "RANK" ADD CONSTRAINT "FK_GAME_TO_RANK_1" FOREIGN KEY ("GAME_NO")
REFERENCES "GAME" ("GAME_NO");

ALTER TABLE "BOARDLIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARDLIKE" FOREIGN KEY ("MEMBER_NO")
REFERENCES "MEMBER" ("MEMBER_NO");

ALTER TABLE "BOARDLIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARDLIKE" FOREIGN KEY ("BOARD_NO")
REFERENCES "BOARD" ("BOARD_NO");

ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "FK_BOARD_TO_BOARD_IMG" FOREIGN KEY ("BOARD_NO")
REFERENCES "BOARD" ("BOARD_NO");

-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOARD_NO; -- 게시판 시퀀스
CREATE SEQUENCE SEQ_MEMBER_NO; -- 회원 시퀀스
CREATE SEQUENCE SEQ_REPLY_NO; -- 댓글 시퀀스
CREATE SEQUENCE SEQ_BOARD_IMG_NO; -- 게시판 이미지 시퀀스 (임시)
CREATE SEQUENCE SEQ_REPORT_NO; -- 신고 시퀀스

-- 시퀀스 삭제
DROP SEQUENCE SEQ_BOARD_NO;
DROP SEQUENCE SEQ_MEMBER_NO;
DROP SEQUENCE SEQ_REPLY_NO;
DROP SEQUENCE SEQ_BOARD_IMG_NO;
DROP SEQUENCE SEQ_REPORT_NO;



-- 샘플데이터

-- MEMBER 테이블 샘플 데이터
BEGIN
    FOR I IN 1..50 LOOP
        INSERT INTO MEMBER VALUES(
            SEQ_MEMBER_NO.NEXTVAL,
            '유저0'||SEQ_MEMBER_NO.CURRVAL,
            'user0'||SEQ_MEMBER_NO.CURRVAL,
            NULL, NULL,
            'pass0'||SEQ_MEMBER_NO.CURRVAL||'!',
            DEFAULT, DEFAULT, '000101'   
        );
    END LOOP;
END;
/

SELECT * FROM MEMBER;

-- GAME 테이블 샘플 데이터
INSERT INTO GAME VALUES(1,'테트리스',NULL);
INSERT INTO GAME VALUES(2,'스네이크',NULL);
INSERT INTO GAME VALUES(3,'달팽이',NULL);
INSERT INTO GAME VALUES(4,'가위바위보',NULL);
INSERT INTO GAME VALUES(5,'업다운',NULL);
INSERT INTO GAME VALUES(6,'숫자야구',NULL);

SELECT * FROM GAME;

-- CATEGORY 테이블 샘플 데이터
INSERT INTO CATEGORY VALUES(1,'공지');
INSERT INTO CATEGORY VALUES(2,'자유');
INSERT INTO CATEGORY VALUES(3,'QnA');
INSERT INTO CATEGORY VALUES(4,'게임');

SELECT * FROM CATEGORY;

-- BOARD 테이블 샘플 데이터
BEGIN
    FOR I IN 1..200 LOOP
        INSERT INTO BOARD VALUES(
           SEQ_BOARD_NO.NEXTVAL,
           SEQ_BOARD_NO.CURRVAL||'번 게시글 제목',
           SEQ_BOARD_NO.CURRVAL||'번 게시글 내용',
           DEFAULT, 1, 1, DEFAULT
        );
    END LOOP;
END;
/

SELECT * FROM BOARD;

-- REPLY 테이블 샘플 데이터

BEGIN
    FOR I IN 1..10 LOOP   
        FOR J IN 1..10 LOOP
            INSERT INTO REPLY VALUES(
                SEQ_REPLY_NO.NEXTVAL,
                SEQ_REPLY_NO.CURRVAL||'번 댓글 내용',
                DEFAULT,
                1, I
            );
        END LOOP; 
    END LOOP;
END;
/
SELECT * FROM REPLY;

-- RANK 테이블 샘플 데이터
BEGIN
    FOR I IN 1..6 LOOP
        FOR J IN 1..10 LOOP
            INSERT INTO RANK VALUES(
                I+J, J, I
            );
        END LOOP;
    END LOOP;
END;
/
