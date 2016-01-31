Membres du groupe :
    Florian Garcia
    Teddy Gustiaux
    Yacine Mayou

Tâches par personne :
    Accueil, connexion : Florian
    Plan d'entrainement, exercices : Yacine
    Recherche, news : Teddy
    Saisies données personnelles : Florian, Yacine
    Consultation données personnelles : Yacine
    Chat : Teddy

Questions réalisées :
    Toutes les questions ont été réalisés y compris la question optionnelle.

Questions non réalisées :
    Aucune

Commentaires :
    Nous avons essayés de mettre en place un contrôle des formulaires avec JQuery Validate,
    par manque de temps nous n'avons pas eu le temps de l'intégrer correctement dans tous les formulaires.

    Au niveau de la gestion des données, on a séparé les accès en base de données des servlets.
    Les class DAO incluent la même interface générique pour avoir les mêmes fonctionnalités de base même si certaines retournent null car nous ne les utilisont pas.
    D'autres méthodes ont été rajoutés pour des fonctionnements bien précis liés au métier.

    Au niveau de la base de données :
        - TRAINING contient les plans d'entrainements
        - EXERCISE contient les exercices. EXERCISE est enfant de TRAINING
        - PERSONALDATA contient les résultats des plans d'entrainements. PERSONALDATA est enfant de TRAINING et contient l'adresse email de l'utilsiateur (car elle est unique chez Google)
