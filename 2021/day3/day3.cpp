#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
 
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

int convert(string num) {
    int dec_value = 0, base = 1;
 
    int len = num.length();
    for (int i = len - 1; i >= 0; i--) {
        if (num[i] == '1')
            dec_value += base;
        base = base * 2;
    }
 
    return dec_value;
}

bool check(string mask, string cmp) {
    for (int c = 0; c < mask.size(); c++) {
        if (mask[c] != cmp[c]) return false;
    }
    return true;
}

int countZero(vector<string> input, vector<int> mask, int idx) {
    int zero = 0;
    
    for (int m: mask)
        if (input[m][idx] == '0') zero++;
    
    return zero;
}


string getLast(vector<string> input, vector<int> indexes, int val) {
    int idx = 0;
    string mask = "";
    while (indexes.size() != 1) {
        int zero = countZero(input, indexes, idx % input[0].size());
        mask += (zero > indexes.size() - zero ? val : val ? 0 : 1) + '0';
        
        vector<int> tmp;
        for (int b=0; b < indexes.size(); b++) {
            if (check(mask, input[indexes[b]])) tmp.push_back(indexes[b]);
        }
        indexes = tmp;
        idx++;
    }
    return input[indexes[0]];
}

void part1(vector<string> input) {
    vector<int> zero;
    zero.resize(input[0].size(), 0);

    for (string bits: input)
        for (int i=0; i<bits.size(); i++)
            if (bits[i] == '0') zero[i]++;
    
    string gamma = "";
    string epsilon = "";
    
    for (int z: zero) {
        gamma += z > input.size() - z ? "0" : "1";
        epsilon += z > input.size() - z ? "1" : "0";
    }
    
    int answer = convert(gamma) * convert(epsilon);
    cout << ":: part1 answer is " << answer << endl;
}

void part2(vector<string> input) {
    vector<int> base;
    
    for (int i=0; i < input.size(); i++) base.push_back(i);
    
    string oxygen = getLast(input, base, 0);
    string co2 = getLast(input, base, 1);
    
    int answer = convert(oxygen) * convert(co2);

    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    vector<string> input;
    string filePath = "/Users/gyeongseon/Git/Algorithm/test/test/input.txt";

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
