#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <numeric>
 
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

void part1(vector<int> input) {
    int answer=0;
    int min=2e9;
    sort(input.begin(), input.end());
    int total = accumulate(input.begin(), input.end(), 0);
    int block = 0, prev = 0;

    for (int i=prev+1; i<input.size(); i++) {
        if (input[prev] != input[i]) {
            int under = input[prev] * i - block;
            int over = total - block - ((input.size() - i) * input[prev]);
            if (min > under+over) min = under+over;
            prev = i;
        }
        block += input[i];
    }

    cout << ":: part1 answer is " << min << endl;
}

void part2(vector<int> input) {
    int answer = 2e9;
    int max = *max_element(input.begin(), input.end());

    for (int i=0; i <= max; i++) {
        int sum = 0;
        for (int j=0; j < input.size(); j++) {
            if (sum > answer) break;
            sum += abs(i-input[j]) * (abs(i-input[j])+1) / 2;
        }
        if (answer > sum) answer = sum;
    }

    cout << ":: part2 answer is " << answer << endl;
}

int main(int argc, char * argv[]) {
    vector<int> input;
    string filePath = "./data/test.txt";

    if (fopen(argv[2], "r") != NULL) {
        filePath = getFilename(argc, argv);
    }

    ifstream file(filePath);
    if(file.is_open()){
        string token;
        while(getline(file, token, ',')){
            input.push_back(stoi(token));
        }
        file.close();
    }

    part1(input);
    part2(input);
   
    return 0;
}
