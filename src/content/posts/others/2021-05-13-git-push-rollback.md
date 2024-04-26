---
title: '[Git] Push 되돌리기 및 Git 실수 되돌리기'
category: Others
tags:
  - git
---

오늘은 Git에서 실수로 인한 Push 혹은 브랜치 삭제 등을 고려하여

해당 작업들을 다시 되돌리는 방법을 알아보려 한다.

나의 상황을 예시로 몇 가지 케이스를 알려주려고 한다.

### 1. 잘못된 Push로 특점 시점의 Commit 지점으로 되돌리기

#### 1. 원하는 Commit 지점으로 되돌리기

```console
$ git reset --hard [commit id]
```

**> Option**

1. --soft : Commit을 취소하고 해당 파일들은 staged 상태로 워킹 디렉터리에 보존
2. --mixed : Commit을 취소하고 해당 파일들은 unstaged 상태로 워킹 디렉터리에 보존
3. --hard : Commit을 취소하고 해당 파일들은 unstaged 상태로 워킹 디렉터리에서 삭제

#### 2. 원격 저장소에 강제로 Push

```console
$ git push -f origin [branch name]
```

**> Option**

1. -f : 강제

---

### 2. Branch 삭제 되돌리기

#### 1. git 작업 내역 확인

```console
$ git reflog
```

#### 2. Branch Checkout 하기

```console
$ git checkout -b <삭제된 branch name> [commit id]
```

**강제 Push권한 혹은 해당 원격 저장소가 강제 Push를 못하게 막혀있는 경우도 있으니**

**권한 및 프로젝트 설정도 한번 꼭 확인해보기 바란다.**
