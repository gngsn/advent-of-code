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
    cout << endl;
    while (getline(ss, s, delim)) { 
        if (s == "" || s == " ") continue;
        out.push_back(stoi(s)); 
    }
}

int counting(int* diagram[], int fix, int start, int end) {
    int count=0;
    for (int ch=start; ch <= end; ch++) {
        if (diagram[ch][fix] == 1) count++;
        diagram[ch][fix]++;
    }
    return count;
}

void part1(vector<vector<int>> input) {
    int answer=0;
    int diagram[MAX_HEIGHT+1][MAX_WIDTH+1];

    for (int i=0; i <= MAX_HEIGHT; i++) fill_n(diagram[i], MAX_WIDTH+1, 0);

    for (vector<int> coord: input) {
        int x1=coord[0], y1=coord[1], x2=coord[2], y2=coord[3];
        if (x1 == x2) {
            int start = min(y1, y2);
            int end = max(y1, y2);

            // counting(diagram, x1, min(y1, y2), max(y1, y2));

            for (int y=start; y <= end; y++) {
                if (diagram[y][x1] == 1) answer++;
                diagram[y][x1]++;
            }
        }

        if (y1 == y2) {
            int start = min(x1, x2);
            int end = max(x1, x2);

            for (int x=start; x <= end; x++) {
                if (diagram[y1][x] == 1) answer++;
                diagram[y1][x]++;
            }
        }
    }

    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<vector<int>> input) {
    int answer = 0;
    int diagram[MAX_HEIGHT+1][MAX_WIDTH+1];

    for (int i=0; i <= MAX_HEIGHT; i++) fill_n(diagram[i], MAX_WIDTH+1, 0);

    for (vector<int> coord: input) {
        int x1=coord[0], y1=coord[1], x2=coord[2], y2=coord[3];
        if (x1 == x2) {
            int start = min(y1, y2);
            int end = max(y1, y2);

            // counting(diagram, x1, min(y1, y2), max(y1, y2));

            for (int y=start; y <= end; y++) {
                if (diagram[y][x1] == 1) answer++;
                diagram[y][x1]++;
            }
        }

        if (y1 == y2) {
            int start = min(x1, x2);
            int end = max(x1, x2);

            for (int x=start; x <= end; x++) {
                if (diagram[y1][x] == 1) answer++;
                diagram[y1][x]++;
            }
        }

        // cout << "x1 : " << x1 << ", x2 : " << x2 << ", y1 : " << y2 << endl;
        // for (int i=0; i <= MAX_HEIGHT; i++) {
        //     for (int j=0; j <= MAX_WIDTH; j++) { 
        //         cout << diagram[i][j] << " ";
        //     } 
        //     cout << endl;
        // }
        // cout << endl;
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

            if (xy[0] > MAX_WIDTH) MAX_WIDTH = xy[0];
            if (xy[2] > MAX_WIDTH) MAX_WIDTH = xy[2];

            if (xy[1] > MAX_WIDTH) MAX_HEIGHT = xy[1];
            if (xy[3] > MAX_WIDTH) MAX_HEIGHT = xy[3];

            input.push_back(xy);
        }        
        file.close();
    }

    for (vector<int> in: input) {
        for (int i: in) cout << i << " ";
        cout << endl;
    }

    part1(input);
    // part2(input);
   
    return 0;
}
