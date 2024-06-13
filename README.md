# khSemiProject
KH 세미 프로젝트(게임)


<div align= "center">
    <img src="https://capsule-render.vercel.app/api?type=waving&color=gradient&height=180&text=게임모음사이트&animation=&fontColor=000000&fontSize=40" />
</div>
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 프로젝트  소개 </h2> <br> 
     <ul>
         <li>프로젝트 기간 :  2023.12.04 ~ 2024.02.07 (주제선정까지 포함기간) 실제구현은 1달정도</li>
         <li>조원 : 전지민, 유찬영, 신규성, 김홍근</li>
         <li>게임사이트는 KH정보교육원 국비교육(디지털 컨버전스)공공데이터 융합 자바개발자 양성과정A30(13)소규모 (세미)프로젝트로 각자 개인의 기술 및 역량을 발전 시키기위해 제작 되었습니다.</li>
         <li>간단한 버튼 및 키보드로 미니 게임을 이용할 수 있는 웹 홈페이지며, 게시글 작성 및 로그인등 또 다른 여러 기능이 들어있습니다.</li>
         <li>최대한 심플하고 간략한 것을 원칙으로 깔끔하게 웹 홈페이지를 제작하는 것을 의도로 하였으며, 교육으로 배운 내용을 복습하고 팀원들과 화합 및 이해를 돕고, 원활한 의사소통을 추구하며 진행하였습니다.</li>
     </ul>
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 개발환경 </h2> <br> 
     <ul>
         <li>사용기술 : Java, javascript, css, html, jsp, sql(oracle) </li>
         <li>형상관리 : github</li>
     </ul>
     <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 실행 시 화면 </h2> <br> 
     <ul>
         <li><a href="https://www.notion.so/e007f50ac0024c00837bbd880fd95f44">화면 참조</a></li>
     </ul>
     <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;">ERD </h2> <br> 
     <img src="https://file.notion.so/f/f/9ea9ba60-1184-44bf-b0b7-c58d1b73dd6a/14f7529b-5941-4f9e-858a-1edc9ba656a3/Untitled.png?id=5c93140f-1f4f-4186-aae5-66ebdf4532e3&table=block&spaceId=9ea9ba60-1184-44bf-b0b7-c58d1b73dd6a&expirationTimestamp=1718344800000&signature=m45epEEmAAnvcBl_LTHG6_F4XII1BtXl2QIYAWZ9URI&downloadName=Untitled.png">
     <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> SQL문 </h2> <br>
     <details>
         <summary>
             SQL
         </summary>
     <pre>
         <code>
DROP TABLE BOARDLIKE;
DROP TABLE RANK;
DROP TABLE REPLY;
DROP TABLE REPORT;
DROP TABLE GAME;
DROP TABLE BOARD_IMG;
DROP TABLE BOARD;
DROP TABLE MEMBER;
DROP TABLE CATEGORY;


DROP SEQUENCE SEQ_BOARD_NO;
DROP SEQUENCE SEQ_MEMBER_NO;
DROP SEQUENCE SEQ_REPLY_NO;
DROP SEQUENCE SEQ_BOARD_IMG_NO;
DROP SEQUENCE SEQ_REPORT_NO;

----------------------테이블 생성 구문---------------------

CREATE TABLE "MEMBER" (
   "MEMBER_NO"   NUMBER PRIMARY KEY,
   "MEMBER_NAME"   VARCHAR2(20)      NOT NULL,
   "MEMBER_ID"   VARCHAR2(50)      NOT NULL,
   "MEMBER_EMAIL"   VARCHAR2(50)    NOT NULL,
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
   "REPLY_CONTENT"   VARCHAR2(4000)      NOT NULL,
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

-----------------제약 조건 생성 구문------------------

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

CREATE SEQUENCE SEQ_BOARD_NO NOCACHE; -- 게시판 시퀀스
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE; -- 회원 시퀀스
CREATE SEQUENCE SEQ_REPLY_NO NOCACHE; -- 댓글 시퀀스
CREATE SEQUENCE SEQ_BOARD_IMG_NO NOCACHE; -- 게시판 이미지 시퀀스
CREATE SEQUENCE SEQ_REPORT_NO NOCACHE; -- 신고 시퀀스

-- 게임
INSERT INTO GAME VALUES(1,'테트리스','https://png.pngtree.com/thumb_back/fw800/background/20230527/pngtree-tetris-wallpaper-hd-1080p-image_2693812.jpg');
INSERT INTO GAME VALUES(2,'스네이크','https://play-lh.googleusercontent.com/L9opLtUUqK0yOTq7uXTou1B4jyqf5Z_kTIG8CShM6tpsXLMTEjg5GVDzcnAO7GxOk9w7');
INSERT INTO GAME VALUES(3,'달팽이','https://image.cine21.com/resize/cine21/article/2013/0722/16_59_46__51ece6723e220[S800,800].jpg');
INSERT INTO GAME VALUES(4,'가위바위보','https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/6Opp/image/nJtTUmhg2apMuSs8mJPK18enXEc.jpg');
INSERT INTO GAME VALUES(5,'업다운','https://upup.fm/images/logo.png');
INSERT INTO GAME VALUES(6,'숫자야구','https://static.indischool.com/images/users/65b5fe18ba01c21a7fd634d337a5d361/739321bf-37b9-49f8-8a2e-32d3aa6010c3.png');

-- 게시판 카테고리
INSERT INTO CATEGORY VALUES(1,'공지');
INSERT INTO CATEGORY VALUES(2,'게임');
INSERT INTO CATEGORY VALUES(3,'자유');
INSERT INTO CATEGORY VALUES(4,'QnA');

-- 관리자
INSERT INTO MEMBER VALUES(SEQ_MEMBER_NO.NEXTVAL,'관리자','admin01','test@naver.com',NULL,'pass01!','Y',DEFAULT,'2001-01-01');

SELECT * FROM MEMBER;

commit;
         </code>
     </pre>
</details>

<br>
<br>
 <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> notion 주소 </h2>
 <span><a href="https://amplified-success-14b.notion.site/8853f975f871414a852773459256657a">세미프로젝트</a></span>
 <br>
 <br>
 <br>
 <br>
    <div style="text-align: left;"> <a href=https://blog.naver.com/jimin10722> <img src="https://img.shields.io/badge/Naver-03C75A?style=for-the-badge&logo=Naver&logoColor=white&link=https://blog.naver.com/jimin10722"> </a>
         <a href=mailto:iamjimin0722@gmail.com> <img src="https://img.shields.io/badge/Gmail-EA4335?style=for-the-badge&logo=Gmail&logoColor=white&link=mailto:iamjimin0722@gmail.com"> </a>
          </div>  <br> 
    <div style="text-align: left;"> <a href="https://hits.seeyoufarm.com"> <img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fjimin1012%2F&count_bg=%23000000&title_bg=%23000000&icon=github.svg&icon_color=%23FFFFFF&title=GitHub&edge_flat=false"/></a>
       </div> 
    <div style="text-align: left;"> 
    <h2 style="border-bottom: 1px solid #d8dee4; color: #282d33;"> 🏅 Stats </h2> <div style="text-align: left;"> <img src="https://github-readme-stats.vercel.app/api?username=jimin1012&bg_color=180,000000,&title_color=000000&text_color=000000"
         /> <img src="https://github-readme-stats.vercel.app/api/top-langs/?username=jimin1012&layout=compact&bg_color=180,000000,&title_color=000000&text_color=000000"
          /> </div> 
    </div>
    
