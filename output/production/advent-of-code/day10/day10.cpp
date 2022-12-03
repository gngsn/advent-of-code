#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <stack>
#include <map>
 
using namespace std;

vector<char> chunks = {'(', ')', '[', ']', '{', '}',  '<', '>'};


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

bool isopen(char c) {
    for (int i=0; i < chunks.size(); i+=2) {
        // cout << "c : " << c << ", chunks : " << chunks[i] << endl;
        if (c == chunks[i]) return true;
    }
    return false;
}

char getclose(char open) {
    return int(open) == 40 ? int(open) - 1: int(open) - 2;
}

void part1(vector<string> input) {
    int answer=0;
    map<char, int> score = {{chunks[0], 3}, {chunks[2], 57}, {chunks[4], 1197}, {chunks[6], 25137}};

    for (string in: input) {
        stack<char> st;
        cout << "in : " << in << endl;
        for (char ch: in) {
            // cout << ch << " " << isopen(ch) << endl;
            if (isopen(ch)) st.push(ch);
            else {
                cout << "ch : " << ch << ", char: " << (ch - st.top() < 3) << ", key : " << getclose(ch) << endl; 
                if (ch - st.top() < 3) st.pop();
                else {
                    answer += score[getclose(ch)];
                    break;
                }
            }
        }
        if (!st.empty()) {
            cout << "\nch : " << st.top() << ", key : " << getclose(st.top()) << endl; 
            answer += score[getclose(st.top())];
        }
    }
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
        while(getline(file, line)){
            input.push_back(line);
        }
        file.close();
    }

    part1(input);
    // part2(input);
   
    return 0;
}
