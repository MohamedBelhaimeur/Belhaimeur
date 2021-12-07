# Implémentation des visiteurs

Ce package contient les visiteurs associés aux lignes de commande implémentées dans le package
__cmdline.impl__. Les interfaces des visiteurs sont dans le package visitors.api.

Le visiteur associé à chaque commande est disposé dans un sous-package indépendant.
Par exemple, le package __visitors.impl.size__ contient le visiteur associé à la commande __cmdline.impl.size.CommandSize__.

Chaque visiteur est créé par une classe dont l'interface se trouve dans le package visitors.api.
L'implémentation de cette classe est située dans le même package que celle du visiteur.
Cette implémentation est liée à son interface dynamiquement par le module Guice situé dans le même package
(ex: SizeVisitorBuilderModule).

Cette liaison dynamique permet l'injection de dépendance dans le Director de la commande considérée.
Le Director est situé dans le package de la ligne de commande correspondante, sous cmdline.impl.
  