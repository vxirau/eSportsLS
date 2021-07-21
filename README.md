# P2-eSportsLS
Combinatory Optimization Algorithms

eSports La Salle is a (fictional) project created by the university to organize League of Legends game competitions. Now that the teams have been organized, we will have to distribute them in groups for the initial phase of the tournament, and organize the services we will give to the players.
The groups consist of a number of teams that will face each other, with different groups, such as group A, group B, group C ... The best teams in each group will advance to the next qualifying round.
However, we will fix the result of the groups, so that the teams of Spanish nationality have the greatest chances of winning. The trick will be to know that the players on each team have a character that they always use, and that some characters are better against each other than others.
Apart from the services that will be given to the participants, the one that concerns us now is to offer a nutritionally balanced menu to feed the players.


To meet the objectives set out in the introduction, we will have to implement a different algorithm in each section, to choose between Backtracking or Branch & Bound, as you see fit. Since these are optimization problems, one of them must find a first solution using Greedy, necessarily.
Apart from these minimum requirements, you are given the freedom to implement all the combinations of algorithms you want optionally, commenting in the memory the comparisons that you find interesting. Performing this task will raise the delivery grade by a maximum of 1.5 points (you can opt for a 10 without it).
The details of the functionalities to be implemented are the following:

MOST BALANCED MENU:

We must prepare meals for players, knowing that a balanced meal contains between 1000 and 3000 kilocalories, and that the fats consumed, in grams, must be between 20% and 35% of the kilocalories.
In our case, we want the menu to be prepared to have as many kilocalories as possible, within the limits, but with the minimum of fat to make it as healthy as possible.
In addition, the menu must contain a maximum of 5 dishes served.

GROUP PHASE:

To do the group stage we must know that each group is made up of 6 teams, with their respective players. To make a group, we will look at the main_champions of each player, as the character of one player, can be good or bad against the character of another.
Let's remember that the group stage is fixed and we want the teams with Spanish nationality to be the ones most likely to win. Thus, we will be interested in separating the Spanish teams into different groups and minimizing the number of counters that are done to a Spanish team in a given group.
You can assume that there will be no more Spanish teams than groups.
