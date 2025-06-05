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


## ⚙️ Configuration du fichier 'application.properties'
![img](Screens/properties.JPG)
