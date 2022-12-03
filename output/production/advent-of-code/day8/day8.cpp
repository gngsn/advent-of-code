#include <iostream>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <vector>
#include <string>
#include <bitset>
 
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

bitset<7> toBitset(string str) {
    bitset<7> a;
    for(char b: str) a.set(int(b - 'a'));
    return a;
}

bool compare(string a, string b) { return a.size() < b.size(); }

void part1(vector<vector<string>> output) {
    int answer=0;
    int digit[10] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    vector<int> dig = {digit[1], digit[4], digit[7], digit[8]};
    
    for (vector<string> out: output) {
        for (string o: out) {
            if (find(dig.begin(), dig.end(), o.size()) != dig.end()) answer++;
        }
    }

    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<vector<string>> input, vector<vector<string>> output) {
    int answer=0;
    
    for (int idx=0; idx < input.size(); idx++) {
        vector<string> signal = input[idx];
        vector<string> out = output[idx];
        bitset<7> sigbits[10] = {0};
        bitset<7> alpha[10] = {0};
        
        sort(signal.begin(), signal.end(), compare);
        for (int i =0; i< signal.size(); i++)
            sigbits[i] = toBitset(signal[i]);

        alpha[1] = sigbits[0];
        alpha[4] = sigbits[2];
        alpha[7] = sigbits[1];
        alpha[8] = sigbits[9];

        for (int i=3; i<6; i++) {
            if ((sigbits[i] & alpha[1]) == alpha[1]) alpha[3] = sigbits[i];       // [3] 1과 AND 연산 == 1
            else if ((sigbits[i] | alpha[4]).all()) alpha[2] = sigbits[i];        // [2] 4와 OR 연산 == 8
            else alpha[5] = sigbits[i];                                           // [5] 둘 다 아닐 때 
        }

        for (int i=6; i<9; i++) {
            if ((sigbits[i] & alpha[4]) == alpha[4]) alpha[9] = sigbits[i];       // [9] 4와 AND 연산 == 4
            else if ((sigbits[i] & alpha[5]) == alpha[5]) alpha[6] = sigbits[i];  // [6] 5와 AND 연산 == 5 (순서 주의)
            else alpha[0] = sigbits[i];                                           // [0] 둘 다 아닐 때 
        }

        int bitsum = 0;

        for (string o: out) {
            bitset<7> bit = toBitset(o);
            for (int j = 0; j < 10; j++) {
                if (alpha[j] == bit) {
                    bitsum = bitsum*10+j;
                    break;
                }
            }
        }

        answer += bitsum;
    }

    cout << ":: part2 answer is " << answer << endl;
}

template<typename T>
void tokenize(string const &str, const char delim, vector<T> &out)  { 
    stringstream ss(str); 
 
    string s;
    while (getline(ss, s, delim)) { 
        if (s == "" || s == " ") continue;
        out.push_back(s); 
    }
}

int main(int argc, char * argv[]) {
    vector<vector<string>> input, output;
    string filePath = "./data/test.txt";

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(file.is_open()) {
        string line;
        while(getline(file, line)) {
            vector<string> all, tmp;
            tokenize(line, '|', all);
            tokenize(all[0], ' ', tmp);
            input.push_back(tmp);
            tmp={};
            tokenize(all[1], ' ', tmp);
            output.push_back(tmp);
        }
        file.close();
    }

    part1(output);
    part2(input, output);
   
    return 0;
}
