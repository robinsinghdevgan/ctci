#include <bits/stdc++.h>
using namespace std;

class People
{
public:
    int birth, death;
    People(int birth, int death)
    {
        this->birth = birth;
        this->death = death;
    }
};

typedef std::vector<People> ListOfPeople;

ListOfPeople generateData(int startYear, int endYear)
{
    ListOfPeople listOfPeople;
    for (int i = 0; i < 1000; ++i)
    {
        srand(time(0)); // much needed, else same random numbers will keep getting generated
        int birthYear = rand() % (endYear - startYear) + startYear;
        int deathYear = rand() % (endYear - birthYear) + birthYear;

        listOfPeople.emplace_back(People(birthYear, deathYear));
    }
    sort(listOfPeople.begin(), listOfPeople.end(), [](People A, People B){
        return A.birth < B.birth;
    }); //sort on the basis of thier birth
    /*for(People p:listOfPeople)
        cout << "{" << p.birth << "," << p.death << "}, ";
    cout << endl;*/
    return listOfPeople;
}

int yearWithMostPeopleALive(ListOfPeople listOfPeople, int startYear, int endYear)
{
    int lengthOfDeltas = endYear - startYear + 2;
    int populationDeltas[lengthOfDeltas] = {0};
    for (People p : listOfPeople)
    {
        int birth = p.birth - startYear;
        ++populationDeltas[birth];

        int death = p.death - startYear;
        ++populationDeltas[death + 1];
    }

    int maxAliveYear = 0;
    int maxAlive = 0;
    int currentlyAlive = 0;
    for (int year = 0; year < lengthOfDeltas; year++) {
        currentlyAlive += populationDeltas[year];
        if (currentlyAlive > maxAlive) {
            maxAliveYear = year;
            maxAlive = currentlyAlive;
        }
    }	
    
    return maxAliveYear + startYear;
}

int main(int argc, char const *argv[])
{
    ListOfPeople listOfPeople = generateData(1900, 2020);
    cout << yearWithMostPeopleALive(listOfPeople, 1900, 2020) << endl;

    return 0;
}
