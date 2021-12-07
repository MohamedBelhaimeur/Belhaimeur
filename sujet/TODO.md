# Travail à faire

En utilisant les patrons de conception étudiés et en se basant sur les sources qui vous sont fournies, 
écrivez des commandes qui réaliseront les opérations suivantes sur une ou 
plusieurs arborescences de fichiers données en argument.

## Consignes
- Après avoir chargé ce projet dans IntelliJ, mettez à jour le fichier [settings.gradle](../settings.gradle), 
en remplaçant la chaine __VOTRE_NUMERO_ETUDIANT__ par votre numéro d'étudiant.
- Tous les documents de cours et TD publiés dans votre espace Coursenligne sont autorisés, 
ainsi que tout manuel de référence sur Java ou sur les bibliothèques tierce partie utilisées dans ce projet. 
- Une attention particulière sera portée au plagiat : vous ne devez pas recopier de contenus trouvés sur l'Internet ;
l'évaluation de votre copie se fera uniquement en fonction du contenu du cours, de réflexions et d'une 
démarche de résolution personnelles.
- Le travail doit être personnel et individuel. Aucune communication n'est autorisée.
- La qualité des réponses et du code sera considérée dans la note.

Contrevenir à ces consignes vous expose à des sanctions et fortes pénalités.

## Exercice 0
Indiquer la taille totale (en KB ou MB ou GB) de l'arborescence.

__NB :__ La résolution de cet exercice est fournie en exemple par la commande __Size__. 
Il n'est donc pas à refaire (cf. section __Résolution de l'exercice 1__ à la fin de ce fichier).

## Exercice 1
Identifiez les patrons de conception ainsi que les bonnes pratiques SOLID utilisés dans ce projet,
sur les sources déjà fournies (ainsi que sur votre propre code ensuite, s'il y a lieu).

Indiquez les ensembles de classes impliquées dans l'implémentation de chaque patron ou bonne pratique SOLID identifié.

Répondez à cette question dans le fichier [reponses/reponses.md](../reponses/reponses.md)

## Exercice 2
Indiquer par type T de fichier rencontré dans l'arborescence :
- le nombre de fichiers et la taille totale pour chaque type T de fichier, 
- puis la somme pour les nombres de fichiers et les tailles de chaque type.

Par exemple, si on suppose que __types__ est le nom de la commande en question, 
l'invocation suivante : 
```bash
gradle run --args='types -f src'
```

pourrait produire comme sortie :

````bash
+==========================+=======+==============+
| Content Type             | Files | Size (BYTES) |
+==========================+=======+==============+
| text/x-java-properties   | 1     | 551          |
| application/xml          | 2     | 1,591        |
| text/x-web-markdown      | 3     | 4,230        |
| application/octet-stream | 1     | 6,148        |
| text/x-java-source       | 78    | 79,570       |
| text/plain               | 2     | 246          |
+--------------------------+-------+--------------+
| TOTAL (BYTES)            | 87    | 92,336       |
+--------------------------+-------+--------------+

````
__NB 2.1 :__ On pourrait également indiquer à cette commande l'option -u qui spécifie l'unité de donnée des tailles de fichier.
L'affichage serait alors modifié en conséquence.

__NB 2.2 :__ Les paramètres dont vous pourriez avoir besoin pour cet exercice sont (déjà) définies dans
src/main/java/cmdline/params. Vous pouvez, si vous l'estimez nécessaire, en définir d'autres, en vous inspirant
de ce qui est déjà fournie et de la documentation de [JCommander](https://jcommander.org/).

## Exercice 3
Indiquer par __type de fichier__ spécifié par un format texte parmi ceux listés dans le fichier 
 [src/main/resources/contenttypes.properties](../src/main/resources/contenttypes.properties) (ex., c, java, plantuml) :
- le nombre de fichiers de ce type,
- la taille totale des fichiers de ce type, 
- le nombre total de lignes sur l'ensemble des fichiers de ce type,
- puis la somme des nombres ci-dessus.

Par exemple, si on suppose que __langs__ est le nom de la commande en question, 
alors l'invocation suivante :
```bash
gradle run --args='langs -u KB -f chemin/vers/un/repertoire/sur/ma/machine'
```

produirait comme sortie :

```bash
+============+=======+=======+===========+
| Language   | Files | Lines | Size (KB) |
+============+=======+=======+===========+
| PROPERTIES | 12    | 97    | 3.577     |
| TEX        | 151   | 23239 | 1,012.877 |
| SHELL      | 20    | 1096  | 31.021    |
| MARKDOWN   | 4     | 24    | 0.835     |
| JAVA       | 331   | 12635 | 285.752   |
+------------+-------+-------+-----------+
| TOTAL (KB) | 518   | 37091 | 1,334.062 |
+------------+-------+-------+-----------+
``` 

Si on ne souhaite avoir les stats (en octets) que sur les fichiers Java et Markdown, 
on pourrait faire l'invocation :
```bash
gradle run --args='langs -l java markdown -f chemin/vers/un/repertoire/sur/ma/machine
``` 

L'affichage s'adapterait en conséquence, ne montrant que le résultat de l'invocation sur les fichiers
Java et Markdown (ainsi que les totaux). On peut aussi ajouter l'option -u KB à la commande ci-dessus.

__NB 3.1 :__ Vous pouvez augmenter la liste des fichiers supportés en rajoutant d'autres formats tirés de 
la liste à l'adresse suivante :
[https://tika.apache.org/2.1.0/formats.html](https://tika.apache.org/2.1.0/formats.html)

__NB 3.2 :__ Les paramètres dont vous pourriez avoir besoin pour cet exercice sont (déjà) définies dans
src/main/java/cmdline/params. Vous pouvez, si vous l'estimez nécessaire, en définir d'autres, en vous inspirant
de ce qui est déjà fournie et de la documentation de [JCommander](https://jcommander.org/).
 
# Résolution de l'exercice 1

__NB :__ La résolution de l'exercice 1 vous est fournie en exemple, à travers les classes :
- cmdline/impl/size/*.java
- visitors/size/*.java
- la déclaration du provider (cmdline.impl.size.SizeProvider) de la commande size 
[cmdline/impl/size/CommandSize.java](../src/main/java/cmdline/impl/size/CommandSize.java)
dans le fichier [services.cmdline.api.commandline.CommandLineProvider](../src/main/resources/META-INF/services/cmdline.api.commandline.CommandLineProvider)

# Rendu
Rendez tout le code source de votre application à la fin de la séance. 
Pour ce faire, compressez le répertoire de votre projet en un .zip que vous déposerez dans l'espace de ce devoir
sur coursenligne.

Pensez à faire un gradle clean avant de compresser votre projet. 
Si votre archive est toujours trop volumineuse pour le rendu, 
supprimez les répertoires .gradle, .idea et out/production.