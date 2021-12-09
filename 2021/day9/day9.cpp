#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>

using namespace std;

bool visited[100][100];
int cnt =0;

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

void part1(vector<string> input) {
    int answer=0;
    for (int i = 0; i < input.size(); i++) {
        for (int c = 0; c < input[i].size(); c++) {
            bool isLowest=true;
            if (i > 0) isLowest = isLowest && (input[i][c] < input[i-1][c]);
            if (i+1 < input.size()) isLowest = isLowest && (input[i][c] < input[i+1][c]);
            if (c > 0) isLowest = isLowest && (input[i][c] < input[i][c-1]);
            if (c+1 < input[i].size()) isLowest = isLowest && (input[i][c] < input[i][c+1]);
            if (isLowest) answer += (input[i][c] - '0') + 1;
        }
    }
    cout << ":: part1 answer is " << answer << endl;
}

void find(vector<string> input, int x, int y) {
    if (visited[x][y] || input[x][y] == '9') return;
    visited[x][y] = true;

    cnt++;
    if (x>0) find(input, x-1, y);
    if (x<input.size()-1) find(input, x+1, y);
    if (y>0) find(input, x, y-1);
    if (y<input[x].size()-1) find(input, x, y+1);
}

void part2(vector<string> input) {
    int answer = 1;
    vector<int> counts;

    for (int i=0; i<input.size(); i++) {
        for (int j=0; j<input[i].size(); j++) {
            if (!visited[i][j] && input[i][j] != '9') {
                find(input, i, j);
                counts.push_back(cnt);
                cnt = 0;
            }
        }
    }

    sort(counts.begin(), counts.end());
    for (int i=counts.size()-3; i<counts.size(); i++) answer *= counts[i];

    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    vector<string> input;
    string filePath = "./data/test.txt";

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(file.is_open()){
        string line;
        while(getline(file, line)){
            input.push_back(line);
        }
        file.close();
    }

    part1(input);
    part2(input);
   
    return 0;
}
