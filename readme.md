# Jeu 2D avec Recherche de Chemin (BFS)

## Description
Ce projet implémente un jeu 2D où un joueur navigue à travers une grille contenant des obstacles. L'objectif est de trouver un chemin viable entre la position de départ du joueur et un objectif à l'aide de l'algorithme de Recherche en Largeur (BFS). Ce projet inclut la logique de jeu, l'implémentation de l'algorithme BFS, et une interface graphique simple réalisée avec Java Swing.

## Fonctionnalités
- **Recherche de chemin** : Utilisation de l'algorithme BFS pour explorer le terrain et trouver un chemin entre le joueur et l'objectif tout en évitant les obstacles.
- **Affichage graphique** : La grille et les obstacles sont affichés à l'aide de Java Swing.
- **Navigation** : Le joueur peut se déplacer à travers la grille, et le jeu calcule si un chemin existe entre la position actuelle du joueur et l'objectif.

## Installation
1. Clonez ce repository sur votre machine locale :
   ```bash
   git clone https://github.com/yourusername/jeu-2d-bfs.git
   ```

2. Compilez et exécutez le projet en utilisant votre IDE Java préféré (par exemple IntelliJ IDEA, Eclipse, ou NetBeans).

## Utilisation
1. Une fois le jeu lancé, vous verrez une grille avec des obstacles, une position de départ pour le joueur et un objectif.
2. L'algorithme BFS est utilisé pour déterminer si un chemin est possible entre la position de départ et l'objectif. Si un chemin est trouvé, il sera affiché à l'écran.

## Structure du Projet
- **Classe Player** : Gère la position du joueur et ses déplacements.
- **Classe Level** : Gère la grille du jeu, les obstacles et l'objectif.
- **Algorithme BFS** : Implémentation de la Recherche en Largeur pour trouver un chemin entre le joueur et l'objectif.
- **Interface Graphique** : Affichage de la grille et des obstacles avec Java Swing.

## Exécution du Code (Exemple de Code en Java)
### Implémentation de l'algorithme BFS :

```java
public class Pathfinding {
    public static boolean isPathExists(int startX, int startY, int goalX, int goalY, boolean[][] obstacles) {
        boolean[][] visited = new boolean[Level.GRID_SIZE][Level.GRID_SIZE];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        visited[startY][startX] = true;

        int[] directions = {-1, 0, 1, 0, 0, -1, 0, 1}; // 4 directions: up, down, left, right

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            if (x == goalX && y == goalY) {
                return true; // Goal reached
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i * 2];
                int ny = y + directions[i * 2 + 1];

                if (nx >= 0 && ny >= 0 && nx < Level.GRID_SIZE && ny < Level.GRID_SIZE && !visited[ny][nx] && !obstacles[ny][nx]) {
                    queue.add(new Point(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }

        return false; // No path found
    }
}
```

## Contributions
Les contributions sont les bienvenues ! Si vous avez des idées d'améliorations, des suggestions ou des corrections, n'hésitez pas à ouvrir une issue ou à soumettre une pull request.

## Licence
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus d'informations.

---

### Auteurs

- **Nom Prénom** Idriss Chadili
