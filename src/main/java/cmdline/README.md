# Ligne de commande : infrastructure

Ce package contient l'interface et les classes d'implémentation pour les lignes de commande.

Le contenu du sous-package __api__ est stable et ne devrait plus être modifié.

## Command line
 Toute classe voulant gérer une nouvelle ligne de commande doit implémenter l'interface __api.commandline.CommandLine__.
 La méthode __execute__ est la seule à définir. Elle prend en paramètre un argument qui représente 
 la ligne de commande déjà parsée, grâce à la bibliothèque [JCommander](https://jcommander.org/).
 Par exemple, la classe __impl.size.CommandSize__ gère la commande ___size___.
 
## Command line provider
 L'interface __api.commandline.CommandLineProvider__ fournit une API standard à implanter par les classes fournisseuses
 d'objets de type __api.commandline.CommandLine__. Par exemple, pour la commande __impl.size.CommandSize__, 
 la classe fournisseuse est __impl.size.SizeProvider__. 

## Command line loader
La classe __api.commandline.CommandLineLoader__ utilise [ServiceLoader](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ServiceLoader.html) 
de java pour charger les classes fournisseuses de __api.commandline.CommandLine__, c.-à-d. les implémentations de 
__api.commandline.CommandLineProvider__.

Pour fonctionner, ServiceLoader doit connaître la déclaration des fournisseuses dans le fichier 
cmdline.api.commandline.CommandLineProvider situé dans  __src/main/resources/META-INF/services/__.
Par exemple, la classe __impl.size.SizeProvider__ est déclarée dans le fichier
 __src/main/resources/META-INF/services/cmdline.api.commandline.CommandLineProvider__, de telle sorte que 
 ServiceLoader puisse la découvrir et la charger automatiquement. 

C'est ainsi que le programme principal (__client.App__) charge toutes les classes fournisseuses de type
__api.commandline.CommandLineProvider__ sans les connaître d'avance et les utilise pour récupérer les objets
de type __api.commandline.CommandLine__ qu'elles créent. 
 
 
# Ligne de commande : création d'une nouvelle commande
 Toute nouvelle classe implémentant __api.commandline.CommandLine__ doit donc être disposée dans un sous-package
 indépendant, sous __cmdline.impl__, avec en plus :

 - la classe implémentant __api.commandline.CommandLineProvider__ et qui la crée (ex.: impl.size.SizeProvider) ;
 - la déclaration du provider dans __src/main/resources/META-INF/services/cmdline.api.commandline.CommandLineProvider__ 
 en rajoutant une ligne indiquant le nom qualifié complet (FQN) du provider ;
 - éventuellement le director de la classe qui crée le visiteur associé à cette commande.
 
 Le visiteur pour la nouvelle commande doit être disposé dans le package __visitors.impl__.
 Ce visiteur doit implanter l'interface visitors.api.Visitor.
 
 L'API de la classe qui crée le visiteur de la nouvelle commande doit être déclaré dans le package __visitors.api__ et son implémentation
 dans le même package que ce visiteur (sours visitors.impl).
 
 
   