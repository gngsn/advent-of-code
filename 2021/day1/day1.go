package main

import (
	"bufio"
	"flag"
	"log"
	"math"
	"os"
	"strconv"
)

var filepath = flag.String("f", "./data/input.txt", "a string of file path")

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func part1(input []int) {
    answer := 0
	for i := 1; i < len(input); i++ {
        if (input[i] > input[i-1]) {answer++;}
	}

	println(":: part1 answer is ", answer)
}

func part2(input []int) {
    answer := 0
	prev := math.MaxInt

	for i := 0; i < len(input)-2; i++ {
        if (input[i] + input[i+1] + input[i+2] > prev) { answer++ }
		prev = input[i] + input[i+1] + input[i+2]
     }

	println(":: part2 answer is ", answer)
}

func init() {
	flag.Parse()
}

func main() {
	var input = []int{}

	f, err := os.Open(*filepath)
	check(err)
	defer f.Close()
	reader := bufio.NewScanner(f)

	for reader.Scan() {
		sv, err := strconv.Atoi(reader.Text())
		check(err)
		input = append(input, sv)
    }

    if err := reader.Err(); err != nil {
        log.Fatalf("Error while reading file: %s", err)
    }

	part1(input)
	part2(input)
}