#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
#include <cstring>
#include <unistd.h>
#include <sstream> 

int MAX_WIDTH;
int MAX_HEIGHT;
 
using namespace std;

string getFilename(int argc, char * argv[]) {
    for (int i = 1; i < argc; i++) {  
        if (i + 1 != argc) { 
            if (strcmp(argv[i], "-f") == 0) {                 
                return argv[i + 1];
            }
        }
    }
    return "";
}

template<typename T>
void tokenize(string const &str, const char delim, vector<T> &out)  { 
    stringstream ss(str); 
 
    string s;
    while (getline(ss, s, delim)) { 
        if (s == "" || s == " ") continue;
        out.push_back(stoi(s)); 
    }
}

int countStraight(int** diagram, int x1, int y1, int x2, int y2) {
    int count=0;
    if (x1 == x2) {
        int start = min(y1, y2);
        int end = max(y1, y2);

        for (int y=start; y <= end; y++) {
            if (diagram[y][x1] == 1) count++;
            diagram[y][x1]++;
        }
    }

    if (y1 == y2) {
        int start = min(x1, x2);
        int end = max(x1, x2);

        for (int x=start; x <= end; x++) {
            if (diagram[y1][x] == 1) count++;
            diagram[y1][x]++;
        }
    }
    return count;
}

int countDiagonal(int** diagram, int x1, int y1, int x2, int y2) {
    int count=0;

    if (abs(x1-x2) / abs(y1-y2) == 1) {
        int xsum = x1 < x2 ? 1 : -1;
        int ysum = y1 < y2 ? 1 : -1;
        while (x1 != x2+xsum) {
            if (diagram[y1][x1] == 1) count++;
            diagram[y1][x1]++;
            x1 += xsum;
            y1 += ysum;
        }
    }

    return count;
}

void part1(vector<vector<int>> input) {
    int answer=0;
    int** diagram = new int *[MAX_HEIGHT];
    for (int i=0; i < MAX_HEIGHT; i++) diagram[i] = new int[MAX_WIDTH];

    for (vector<int> coord: input)
        answer += countStraight(diagram, coord[0], coord[1], coord[2], coord[3]);

    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<vector<int>> input) {
    int answer = 0;
    int** diagram = new int *[MAX_HEIGHT];
    for (int i=0; i < MAX_HEIGHT; i++) diagram[i] = new int[MAX_WIDTH];

    for (vector<int> coord: input) {
        int x1=coord[0], y1=coord[1], x2=coord[2], y2=coord[3];
        if (x1 == x2 || y1 == y2) answer += countStraight(diagram, x1, y1, x2, y2);
        else answer += countDiagonal(diagram, x1, y1, x2, y2);
    }

    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    vector<vector<int>> input;
    string filePath = "./data/test.txt";

    if (fopen(argv[2], "r") != NULL) filePath = getFilename(argc, argv);

    ifstream file(filePath);
    if(file.is_open()){
        string line;
        while(getline(file, line)) {
            vector<string> coord;
            stringstream ss(line);
            string s;

            while (getline(ss, s, char(' '))) { 
                if (s == "" || s == " ") continue;
                coord.push_back(s);
            }

            string st = coord[0];
            vector<int> xy;

            tokenize(st, ',', xy);
            st = coord[2];
            tokenize(st, ',', xy);

            if (xy[0] > MAX_WIDTH) MAX_WIDTH = xy[0]+1;
            if (xy[2] > MAX_WIDTH) MAX_WIDTH = xy[2]+1;

            if (xy[1] > MAX_HEIGHT) MAX_HEIGHT = xy[1]+1;
            if (xy[3] > MAX_HEIGHT) MAX_HEIGHT = xy[3]+1;

            input.push_back(xy);
        }        
        file.close();
    }

    part1(input);
    part2(input);
   
    return 0;
}
