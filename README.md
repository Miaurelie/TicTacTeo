# TicTacTeo

La création d’un agent qui simule le jeu du Tic Tac Toe ou un joueur peut jouer contre un agent intelligent, comme il a le choix entre différents niveaux de difficultés. 

## 1. Problématique 

Comme vu dans le projet précédent [Astar](https://github.com/yuceef/Astar), l’algorithme A* consiste à calculer le meilleur chemin vers une destination, dans les jeux avec adversité il n’est pas possible d’utiliser directement A* étant donné que dans ces jeux, le monde n’est pas statique mais dynamique et le joueur peut modifier l’environnement. 
Afin de réaliser les jeux entre deux adversaires nous utiliserons l’algorithme Minmax et Alpha-Beta, algorithme permettant de résoudre le problème de jeu comme un problème de recherche dans un arbre en visitant l'arbre de jeu pour faire remonter à la racine une valeur (appelée « valeur du jeu ») qui est calculée récursivement. 



## 2. Objectif du projet 
L’objectif de ce projet est d’implémenter l’algorithme MinMax et de son amélioration principale l’élagage Alpha-Beta en représentant le jeu sous forme d’arbre représentant tous les coups possibles jusqu’à une certaine profondeur à partir de la position courante afin d’adapter les algorithmes pour permettre à la machine de jouer contre un joueur humain. 
3. Principe de l’algorithme MinMax  
L’algorithme Min-Max est un algorithme conçu particulièrement aux jeux se jouant à tour de rôle, a pour objectif de trouver le meilleur coup à jouer à partir d’un état donné du jeu.  
 
Le principe consiste à construire un arbre de jeu qui représente toutes les évolutions possibles du jeu : 
- Un état (configuration) initial  
- Un ensemble d’opérateurs (coups légaux ou actions légales)  
                 Ces opérateurs génèrent des transitions dans l’arbre  
- Un test de terminaison  
                 Indique si le jeu est terminé  
- Une fonction d’utilité pour les états finaux 

 Cet algorithme voit quelques pas en avant et se met dans la peau de son adversaire. 
 
 Il continue de jouer jusqu'à atteindre un arrangement terminal du tableau  (état terminal), ce qui entraîne une égalité, une victoire ou une défaite. Une fois dans un état terminal, l'agent attribuera un score positif arbitraire (1)  pour une victoire, un score négatif (-1) pour une défaite ou un score neutre (0) pour une égalité. 
 Simultanément, l’algorithme évalue les mouvements menant à un état terminal en fonction du tour de jeu des joueurs. Il choisira le coup avec le score maximum quand c’est le tour de l’agent et choisira le coup avec le score minimum quand ce sera le tour du joueur humain. En utilisant cette stratégie, Minimax évite de perdre au joueur humain. 
 
 Un algorithme Minimax peut être défini comme une fonction récursive qui effectue les tâches suivantes: 
 
- Renvoyer une valeur si un état terminal est trouvé(-1,0,1) . 
- Passer par les places disponibles sur le tableau. 
- Appeler la fonction minimax sur chaque point disponible (récursivité) 
- Evaluer les valeurs renvoyées par les appels de fonction et retourner la meilleure valeur. 
 
## 4. Principe de l’algorithme Min-Max avec élagage Alpha-Beta 
L’algorithme Min-Max effectue l’évaluation pour tous le nœuds de l’arbre de jeu d’un horizon donné. Mais il existe des situations dans lesquelles, pour déterminer la valeur Minimax associée à la racine, il n’est pas nécessaire de calculer les valeurs associées à tous les nœuds de l’arbre. 
L’algorithme d’élagage Alpha-Beta conduit à une économie de temps de calcul et mémoire, et il tire son nom des paramètres suivant décrivant les bornes des valeurs d’utilité enregistrée durant le parcours .en renonçant à l`évaluation des sous arbres dès que leur valeur devient inintéressante pour le calcul de la valeur associée aux nœuds père de ces sous arbres. 

- Pour réaliser l'algorithme Alpha-Beta on associe à chaque nœud n de type MAX une valeur auxiliaire appelée Alpha(n) égale à la valeur 
de f(n) si n est un nœud terminal, ou à la meilleur valeur de ses fils trouvée jusqu'ici. 

- une valeur Beta(m) pour les nœuds MIN, valeur égale à f(m), si m est un nœud terminal, ou à la valeur minimum de ses successeurs trouvés jusqu'ici. 
Parce que dans un arbre de jeu les niveaux des nœuds de type MAX et MIN s'alternent, on a les relations suivantes :

            `Alpha(n) = max {Beta(f1), …, Beta(fk) } `

            `Beta(m) = min {Alpha(j1), .. Alpha(jk) } `

            `Beta(fi) <= Alpha ( Père(fi) ) `

            `Alpha(fi) >= Beta ( Père(fi) ) `

où fi et ji sont respectivement les fils de n et de m 
Dans l’exécution de l'algorithme Alpha-Beta coupe les sous arbres selon les règles suivantes: 
- on interrompt la recherche d'un nœud n de type MAX si  Alpha(n) ³ Beta(Père(n)), parce que c'est une valeur inintéressante qui ne peut pas changer la valeur Beta du nœud père de n (qui est un nœud de type MIN). 

- on interrompt la recherche d'un nœud m de type MIN si        Beta(m) £ Alpha (Père(m)) parce que cette valeur ne peut pas influencer le processus de détermination du maximum qui établit la valeur Alpha de son père. 

De cette façon, l'algorithme Alpha-Beta ne génère plus les successeurs d'un nœud dès qu'il est évident que, suite aux propriétés des fonctions minimum et maximum et aux valeurs associées aux nœuds déjà générés, ce nœud ne sera pas pris en compte pour le choix du coup. 

