# Advent Of Code 

재밌어 보여서 도전하다가 너무 제대로 준비해버린 챌린지 😉

이렇게 된 거 매년 도전하기로 마음먹었습니다 🤖

<br/>

## What is the AoC (Advent of Code)?

[Advent of Code](https://adventofcode.com/)는 매년 12월 1일 부터 25일 까지 진행되는 온라인 이벤트입니다. 매일 자정(EST/UTC-5 기준)에 하나의 프로그래밍 퍼즐이 공개되고, 정답을 제출하면 제출한 순서대로 1~100 점 사이의 점수를 받습니다. 가장 빨리 푼 사람이 100점, 그 다음부터 1점씩 떨어집니다.

<br/>

<details>
<summary>Complete!</summary>

**2021**

| day | done | language |
| --- | ----- | ---|
| 1 | ✔️ | cpp, go |
| 2 | ✔️ | cpp |
| 3 | ✔️ | cpp |
| 4 | ✔️ | cpp |
| 5 | ✔️ | cpp |
| 6 | ✔️ | cpp |
| 7 | ✔️ | cpp |

</details>

<br/><br/>

## How to Run

``` bash
$ sh run.sh -d <day> [-f <input-file> -y <year> -e <extension>]
```

<br/>

### Options

|flags|description| note |
| --- | ----- | ---|
| -d | *day.* 실행할 날짜(day) 입력 | *Not Null* |
| -f | *filename.* input file 입력 | *default: input.txt* |
| -y | *year.* 실행할 연도(year) 입력 | *default: 실행 연도(현재 연도)* |
| -e | *ext.* 실행할 언어 확장자 입력 | *default: cpp* |

* input file 을 지정할 시에는 실행하고자 하는 `<year>/<day>/data/` 디렉터리 내에 존재해야 합니다.

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

<br/>

#### CASE #4 ) 없는 날짜(파일)에 접근

<img width="1081" alt="스크린샷 2021-12-02 오후 3 44 18" src="https://user-images.githubusercontent.com/43839834/144371552-ef844ac2-5002-4b31-bdb6-9a09895e32f8.png">

<br/><br/>

참고로,,,터미널의 저 귀여운 로고는 [여기](https://patorjk.com/software/taag/#p=display&c=bash&f=Dancing%20Font&t=Advent%20of%20Code%20!) 에서 골랐습니다,, 내년에는 귀여운 거 말고 멋진 걸로 해볼까요 ~.~

<br/><br/>

<a href="https://hits.seeyoufarm.com">
<img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fgngsn%2Fadvent-of-code&count_bg=%23F95656&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/>
</a>

<br/><br/>