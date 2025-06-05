## Nom et Prénom : Anejjar Wiame
## Filière: MSDIA

---
# Rapport TP°5 : Gestion de Produits - Application Spring Boot

## 📌 Objectif
L'objectif de cette activité est de créer une application web JEE complète pour la gestion de produits en utilisant :
- **Spring Boot 3** (Web, Data JPA, Security, Validation)
- **Hibernate** (ORM)
- **Thymeleaf** (Templates)
- **Bootstrap 5** (Interface)
- **Base de données** : H2 (développement) / MySQL (production)
  
## ✨ Fonctionnalités principales

### 🔒 Sécurité & Authentification
- Authentification stateless (JWT/LocalStorage)
- Rôles utilisateurs : `USER` (lecture) et `ADMIN` (CRUD complet)
- Protection CSRF.
  

### 🛠️ Gestion des Produits (CRUD)
| Fonctionnalité       | Description                                  |
|----------------------|---------------------------------------------|
| **Création**         | Formulaire validé côté serveur              |
| **Lecture**          | Liste paginée + recherche dynamique         |
| **Mise à jour**      | Édition des produits existants              |
| **Suppression**      | Avec confirmation interactive               |

### 🌐 Frontend
- Templates Thymeleaf avec layout partagé
- Interface responsive (Bootstrap 5)
- Gestion des erreurs :
  - `403` Accès refusé
  - `404` Page introuvable
  - `405` Méthode non supportée

 ---
## 📦 Architecture de projet 

![img](Screens/archit1.JPG)
![img](Screens/archit2.JPG)

###  Modèles basés sur Thymeleaf layout et Bootstrap: 
![img](Screens/templates.JPG)


 ---
## 📄 Explication détaillée 
  
---
## 🛒 Entité Produit (Product.java)

La première étape consiste à modéliser notre produit en créant une classe Java annotée `@Entity`. Cette classe représente la structure de base d'un produit dans notre système avec :

- **Un identifiant unique** (`id`) généré automatiquement par la base de données  
- **Un nom** (`name`) avec validation : obligatoire (entre 3 et 50 caractères)  
- **Un prix** (`price`) qui doit être positif ou nul  
- **Une quantité** (`quantity`) toujours supérieure à 1  

Les annotations comme `@NotEmpty` et `@Min` assurent que les données sont valides avant d'être enregistrées. 

![img](Screens/pageproduct.JPG)

## 🗃️ Couche d'accès aux données - ProductRepository

La deuxième étape consiste à créer l'interface `ProductRepository` qui étend `JpaRepository`. Cette interface permet d'accéder aux données des produits en base avec :

- **Toutes les opérations CRUD** de base fournies automatiquement par Spring Data JPA  
- **Une méthode de recherche personnalisée** (`findByNameContains`) pour filtrer les produits par nom  
- **Un système de pagination intégré** via l'objet `Pageable`  

![img](Screens/productRepo.JPG)

## 🔒 Configuration de la Sécurité (SecurityConfig.java)

Cette étape configure la sécurité de l'application avec Spring Security. Nous avons :

1. **Authentification en mémoire** avec 3 utilisateurs pré-définis (dont un admin)  
2. **Hachage des mots de passe** via BCrypt (algorithme sécurisé)  
3. **Protection CSRF activée** par défaut contre les attaques  
4. **Gestion des accès** :
   - Pages publiques (`/public/**`, CSS...) accessibles sans login
   - Toutes autres routes nécessitent une authentification
   - Page d'erreur custom pour les accès refusés (`/notAuthorized`)

>  **Bonnes pratiques** :  
> - Les mots de passe sont toujours hachés (jamais en clair)  
> - En production, remplacer `InMemoryUserDetailsManager` par un UserDetailsService personnalisé avec accès à la BDD  
![img](Screens/secur1.JPG)
![img](Screens/ssecur2.JPG)
![img](Screens/secur3.JPG)

## Contrôleur de gestion des produits

Dans ce contrôleur, nous avons implémenté la gestion complète des produits avec une sécurité basée sur les rôles. Les utilisateurs peuvent consulter une liste paginée et filtrée des produits, tandis que les administrateurs peuvent ajouter, modifier ou supprimer un produit. Pour l’ajout, on utilise des formulaires avec validation via `@Valid`, et La suppression d’un produit se fait uniquement via une requête `POST` à `/admin/delete`, en récupérant l’identifiant du produit à l’aide de `@RequestParam`, ce qui évite toute suppression accidentelle par requête `GET`. Pour la modification, l’administrateur peut charger un produit existant dans un formulaire grâce à l’identifiant fourni dans l’URL via `@PathVariable`, puis l’enregistrer avec `/admin/update/{id}` après vérification de cohérence entre l'ID fourni et celui de l’objet.

La sécurité est gérée par les annotations `@PreAuthorize`, qui restreignent l'accès à certaines routes en fonction des rôles ainsi qu'une gestion des erreurs personnalisée est intégrée pour améliorer l’expérience utilisateur.

![img](Screens/controll1.JPG)
![img](Screens/controll2.JPG)
![img](Screens/controll3.JPG)
![img](Screens/controll4.JPG)

## Initialisation des produits au démarrage de l'application

Dans cette classe principale, nous avons configuré l’initialisation automatique de la base de données avec un ensemble de produits dès le lancement de l’application. Grâce à la méthode `CommandLineRunner`, une série d’objets `Product` sont créés et enregistrés dans le dépôt `ProductRepository`. Cela permet de disposer immédiatement de données de test couvrant plusieurs catégories (hardware, composants, accessoires, etc.) pour tester l’application sans devoir saisir manuellement les produits. La sécurité Spring est désactivée via `exclude = {SecurityAutoConfiguration.class}` pour simplifier le développement initial.
![img](Screens/app1.JPG)
![img](Screens/app2.JPG)
![img](Screens/app3.JPG)


## ⚙️ Configuration du fichier 'application.properties'
![img](Screens/properties.JPG)


## Résultats : Interfaces utilisateur

### 1. Page d'authentification

Les utilisateurs par défaut du système sont:  
  - Deux utilisateurs standards (Anejjar et user) et un compte administrateur (admin).
  - Avec le même mot de passe temporaire '1234'."

  - Page d'authentification : (ici l'user Anejjar qui s'est connécté mais c'est la même page d'authentification pour l'admin) 
 ![img](Screens/PAGELOGIN.JPG)

### 2. Page d'acceuil :  

  - J'ai ajouté une simple page d'acceuil juste pour présenter le système.  
![img](Screens/homme.JPG)

### 3. Liste des produits avec pagination 
  - Pour l'user :
![img](Screens/USERINTERFACE.JPG)
![img](Screens/user2.JPG)

  - Pour l'admin :
![img](Screens/ADMINPAGE.JPG)
![img](Screens/admin2.JPG)

### 4. Page d'ajout
  - Formulaire d'ajout :
  ![img](Screens/AJOUTPAGE.JPG)
  - En cas des champs vides :
![img](Screens/VALIDATEFORM.JPG)
  - Aprés l'ajout de produit :
![img](Screens/APRESAJOUT.JPG)

### 5. Page de modification
  - Formulaire de modification : j'ai modifié le produit avec l'id = 2.
![img](Screens/update.JPG)
  - Aprés la modification : 
![img](Screens/afterupdate.JPG)

### 6. Pour la suppression
  J'ai supprimé le produit avec l'id = 1.
 - Alerte de confirmation de suppression :
   ![img](Screens/SUPP.JPG)
 - Aprés la suppression :
   ![img](Screens/APRESUPP.JPG)

### 7. Page de recherche
![img](Screens/SEARCH.JPG)
### 8. Pour la déconnexion 
![img](Screens/logout.JPG)
### 9. Cas d'un accées refusés 
![img](Screens/NOTAUTOHRIZED.JPG)

  ---
## Conclusion et améliorations 

---

Ce projet Spring MVC met en place une application de gestion de produits sécurisée, avec séparation des rôles entre utilisateurs et administrateurs, ainsi qu’une interface conviviale pour afficher, ajouter, modifier et supprimer des produits. L’initialisation de la base avec des données réalistes permet une prise en main rapide et un bon aperçu des fonctionnalités.

Pour améliorer davantage ce projet, on pourrait :  

- Intégrer un système d’inscription des utilisateurs pour permettre la création de comptes.
- Permettre la modification des rôles ou profils utilisateurs à partir de l’interface admin.
- Conserver automatiquement le mot-clé de recherche et la page courante lors de la navigation ou des actions (suppression, édition), pour éviter de revenir à la première page ou perdre le filtre en cours.
- Ajouter le support du téléchargement d’images pour les produits afin d’enrichir les fiches produit.


























