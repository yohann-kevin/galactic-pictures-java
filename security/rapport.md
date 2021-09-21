<h1 align="center">Rapport de sécurité pour la soutenance 5</h1>

<h2>1 - Présentation de l'application daily-galactic-pcitures</h2>

Daily Galactic Pictures est une application en lien avec une API open-data de la 
NASA qui met chaque jour de nouvelles images en rapport avec l'astronomie à 
disposition des développeurs. Le but de notre application est de regrouper toutes ces 
publications de la NASA sur une seule plateforme faisant office de forum sur lesquelles 
les amateurs en astronomie peuvent échanger, débattre et apporté de nouvelles 
informations sur un sujet données. L'application permet aux utilisateurs lambda 
(non inscrit) de consulter les photos et les articles mis à disposition par la NASA. 
L'utilisateur inscrit quant à lui peut commenter une publication, il peut aussi aimer 
une publication qui par conséquent se retrouvera dans ses favoris de plus, il aura 
aussi la possibilité de télécharger l'image de l'article en haute définition.

<h2>2 - Rapport sur la sécurité de l'application</h2>

<h3>2.1 - Politique de RGPD</h3>

La politique de RGPD de l'application est assez simple, car nous ne récoltons que très 
peu de données utilisateur. Les données présente sur notre application peuvent se 
classer en deux catégories.

* Données liées à l'utilisateur :
  * Son pseudonyme
  * Son mot de passe
  * Son adresse email afin de permettre à l'utilisteur de récupérer son mot de passe (prochainement)
  * Ses images ajouté en favoris
  * Ainsi que les commentaires posté sur la plateforme
* Données liées aux images :
  * Le titre
  * La déscription
  * L'auteur de l'article
  * Le type de média (image ou vidéo)
  * L'url de l'image ou de la vidéo
  * L'url de l'image en haute définition
  * Le nombre de fois que l'image à été aimer
  * Le nombre de fois que l'image à été télécharger

L'utilisateur à un droit complet de regard sur ces données, il peut à tout moment 
les modifier ou supprimer son compte (fonctionnalité à implémenter côté front) ce 
qui aura effet de supprimer l'intégralité de ces commentaires sur la plateforme 
cela aura aussi pour effet de supprimer sa liste d'images favorite cependant cela 
n'aura pas pour effet de faire baisser le nombre de j'aime d'une image le nombre de 
j'aime d'une image ne baisse que quand un utilisateur retire son "like" de l'image. 
Nous partons du principe que même si l'utilisateur se désinscrit, s'il n'a pas pris 
le temps de supprimer l'image de ses favoris qu'il aimait quand même cette image, à 
noter que le nombre de "like" d'une image n'est en rien lié à l'utilisateur.

<h3>2.2 - Les dépendances du projet</h3>

<h4>A - Mockito</h4>

Mockito est une dépendance très utilisée pour la mise en place de test sur une 
application Java son but va être de "mocker" (simuler) le comportement d'un 
composant. Partons du principe que nous voulons tester l'un de nos services, 
ce service dépend forcément d'un DAO et bien mockito va venir simuler le comportement 
du DAO afin de tester notre service.

Mockito est une dépendance open source dont le code source est disponible sur github 
on peut y voir qu'il possède une communauté assez active composer de 226 contributeurs 
et que la dernière mise à jour date de moins d'un mois.

<h4>B - ORG JSON</h4>

ORG JSON est un dépendance assez classique permettant de décoder du JSON. On peut 
voir sur mvn repository que sa dernière version date de mars de cette année 
(plutôt récente) et que plus de 4 000 développeurs l'utilise.

<h4>C - Lombok </h4>

Lombok est aussi une dépendance assez connue en Java elle permet d'écrire moins de 
code en générant automatiquement  des "getter et Setter" ainsi que des constructeurs 
en utilisant seulement des annotations en haut d'une classe Java. Plutôt pratique ! 
Elle permet aussi de réduire le code nécessaire à l'instanciation d'une classe en 
utilisant un "Builder".

Nous pouvons voir sur mvn repository que cette dépendance est utilisé par plus de 
13 000 développeurs Java et que ça dernière version publiée date d'avril 2021.

<h4>D - Json web token</h4>

Json web token est une dépendance un peu moins utiliser permettant d'utiliser un 
token json pour authentifier un utilisateur lors de ces requêtes vers le serveur 
lors de la connexion le serveur va renvoyer un token au front que le front réutilisera 
pour chaque appel à l'API.

Pour cette dépendance, nous utilisons la dernière version qui date de 2018.

<h4>E - Swagger</h4>

Et enfin nous utilisons swagger qui permet de générer une documentation d'API à partir 
des routes présente dans nos contrôleurs. Cette dépendance compte presque un millier 
d'utilisateurs et sa dernière version date de juillet 2020.

<h3>2.3 - Gestion des rôles utilisateurs</h3>

Concernant les rôles utilisateurs, nous avons trois rôles distincts qui n'ont pas tous 
accès aux mêmes fonctionnalités.

<h4>A - L'utilisateur non inscrit</h4>

L'utilisateur non inscrit n'a aucune stocker en base, il peut seulement consulter les 
photos et les articles sur la plateforme.

<h4>B - L'utilisateur inscrit</h4>

L'utilisateur inscrit quant à lui à accès à plus de fonctionnalité, il peut 
notamment aimer une image, la commenté afin d'y apporter un complément d'information 
ou pour débattre avec d'autres utilisateurs. Il a aussi la possibilité de télécharger 
l'image d'un article en haute définition.

<h4>C - L'administrateur</h4>

L'administrateur possède toutes les fonctionnalités citées ci-dessus, mais il peut 
en plus manipuler les images, c'est le seul à pouvoir le faire, c'est lui qui a pour 
tâche de mettre à jour la base et d'ajouter de nouvelles images en faisant appel à 
l'API de la NASA.

<h3>2.4 - Autres informations sur la sécurité du projet</h3>

<h4>A - Requête à la base de données</h4>

Pour les requêtes faites à la base de données nous utilisons JPA un ORM de spring 
qui nous permet tout simplement de ne pas avoir à rédiger nos requêtes en SQL et 
de les préparer avant de les exécuter nous avons seulement besoin de faire appel à 
des méthodes l'interface puis JPA se charge de préparer la requête 
avant de l'exécuter.

<h4>B - Politique de Cors</h4>

Afin de contrôler qui à accès à notre API, j'ai pris le temps de configurer une 
politique de Cors séparé en deux parties distinct la première est la partie globale 
de l'api avec les images, les commentaires, les utilisateurs et les favoris de nos 
utilisateurs qui sont seulement accessibles par deux sources la première est le front 
présent en production et la seconde est notre front en local pour le développement.

La deuxième partie sera quant à elle disponible pour tout le monde, car notre 
application a pour but final de proposer une api open-data mettant nos images à 
disposition d'autre développeur. Afin d'assurer tout de même la sécurité malgré 
l'ouverture à n'importe quelle source j'ai limité l'utilisation de cette partie de 
l'api à l'utilisation de la méthode "GET" en d'autres termes les utilisateurs de la 
futur API open-data ne pourrons que récupérer de la donnée via les routes qui leur 
seront mise à disposition, ils ne pourront ni supprimer ni modifier ni ajouter de la 
donnée dans notre base.

Notre politique de Cors : 

```java
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/open/picture").allowedOrigins("*").allowedMethods("GET");
    registry.addMapping("/**").allowedOrigins(
            "https://happy-lalande-1ba658.netlify.app/",
            "http://localhost:8080"
    );
  }
}
```

<h4>C - En-tête http</h4>

Afin d'améliorer la sécurité de notre application, il est important de mettre 
en place différents en-têtes http qui vont venir sécuriser la connexion. Pour 
vérifier la sécurité de nos en-têtes, j'utilise le site securityheaders.com qui 
permet de scanner son site et d'obtenir une note de sécurité allant de A à F 
l'objectif étant bien sur d'obtenir la meilleure note.

Pour commencer je me suis occuper des "CSP" la politique de sécurisation du 
contenue qui est notamment efficace contre les failles XSS car il nous permet de 
limiter les sources de notre programme je lui suis mit la valeur "self" afin qu'il 
n'autorise que les sources de code interne au programme ce qui évite les injections 
de script venu de l'extérieur.

Je me suis aussi occupé du "Referrer-Policy" la politique de référencement, 
lorsque un utilisateur de votre application clique sur un lien pointant vers une 
autre application, cette autre application à la possibilité de récupérer des 
informations sur l'origine d'où vient l'utilisateur. C'est ce même système qui 
est utilisé par Google Analytics par exemple où l'on peut voir que tant 
d'utilisateur sont venu sur mon site via Twitter par exemple ou autre. Cette 
fonctionnalité est très utile cependant il est parfois nécessaire de restreindre 
la quantité d'informations présentes dans cet en-tête. Cet en-tête peut prendre 
différentes valeurs en fonction des besoins de l'application dans notre cas, je 
lui ai mis la valeur "no-referrer-whendowngrade", grâce à cette valeur le navigateur 
n'enverra pas l'en-tête de référence lors de la navigation de HTTPS vers HTTP.

J'ai aussi configuré une politique de permission "Permission-Policy" à laquelle 
j'ai mis la valeur "self" cette en-tête permet de limiter l'utilisation de nos 
fonctionnalités avec cette valeur, je limite l'utilisation de nos fonctionnalités 
à notre domaine.

Notre configuration d'en-tête http : 
```java
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers()
            .xssProtection()
            .and()
            .contentSecurityPolicy("script-src 'self'")
            .and().permissionsPolicy(permissions -> permissions
                    .policy("geolocation 'self'")
            )
            .and().referrerPolicy(referrerPolicy ->
                    referrerPolicy
                            .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER_WHEN_DOWNGRADE)
            );
  }
}
```

Notre note sur securityheaders.com : 

![](src\security-header.png)

<h2>3 - Application à attaquer</h2>

Pour la partie attaque, je m'en suis pris à Yaroslave et son application 
destiner à un club de Bushido son application est composé de plusieurs types 
d'utilisateur nous avons tout d'abord l'utilisateur de base qui peut s'inscrire 
et participer à un cours nous avons le coach qui peut créer des cours puis nous avons 
l'administrateur qui lui peut supprimer des utilisateurs.

Lors des différentes attaques, mon but sera donc de créer un nouveau 
administrateur afin d'avoir accès à la liste de tous les utilisateurs.

<h3>3.1 - La faille XSS</h3>

<h4>A - Description de la faille</h4>

La faille XSS est la plus facile à essayer, il suffit juste d'essayer 
d'injecter un script dans l'application. Pour tester cette faille, je vais 
tout simplement essayer de faire apparaître une alerte en injectant un script 
dans l'URL.

<h4>B - Classification</h4>

| Impact | Probabilité | Exploitabilité
| :--------------- |:---------------:|:---------------:|
| 2/3 | 3/3 | 2/3 |

[processus de classification suivi](https://blog.clever-age.com/fr/2014/02/10/owasp-xss-cross-site-scripting/)

<h4>C - Processus de test </h4>

Pour tester cette vulnérabilité, je vais tout simplement essayer d'injecter 
un petit bout de script dans l'URL permettant de récupérer les utilisateurs. 
Ce script va tout simplement essayer d'afficher une boite modale si celle-ci 
fonctionne, il sera donc possible d'injecter du JavaScript malicieux sur 
le serveur.

L'URL tester : 

```
https://cda-bushido.herokuapp.com/user/%3Cscript%3Ealert(%22plop%22)%3C/script%3E
```

La boîte modale ne s'affiche pas il est donc impossible d'effectuer des 
injections XSS sur cette application.

<h3>3.2 - Security Misconfiguration</h3>

<h4>A - Description de la faille</h4>

Security Misconfiguration fait référance au configuration de sécurité mal 
paramétré dans notres cas je vais essayer de me connecter en tant qu'administrateur 
en utilisant des mot de passe simple mais très courant comme "admin" ou autre.

<h4>B - Classification</h4>

| Impact | Probabilité | Exploitabilité
| :--------------- |:---------------:|:---------------:|
| 2/3 | 3/3 | 3/3 |

[processus de classification suivi](https://owasp.org/www-project-top-ten/2017/A6_2017-Security_Misconfiguration)

<h4>C - Processus de test </h4>

Pour commencer, je connaissant le prénom et le nom de Yaroslav, je me suis mis 
en quêtes de trouver son adresse e-mail personnelle que je n'ai pas eu de mal 
à trouver via LinkedIn une fois récupérer je n'avais plus qu'à tester sa route 
d'authentification sur Postman avec son e-mail est des mot de passe généraliste 
comme "admin" ou "root" ou bien "test".

L'URL tester : 

```
https://cda-bushido.herokuapp.com/auth
```

Les données d'identification tester : 

```json
{
  "email": "yaroslav.hontar@gmail.com",
  "password": "admin"
}
```

Dans notre cas, le serveur m'a renvoyé une erreur 403 de non-autorisation,
je peux en conclure que le système de connexion à l'administration est bien configurer.

<h3>3.3 - Broken Access Control</h3>

<h4>A - Description de la faille</h4>

Le Broken Access Control fait référence à une mauvaise gestion de droit des 
utilisateurs pour tester ça, nous allons essayer de créer un coach fonctionnalité 
qui est normalement réservée à l'administrateur.

<h4>B - Classification</h4>

| Impact | Probabilité | Exploitabilité
| :--------------- |:---------------:|:---------------:|
| 3/3 | 2/3 | 2/3 |

[processus de classification suivi](https://owasp.org/www-project-top-ten/2017/A5_2017-Broken_Access_Control)

<h4>C - Processus de test </h4>

Pour tester cette faille de sécurité, je vais de nouveau utiliser Postman, je vais 
tout simplement essayer de créer un nouveau coach avec des identifiants perso si ça 
marche, je pourrais donc me connecter à son application en tant que coach.

L'URL tester :

```
https://cda-bushido.herokuapp.com/admin/signup/coach
```

Les données d'identification tester :

```json
{
  "firstName": "super",
  "lastName": "yoyo",
  "email": "plop@plop.com", 
  "password": "super-yoyo"
}
```

Dans notre cas, le serveur m'a renvoyé une erreur 403 de non-autorisation, 
je peux en conclure que le système rôle mis en place fonctionne bien.

<h3>3.4 - Injection SQL</h3>

<h4>A - Description de la faille</h4>

Je termine donc par l'une des plus connue la plus haute le classement de l'OWASP, 
je parle bien sur des injections de type SQL dans notre cas. Cette faille de 
sécurité consiste à essayer d'envoyer une requête SQL au serveur ou bien de 
modifier une requête présente afin d'en extraire des données par exemple, mais 
il est aussi possible d'altérer la base de données en ajoutant ou supprimant des 
tables en modifiant le contenu de certaine, etc...

<h4>B - Classification</h4>

| Impact | Probabilité | Exploitabilité
| :--------------- |:---------------:|:---------------:|
| 3/3 | 3/3 | 3/3 |

[processus de classification suivi](https://owasp.org/www-project-top-ten/2017/A1_2017-Injection)

<h4>C - Processus de test </h4>

Pour tester cette faille, je vais tout simplement essayer de me connecter sur 
l'application en insérant une condition SQL qui sera toujours vraie. En faisant 
ça sur l'e-mail et le mot de passe, il est possible que le serveur interprète la 
requête comme étant vrai même si les identifications de connexion sont fausses, 
il serait donc possible de me connecter et d'avoir accès à certaines données en 
fonctionnalité sans être inscrit.

L'URL tester :

```
https://cda-bushido.herokuapp.com/auth
```

L'injection SQL :

```json
{
  "email": "' OR '1' = '1';--", 
  "password": "' OR '1' = '1';--"
}
```

Dans notre cas, le serveur m'a renvoyé une erreur 403 de non-autorisation, 
je peux en conclure que les requêtes SQL sont préparées avant d'être exécutées 
ce qui rend impossible toute injection SQL.

GG à toi Yaroslav ton API est bien sécurisé :)