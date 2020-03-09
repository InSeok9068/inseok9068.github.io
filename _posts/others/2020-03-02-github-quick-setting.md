---
title: "[Git] GitHub 저장소와 로컬 프로젝트 연결"
categories: 
  - Others
tags : 
  - git
---


### …or create a new repository on the command line

```shell
echo "# Spring-Boot-Learn" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin <사용자 Git 주소>
git push -u origin master
```

### …or push an existing repository from the command line

```shell
git remote add origin <사용자 Git 주소>
git push -u origin master
```