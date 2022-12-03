#include <iostream>
#include <fstream>
#include <vector>
#include <climits>
#include <cstring>
#include <map>
#include <cmath>
#include <unistd.h>
#include <numeric>
#include <sstream> 
 
using ul = unsigned long;
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

void part1(vector<int> input, int day) {    
    while (day > 0) {
        int newFish = 0;
        for (auto it = input.begin(); it != input.end(); it++) {
            if (*it == 0) {
                *it = 6;
                newFish++;
            } else {
                *it -= 1;
            }
        }
        for (;newFish > 0;newFish--) input.push_back(8);
        day--;
    }

    int answer = input.size();
    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<ul> input, int day) {
    vector<ul> counts;
    counts.resize(9);
    for (ul in: input) ++counts[in];

    while (day > 0) {
        ul z = counts[0];
        rotate(counts.begin(), counts.begin()+1, counts.end());
        counts[6] += z;
        day--;
    }

    ul answer = accumulate(counts.cbegin(), counts.cend(), 0uL);
    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    vector<ul> input;
    string filePath = "./data/test.txt";
    string token, line;

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(file.is_open()){
        while (getline(file, token, ',')) {
            ul i = stoul(token);
            input.push_back(i);
        }
        file.close();
    }

    part1(input, 80);
    part2(input, 256);
   
    return 0;
}
