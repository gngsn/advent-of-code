CPP     = g++
CPPFLAGS=-Iinclude --std=c++11

build/aoc: 
	${CPP} ${CPPFLAGS} -o build/aoc.o aoc.cpp

clean: 
	rm -f aoc.cpp \
	build/aoc.o
	rmdir build/
