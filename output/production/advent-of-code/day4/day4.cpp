#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
#include <cstring>
#include <unistd.h>
#include <sstream> 
#include <algorithm> 
#define INF 2e9

int BOARD_COUNT;
int BOARD_SIZE;

using namespace std;

template<typename T>
void tokenize(string const &str, const char delim, vector<T> &out)  { 
    stringstream ss(str); 
 
    string s;
    while (getline(ss, s, delim)) { 
        if (s == "" || s == " ") continue;
        out.push_back(stoi(s)); 
    }
}

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

bool checkBingo(vector<vector<int>> boards, int x, int y) {
    bool hor=true, ver=true;
    for (int i=0; i < BOARD_SIZE; i++) {
        if (boards[i][y] != -1) hor = false;
        if (boards[x][i] != -1) ver = false;
    }
    return hor || ver;
}

int countAnswer(vector<vector<int>> boards[], int idx) {
    int sum = 0;
    for (int x=0; x < BOARD_SIZE; x++) {
        for (int y=0; y < BOARD_SIZE; y++) {
            if (boards[idx][x][y] != -1) sum += boards[idx][x][y];
        }
    }
    return sum;
}

void part1(vector<int> numbers, vector<vector<int>> boards[]) {
    int i, n;

    for (n=0; n < numbers.size(); n++) {
        for (i=0; i< BOARD_COUNT; i++) {
            for (int x=0; x < BOARD_SIZE; x++) {
                for (int y=0; y < BOARD_SIZE; y++) {
                    if (boards[i][x][y] == numbers[n]) boards[i][x][y] = -1;
                    if (n < 5) continue;
                    if (checkBingo(boards[i], x, y)) goto exit;
                }
            }
        }
    }
    exit: {}
    int answer = countAnswer(boards, i) * numbers[n];
    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<int> numbers, vector<vector<int>> boards[]) {
    int i, n, count=BOARD_COUNT;
    vector<bool> bingo;
    bingo.resize(BOARD_COUNT, false);
    
    for (n=0; n < numbers.size(); n++) {
        for (i=0; i< BOARD_COUNT; i++) {
            if (bingo[i]) continue;
            for (int x=0; x < BOARD_SIZE; x++) {
                for (int y=0; y < BOARD_SIZE; y++) {
                    if (boards[i][x][y] == numbers[n]) boards[i][x][y] = -1;
                    if (n < 5) continue;
                    if (checkBingo(boards[i], x, y)) {
                        bingo[i] = true;
                        count--;
                        if (count == 0) goto exit;
                        goto next;
                    }
                }
            }
            next: {}
        }
    }
    exit: {}

    int answer = countAnswer(boards, i) * numbers[n];
    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    string filePath = "./data/test.txt";
    vector<int> numbers;

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(!file.is_open()) {
        cout << "file을 확인해주세요." << endl;
        return 0;
    }

    string n;
    getline(file, n);
    BOARD_COUNT = stoi(n);
    vector<vector<int>> boards[BOARD_COUNT];

    string line;
    getline(file, line);
    
    tokenize(line, ',', numbers);
    int boardIdx = -1;

    while(getline(file, line)) {
        if (line == "") {
            boardIdx++;
            continue;
        }

        vector<int> bo;
        tokenize(line, ' ', bo);
        
        boards[boardIdx].push_back(bo);
    }
    file.close();

    BOARD_SIZE = boards[0].size();

    part1(numbers, boards);
    part2(numbers, boards);
   
    return 0;
}


