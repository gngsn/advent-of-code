#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
#include <cstring>
#include <unistd.h>
#include <sstream> 
 
using namespace std;

void tokenize(string const &str, const char delim, vector<string> &out)  { 
    stringstream ss(str); 
 
    string s; 
    while (getline(ss, s, delim)) { 
        out.push_back(s); 
    }
}

void part1(vector<string> input) {
    int horizon = 0;
    int depth = 0;

    for (int i =0; i< input.size(); i++) {
        const char delim = ' '; 
    
        vector<string> out; 
        tokenize(input[i], delim, out);

        string cmd = out[0];
        int n = stoi(out[1]);
        if (cmd == "forward") {
            horizon += n;
        } else if (cmd == "up") {
            depth -= n;
        } else if (cmd == "down") {
            depth += n;
        }
    }

    int answer = horizon * depth;
    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<string> input) {
    int horizon = 0;
    int aim = 0;
    int depth = 0;

    for (int i =0; i< input.size(); i++) {
        const char delim = ' '; 
    
        vector<string> out; 
        tokenize(input[i], delim, out);
        string cmd = out[0];
        int n = stoi(out[1]);

        if (cmd == "forward") {
            depth += n * aim;
            horizon += n;
        } else if (cmd == "up") {
            aim -= n;
        } else if (cmd == "down") {
            aim += n;
        }
    }

    int answer = horizon * depth;
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
