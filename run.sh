#!/bin/bash

echo "\n"
echo '      _      ____   __     __ U _____ u _   _     _____         U  ___ u  _____        ____   U  ___ u  ____  U _____ u      _    '
echo '  U  /"\  u |  _"\  \ \   /"/u\| ___"|/| \ |"|   |_ " _|         \/"_ \/ |" ___|    U /"___|   \/"_ \/ |  _"\ \| ___"|/    U|"|u  '
echo '   \/ _ \/ /| | | |  \ \ / //  |  _|" <|  \| |>    | |           | | | |U| |_  u    \| | u     | | | |/| | | | |  _|"      \| |/  '
echo '   / ___ \ U| |_| |\ /\ V /_,-.| |___ U| |\  |u   /| |\      .-,_| |_| |\|  _|/      | |/__.-,_| |_| |U| |_| |\| |___       |_|   '
echo '  /_/   \_\ |____/ uU  \_/-(_/ |_____| |_| \_|   u |_|U       \_)-\___/  |_|          \____|\_)-\___/  |____/ u|_____|      (_)   '
echo '   \\    >>  |||_     //       <<   >> ||   \\,-._// \\_           \\    )(\\,-      _// \\      \\     |||_   <<   >>      |||_  '
echo '  (__)  (__)(__)_)   (__)     (__) (__)(_")  (_/(__) (__)         (__)  (__)(_/     (__)(__)    (__)   (__)_) (__) (__)    (__)_) '
echo "\n\n\n"


while getopts d:f:y:e: flag; do
  case "${flag}" in
    d) day=${OPTARG} ;;
    f) file=${OPTARG} ;;
    y) year=${OPTARG} ;;
    e) ext=${OPTARG} ;;
    *) print_usage
      exit 1 ;;
  esac
done

if [ -z $day ]
then
  echo "날짜를 입력하세요!\nEXAMPLE) sh run.sh -d 1\n\n"
  exit
fi

# default 값 지정
file=${file:="input.txt"}
year=${year:=$(date +%Y)}
ext=${ext:="cpp"}

dir="`pwd -P`/$year/day$day"
source="$dir/day$day.$ext"
input="$dir/data/$file"

echo $source
# echo $source

if ! [ -d $dir ]; then
  echo "해당하는 디렉터리가 없습니다 🥲\n"
  exit
fi

if ! [ -f $source ]; then
  echo "해당하는 파일이 없습니다 🥲\n"
  exit
fi

if ! [ -d build/ ]; then
  mkdir build/
fi

cp $source ./aoc.$ext

echo "| $year Advent of Code DAY $day |\n"

if [ $ext = "go" ]; then
  go build -o build/aoc aoc.go
  ./build/aoc -f $input
  rm aoc.go
  
elif [ $ext = "java" ]; then
  java aoc.java
  rm aoc.java

else
  `make`
  build/aoc.o -f $input

  `make clean`
fi

echo "\n"

rm -rf build/