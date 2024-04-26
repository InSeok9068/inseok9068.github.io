---
title: '[Node] Node로 정해진 시간마다 이메일 전송(Schedule, Mailer 사용)'
category: Node
tags:
  - node-schedule
  - nodemailer
---

오늘은 Node를 사용해서 정해진 시간마다 메일을 전송해보는 시스템을 만들어보겠다.

node-schedule와 nodemailer 패키지를 통해서 기능을 개발하며 서버는 express를 이용해보겠다.

```shell
npm install node-schedule nodemailer express --save
```

exprees 서버를 작성해보자.<br>
아래와 같이 app.js를 작성하고

### app.js

```js
var express = require('express');
var app = express();

app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
```

```shell
node app.js
```

**Example app listening on port 3000!**

콘솔에 해당 문자가 찍이면 정상적으로 서버가 올라간것이다.<br>
브라우저 통해서 확인하고 싶으면 **localhost:3000**을 입력해보면 Hello World!가 찍혀있는걸 볼 수 있다.

이제는 일단 node-schedule와 nodemailer을 import 시킨 후<br>
스케줄러가 정상작동하는지 테스트 해볼것이다.

### Cron-style Scheduling

```
*    *    *    *    *    *
┬    ┬    ┬    ┬    ┬    ┬
│    │    │    │    │    │
│    │    │    │    │    └ day of week (0 - 7) (0 or 7 is Sun)
│    │    │    │    └───── month (1 - 12)
│    │    │    └────────── day of month (1 - 31)
│    │    └─────────────── hour (0 - 23)
│    └──────────────────── minute (0 - 59)
└───────────────────────── second (0 - 59, OPTIONAL)
```

Cron-style로 시간을 나타낼수 있으면 하나의 예제를 작성해보겠다.

```js
// 5초 간격으로 실행되는게 아닌 5초에 실행한다.
var j = schedule.scheduleJob('5 * * * * *', function () {
  console.log('매분 5초마다 등장');
});
```

app.js에 해당 함수를 작성 한뒤 다시 서버를 실행해보면<br>
3분 정도 경과한 뒤 아래와 같이 콘솔에 찍혀있다.

```shell
Example app listening on port 3000!
매분 5초마다 등장
매분 5초마다 등장
매분 5초마다 등장
```

이제는 정해진 시간마다 어떠한 일을 할 수 있게 됐다.<br>
그러면 이제 nodemailer을 통해 이메일 전송하는 작업을 만든다음<br>
해당 스케줄러에 작업을 추가해준다면 우리가 원하던 작업이 완료될수 있을거같다.

Gmail을 이용하여 메일을 보낼건데 gmail 보안이 낮은 앱 허용 권한을 해주어야 한다.<br>
<https://myaccount.google.com/lesssecureapps?pli=1>

로그인이 되어있다면 링크를 따라가면 바로 설정 할 수 있을것이다.

이제 app.js를 다시한번 수정해주자.

```js
var mailSender = {
  // 메일발송 함수
  sendGmail: function (param) {
    var transporter = mailer.createTransport({
      service: 'gmail',
      prot: 587,
      host: 'smtp.gmlail.com',
      secure: false,
      requireTLS: true,
      auth: {
        user: '보내는ID@gmail.com',
        pass: '패스워드',
      },
    });
    // 메일 옵션
    var mailOptions = {
      from: '보내는ID@gmail.com',
      to: param.toEmail, // 수신할 이메일
      subject: param.subject, // 메일 제목
      text: param.text, // 메일 내용
    };
    // 메일 발송
    transporter.sendMail(mailOptions, function (error, info) {
      if (error) {
        console.log(error);
      } else {
        console.log('Email sent: ' + info.response);
      }
    });
  },
};

// 보내는 사람은 같아도 받을ID는 하나가 아니므로 따로 빼주었다.
var emailParam = {
  toEmail: '받은ID@gmail.com',
  subject: '메일 테스트',
  text: '메일 내용',
};
```

메일 설정 끝났으니 스케줄러에 정해진 시간마다 해당 함수가 실행되게 해보자

```js
var j = schedule.scheduleJob('5 * * * * *', function () {
  mailSender.sendGmail(emailParam);
});
```

이제 모든 작업 끝났다. **node app.js**를 통해 서버를 다시 실행시켜보자<br>
그리고 5분정도 지난뒤 메일을 확인해보면 아래와 같이 매분마다 메일이 도착한걸 볼 수 있다.

![메일](/assets/images/post/2019-11-02-node-schedule-mailer-image1.PNG)
