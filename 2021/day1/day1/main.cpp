//
//  main.cpp
//  day1
//
//  Created by 박경선 on 2021/12/01.
//

#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(int argc, const char * argv[]) {
    vector<int> input;
    string filePath = "input.txt";
    int answer = 0;
    
    if (argc == 3 && fopen(argv[2], "r") == NULL) {
        filePath = argv[2];
    }
    
    ifstream file(filePath);
    if(file.is_open()){
        string line;
        while(getline(file, line)){
            input.push_back(stoi(line));
        }
        file.close();
    }
   
    for (int i = 1; i < input.size(); i++) {
        if (input[i] > input[i-1]) answer++;
    }
    
    cout << "answer is " << answer << endl;
    return 0;
}
