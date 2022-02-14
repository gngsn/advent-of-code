#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
 
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

void part1(vector<string> input) {
    int answer=0;
    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<string> input) {
    int answer=0;
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
        while(getline(file, line, ',')){
            input.push_back(line);
        }
        file.close();
    }

    part1(input);
    part2(input);
   
    return 0;
}
