# Opérations sur un système de fichiers

Ce projet crée un logiciel s'inspirant de __[cloc](https://github.com/AlDanial/cloc)__ (Count Lines of Code).
Il existe d'autres logiciels similaires, comme __[tokei](https://github.com/XAMPPRocky/tokei)__.
Nous en ferons ici une version très simplifiée.

Le travail à faire est indiqué dans [sujet/TODO.md](sujet/TODO.md)

##  JDK utilisé
Ce projet déclare JAVA 17 dans son [build.gradle](build.gradle) et utilise effectivement certaines constructions
de langage issues de JAVA 17.

## Logging
Si vous souhaitez activer la journalisation des opérations effectuées par chaque commande,
commentez (au sens XML) la configuration sur la ligne 4 dans le fichier src/main/resources/logback.xml

Lorsque la journalisation est activée, la configuration actuelle permet d'écrire les logs dans le fichier 
logs/application.log. Les logs ne sont pas écrits dans la console pour ne pas surcharger l'affichage.

# Exécution
Cette section montre des exemples d'utilisation de ce logiciel.

__NB :__ Les tailles affichées dans les tableaux ci-après, résultants de l'exécution 
de chaque commande, seront différentes dans votre environnement, et chaque fois que
vous aurez modifié, ajouté ou supprimé des fichiers.
 
## Commande SIZE
Pour lancer ce programme dans le terminal, en utilisant gradle 
(dans ce qui suit, __gradle__ peut être remplacé par __./gradlew__) : 
```bash
gradle run --args='size -h'
```

La commande ci-dessus lance le programme avec les arguments ___size -h___. 
Du point de vue de cette exécution, nous avons lancé la ligne de commande __size__ avec __-h__ comme option. 
Cela affichera l'aide de la commande __size__ :

````bash
Usage: client.App [command] [command options]
  Commands:
    size      Shows size of files or directories. Default behavior includes 
            only files sizes in the total, while showing each directory's own 
            size. 
      Usage: size [options]
        Options:
        * -f, --files
            space-separated list of file or directory paths
          -h, --help
            display this help
          -ds, --include-directories
            include directories sizes also in total, showing each directory 
            own's size
            Default: false
          -fs, --include-files
            include files sizes only in total, showing each directory total as 
            contained files or subdirectories' total. This implies that an 
            empty directory's size is 0
            Default: false
          -u, --unit
            unit in which to display the size
            Default: BYTES
            Possible Values: [BYTES, KB, MB, GB]
````
 __NB :__ En réalité, l'option -h affichera l'aide pour toutes les commandes qui seront définies, y compris les vôtres
 (cf. sujet/TODO.md)

### Exemple 1
```bash
gradle run --args='size -f ../td4-ampoule/src'
```
Dans l'exemple ci-dessus, la commande size est lancée avec comme option __-f__ et comme
argument à cette option le chemin relatif au répertoire __src__ dans un autre projet (mais vous pouvez 
utiliser le répertoire __src__ du présent projet comme exemple). 
Cette commande affiche la taille de chaque fichier dans l'arborescence de __src__ (en octets).

```bash
+==================================================================+==============+
| Name                                                             | Size (BYTES) |
+==================================================================+==============+
| ../td4-ampoule/src/test/resources/SpockConfig.groovy             | 54           |
| ../td4-ampoule/src/test/resources                                | 96           |
| ../td4-ampoule/src/test/java                                     | 64           |
| ../td4-ampoule/src/test/groovy/ampoule/AppTest.groovy            | 563          |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleAllumeeTest.groovy | 2,246        |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleCasseeTest.groovy  | 2,459        |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleEteinteTest.groovy | 2,296        |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleIOFixtures.groovy  | 621          |
| ../td4-ampoule/src/test/groovy/ampoule                           | 224          |
| ../td4-ampoule/src/test/groovy                                   | 96           |
| ../td4-ampoule/src/test                                          | 160          |
| ../td4-ampoule/src/main/resources                                | 64           |
| ../td4-ampoule/src/main/java/ampoule/AmpouleAllumee.java         | 645          |
| ../td4-ampoule/src/main/java/ampoule/Ampoule.java                | 783          |
| ../td4-ampoule/src/main/java/ampoule/AmpouleState.java           | 140          |
| ../td4-ampoule/src/main/java/ampoule/AmpouleEteinte.java         | 844          |
| ../td4-ampoule/src/main/java/ampoule/AmpouleCassee.java          | 755          |
| ../td4-ampoule/src/main/java/ampoule/App.java                    | 573          |
| ../td4-ampoule/src/main/java/ampoule                             | 256          |
| ../td4-ampoule/src/main/java                                     | 96           |
| ../td4-ampoule/src/main/groovy                                   | 64           |
| ../td4-ampoule/src/main                                          | 160          |
| ../td4-ampoule/src                                               | 128          |
+------------------------------------------------------------------+--------------+
| TOTAL (BYTES)                                                    | 11,979       |
+------------------------------------------------------------------+--------------+
```

__NB :__ L'option __-f__ est requise, contrairement aux autres.


### Exemple 2 
```bash
 ./gradlew run --args='size -f ../td4-ampoule/src -u KB'   
```
Dans la commande ci-dessus, l'option __KB__ est indiquée à __size__ avec __-u__ pour spécifier l'unité
d'affichage des tailles (KB = Kilo-octets) :

```bash
+==================================================================+===========+
| Name                                                             | Size (KB) |
+==================================================================+===========+
| ../td4-ampoule/src/test/resources/SpockConfig.groovy             | 0.053     |
| ../td4-ampoule/src/test/resources                                | 0.094     |
| ../td4-ampoule/src/test/java                                     | 0.062     |
| ../td4-ampoule/src/test/groovy/ampoule/AppTest.groovy            | 0.55      |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleAllumeeTest.groovy | 2.193     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleCasseeTest.groovy  | 2.401     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleEteinteTest.groovy | 2.242     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleIOFixtures.groovy  | 0.606     |
| ../td4-ampoule/src/test/groovy/ampoule                           | 0.219     |
| ../td4-ampoule/src/test/groovy                                   | 0.094     |
| ../td4-ampoule/src/test                                          | 0.156     |
| ../td4-ampoule/src/main/resources                                | 0.062     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleAllumee.java         | 0.63      |
| ../td4-ampoule/src/main/java/ampoule/Ampoule.java                | 0.765     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleState.java           | 0.137     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleEteinte.java         | 0.824     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleCassee.java          | 0.737     |
| ../td4-ampoule/src/main/java/ampoule/App.java                    | 0.56      |
| ../td4-ampoule/src/main/java/ampoule                             | 0.25      |
| ../td4-ampoule/src/main/java                                     | 0.094     |
| ../td4-ampoule/src/main/groovy                                   | 0.062     |
| ../td4-ampoule/src/main                                          | 0.156     |
| ../td4-ampoule/src                                               | 0.125     |
+------------------------------------------------------------------+-----------+
| TOTAL (KB)                                                       | 11.698    |
+------------------------------------------------------------------+-----------+
```

__NB :__ Bytes (octets) est l'unité par défaut si rien n'est spécifié, comme dans l'exemple 1.

Dans le résultat ci-dessus, les tailles des répertoires ne sont pas incluses dans le total.

### Exemple 3 :
```bash
gradle run --args='size -u KB -ds -f ../td4-ampoule/src'   
```    
La commande ci-dessus lance la ligne de commande __size__ avec comme options
__-u KB__ et __-ds__, et comme argument (requis) le répertoire __src__ via -f.
__Size__ affiche les tailles des fichiers en Kilo-octets (KB) via l'option __-u KB__.
Avec l'option __-ds__, elle inclut les répertoires dans le calcul du total, tout en affichant 
la taille propre de chaque répertoire.

```bash
+==================================================================+===========+
| Name                                                             | Size (KB) |
+==================================================================+===========+
| ../td4-ampoule/src/test/resources/SpockConfig.groovy             | 0.053     |
| ../td4-ampoule/src/test/resources                                | 0.094     |
| ../td4-ampoule/src/test/java                                     | 0.062     |
| ../td4-ampoule/src/test/groovy/ampoule/AppTest.groovy            | 0.55      |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleAllumeeTest.groovy | 2.193     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleCasseeTest.groovy  | 2.401     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleEteinteTest.groovy | 2.242     |
| ../td4-ampoule/src/test/groovy/ampoule/AmpouleIOFixtures.groovy  | 0.606     |
| ../td4-ampoule/src/test/groovy/ampoule                           | 0.219     |
| ../td4-ampoule/src/test/groovy                                   | 0.094     |
| ../td4-ampoule/src/test                                          | 0.156     |
| ../td4-ampoule/src/main/resources                                | 0.062     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleAllumee.java         | 0.63      |
| ../td4-ampoule/src/main/java/ampoule/Ampoule.java                | 0.765     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleState.java           | 0.137     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleEteinte.java         | 0.824     |
| ../td4-ampoule/src/main/java/ampoule/AmpouleCassee.java          | 0.737     |
| ../td4-ampoule/src/main/java/ampoule/App.java                    | 0.56      |
| ../td4-ampoule/src/main/java/ampoule                             | 0.25      |
| ../td4-ampoule/src/main/java                                     | 0.094     |
| ../td4-ampoule/src/main/groovy                                   | 0.062     |
| ../td4-ampoule/src/main                                          | 0.156     |
| ../td4-ampoule/src                                               | 0.125     |
+------------------------------------------------------------------+-----------+
| TOTAL (KB)                                                       | 13.073    |
+------------------------------------------------------------------+-----------+
````

# Build
La création d'un jar de ce logiciel s'effectue par l'invocation de la cible __jar__ via gradle :
````bash
gradle jar
````

Le (uber) jar produit se trouvera dans le répertoire __build/libs/__. Ce jar contient toutes les dépendances nécessaires
à l'exécution. 

## Exécution du jar avec la commande SIZE

Pour lancer ce programme via son jar et non plus via gradle 
(remplacer __chemin/vers/nom_du_fichier.jar__ par le vrai chemin vers le jar) 
voici un exemple d'utilisation du jar pour la commande SIZE :

```bash
java -jar chemin/vers/nom_du_fichier.jar size -u KB -fs -f src
```

La commande est lancée au même niveau que le répertoire __src__.

# Bibliothèques utiles

## Parser les options et les arguments
Nous utilisons la bibliothèque __[JCommander](https://jcommander.org/)__ pour spécifier et parser les options et les 
arguments.    

## Détecter les types de fichier
Pour vous aider à détecter les types de fichier, vous pouvez faire appel à la bibliothèque 
__[Apache Tika](https://tika.apache.org/1.4/formats.html)__.

Voir aussi : __[https://dzone.com/articles/determining-file-types-java](https://dzone.com/articles/determining-file-types-java)__

La page __[suivante sur StackOverflow](https://stackoverflow.com/questions/51438/getting-a-files-mime-type-in-java__)__ 
discute abondamment de la question.

## Journalisation
La bibliothèque la plus répandue dans l'univers Java et que nous utilisons dans ce projet est 
__[Logback](http://logback.qos.ch/index.html)__.
Elle implémente la spécification définie par __[SLF4J](http://www.slf4j.org/).

# Pour aller plus loin
- Vous trouverez dans __[cet article](https://boyter.org/posts/sloc-cloc-code/)__ sur __[https://boyter.org](https://boyter.org/)__
une discussion intéressante sur le comptage de lignes de codes et un benchmark des outils existants.
- Interactive console applications in Java, __[article sur DZone](https://dzone.com/articles/interactive-console-applications-in-java)__
