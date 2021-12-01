# Advent Of Code 

<br/>

## What is the AoC (Advent of Code)?

[Advent of Code](https://adventofcode.com/)는 매년 12월 1일 부터 25일 까지 진행되는 온라인 이벤트입니다.

매일 자정(EST/UTC-5 기준)에 하나의 프로그래밍 퍼즐이 공개되고, 정답을 제출하면 제출한 순서대로 1~100 점 사이의 점수를 받습니다. 

가장 빨리 푼 사람이 100점, 그 다음부터 1점씩 떨어집니다.

<br/>

## How to Run

``` bash
    sh run.sh -d <day> [-f <input-file> -y <year>]
```

<br/>

### Options

|flags|description| note |
| --- | ----- | ---|
| -d | 실행할 날짜(day) 입력 | *Not Null* |
| -f | input file 입력 | *default: input.txt* |
| -y | 실행할 연도(year) 입력 | *default: 실행 연도(현재 연도)* |

* input file 은 실행하고자 하는 `/<year>/<day>/data/` 디렉터리 내에 존재해야 합니다.

<br/>

### 실행 예시

#### CASE #1) 실행 성공 ~

<img width="1081" alt="스크린샷 2021-12-02 오전 12 12 27" src="https://user-images.githubusercontent.com/43839834/144260406-1d901c0b-4f3c-407f-a6da-369ab984d504.png">

<br/>

#### CASE #2 ) 필수 옵션 `-d` 입력 누락시

<img width="1081" alt="스크린샷 2021-12-02 오전 12 15 36" src="https://user-images.githubusercontent.com/43839834/144260895-c7631bc6-85b0-4a91-90fb-948e6e683e08.png">

<br/>

#### CASE #3 ) 없는 날짜(디렉터리)에 접근

<img width="1081" alt="스크린샷 2021-12-02 오전 12 14 04" src="https://user-images.githubusercontent.com/43839834/144260651-20c2327d-99d1-4361-a488-60b407a134aa.png">


<br/><br/>

참고로,,,터미널의 저 귀여운 로고는 [여기](https://patorjk.com/software/taag/#p=display&c=bash&f=Dancing%20Font&t=Advent%20of%20Code%20!) 에서 골랐습니다,, 내년에는 귀여운 거 말고 멋진 걸로 해볼까요 ~.~

<br/><br/>
