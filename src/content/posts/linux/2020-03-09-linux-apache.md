---
title: '[Linux] Apache 기본적인 명령어'
categories:
  - Linux
tags:
  - Apache
---

### 1. Apache 버전 확인

```
httpd -v
```

### 2. Apache 상태 확인

```
systemctl status httpd

service httpd status
```

### 3. Apache 시작

```
systemctl start httpd

service httpd start

apachectl start
```

아파치가 running 상태 살아있음을 뜻하는것을 확인 할 수 있습니다.

### 4. Apache 중지

```
systemctl stop httpd

service httpd stop

apachectl stop
```

아파치가 dead상태 죽어있다는것을 뜻하는것을 확인 할 수 있습니다.

### 5. Apache 재시작

```
systemctl restart httpd

service httpd restart

apachectl restart
```
