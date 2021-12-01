CPPFLAGS=-Iinclude

build/aoc: 
	g++ ${CPPFLAGS} -o build/aoc.o aoc.cpp

clean: 
	rm -f aoc.cpp \
	build/aoc.o
	rmdir build/
