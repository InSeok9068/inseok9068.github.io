---
title: "[Node] Exprees 서버 Google 소셜 로그인"
categories:
  - Node
tags:
  - express
  - google
---

> [인프런 OAuth2 강의](https://www.inflearn.com/course/web2-oauth2/)
> 해당 강의를 듣고 보시면 많은 도움이 됩니다.

웹 사이트를 돌아다녀 보면 회원가입하며 로그인하는 게 여간 귀찮은 게 아니다. 그래서 많이들 소셜 로그인을 이용하며

대표적으로는 Google, FaceBook, Naver 등이 있다.

오늘은 Exprees로 Google 소셜 로그인하는 법을 알아보도록 하자.

일단 Express 서버를 구현해야 하는데 하나씩 설정하기 시간이 걸리니 이러한 Express 서버를 한번 구축하여 주는 패키지가 있다

```shell
npm install express-generator -g
```

해당 express-generator 패키지를 글로벌로 설치하여 준다.

설치한 글로벌 패키지를 통해서 express-google-login 프로젝트를 생성하여 준다 본인은 Template 엔진으로 pug를 사용할 것이다

```shell
express express-google-login --view=pug
```

![디렉토리](/assets/images/post/2019-10-27-express-google-login-image1.PNG)

해당 프로젝트가 생겼다.

```shell
//package.json을 기준으로 패키지 설치하여준다
npm install

//설치가 완료되면 프로젝트 실행
npm start
```

이제 브라우저를 키고 **localhost:3000** 를 쳐보자

![서버실행](/assets/images/post/2019-10-27-express-google-login-image2.PNG)

위와 같이 보인다면 정상적으로 서버가 켜진것이다.

이제 본론으로 들어가 Google 로그인 하는법을 설정할 것이다.

일단 [구글 클라우드 플랫폼](https://console.cloud.google.com/home/)으로 들어가 등록을 해주어야한다.

메뉴 -> API 및 서비스 -> 사용자 인증 정보로 들어가면 등록할 수 있다.

![Google](/assets/images/post/2019-10-27-express-google-login-image3.PNG)

위와같이 사용자인증정보 API를 설정해준다. **리다이렉트는 똑같이 입력 바란다.**

클라이언트 아이디와 클라이언트 보안 비밀은 기억하여 준다. 본인은 파일로 보관하기 위해 JSON다운로드를 해주었다.<br>
예제를 똑같이 따라하려면 JSON다운로드 해주길 바란다.

이제 Google Login 필요한 패키지를 설치해준다.

- passport
- passport-google-oauth20
- express-session

```shell
npm install passport passport-google-oauth20 express-session --save
```

아까받은 JSON파일을 이름을 변경하여 루트 디렉토리에 google.json으로 파일이름을 변경하여 넣어준다.

#### google.json

```json
{
  "web": {
    "client_id": "************************", //본인 CliendId
    "project_id": "express-257208",
    "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    "token_uri": "https://oauth2.googleapis.com/token",
    "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    "client_secret": "**********************", //본인 CliendSecret
    "redirect_uris": ["http://localhost:3000/login/google/callback"]
  }
}
```

간단한 로그인 화면과 인증이 된 사용자만 볼 수 있는 페이지를 작성하겠다.<br>
views 디렉토리에 아래와 같이 두개의 신규 페이지를 만들어준다.<br>
index.pug도 조금 고쳐야한다.

#### index.pug

```
extends layout

block content
  h1= title
  p Welcome to #{title}
  a(href="/login") 로그인<br>
  a(href="/new") New 화면
```

#### login.pug

```
extends layout

block content
  a(href="/login/google") 구글로그인<br>
  a(href="/login/logout") 로그아웃
```

#### new.pug

```
extends layout

block content
  p 로그인한 사용자만 볼수있습니다.
```

수정이 완료되면 routes 디렉토리에 login.js, new.js 파일을 생성해준다.<br>
app.js는 수정하여 준다.

#### new.js

```js
var express = require("express");
var router = express.Router();

/* GET users listing. */
router.get("/", function (req, res, next) {
  res.render("new");
});

module.exports = router;
```

#### login.js

```js
const express = require("express");
const passport = require("passport");
const GoogleStrategy = require("passport-google-oauth20").Strategy;
const google = require("../google.json");

const router = express.Router();

passport.serializeUser((user, done) => {
  done(null, user);
});

passport.deserializeUser((obj, done) => {
  done(null, obj);
});

passport.use(
  new GoogleStrategy(
    {
      clientID: google.web.client_id,
      clientSecret: google.web.client_secret,
      callbackURL: google.web.redirect_uris[0],
    },
    (accessToken, refreshToken, profile, done) => {
      process.nextTick(() => {
        const user = profile;
        return done(null, user);
      });
    }
  )
);

/* GET home page. */
router.get("/", (req, res) => {
  res.render("login");
});

router.get("/logout", (req, res) => {
  req.logout();
  res.redirect("/");
});

router.get("/google", passport.authenticate("google", { scope: ["profile"] }));

router.get(
  "/google/callback",
  passport.authenticate("google", { failureRedirect: "/login" }),
  (req, res) => {
    res.redirect("/");
  }
);

module.exports = router;
```

#### app.js

```js
var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
var session = require("express-session");
var passport = require("passport");

var indexRouter = require("./routes/index");
var usersRouter = require("./routes/users");
var loginRouter = require("./routes/login");
var newRouter = require("./routes/new");

var app = express();

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "pug");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

app.use(
  session({
    secret: "key",
    cookie: { maxAge: 60 * 60 * 1000 },
    resave: false,
    saveUninitialized: true,
  })
);

app.use(passport.initialize());
app.use(passport.session());

const authenticateUser = (req, res, next) => {
  if (req.isAuthenticated()) {
    next();
  } else {
    res.status(301).redirect("/login");
  }
};

app.use("/", indexRouter);
app.use("/users", usersRouter);
app.use("/login", loginRouter);
app.use("/new", authenticateUser, newRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;
```

설정이 완료되면 다시 서버를 실행시켜준다.

```shell
npm start
```

테스트를 실시해본다.

1. 로그인 후 -> new화면 -> 정상 출력
2. 로그아웃 -> new화면 -> 로그인 화면으로 이동
