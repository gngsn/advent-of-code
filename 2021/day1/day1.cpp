//
//  main.cpp
//  day1
//
//  Created by 박경선 on 2021/12/01.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
#include <cstring>
#include <unistd.h>

using namespace std;

void part1(vector<int> input) {
    int answer = 0;
    
     for (int i = 1; i < input.size(); i++) {
        if (input[i] > input[i-1]) answer++;
     }
     
     cout << ":: part1 answer is " << answer << endl;
}


void part2(vector<int> input) {
    int answer = 0;
    int prev = INT_MAX;

    for (int i = 0; i < input.size()-2; i++) {
        if (input[i] + input[i+1] + input[i+2] > prev) answer++;
        prev = input[i] + input[i+1] + input[i+2];
    }

    cout << ":: part2 answer is " << answer << endl;
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

int main(int argc, char * argv[]) {
    vector<int> input;
    string filePath = "./data/input.txt";

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(file.is_open()){
        string line;
        while(getline(file, line)){
            input.push_back(stoi(line));
        }
        file.close();
    }

    part1(input);
    part2(input);
   
    return 0;
}
