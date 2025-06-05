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
- Protection CSRF désactivée (*à implémenter en production*)
  
## ⚠️ Note sur la protection CSRF

**Pourquoi c'est désactivé ?**  
Dans cette application (mode développement), la protection CSRF est désactivée car :
- L'authentification utilise le **LocalStorage** (stateless) plutôt que des cookies
- Simplifie les tests pendant le développement


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



##⚙️ Configuration du fichier 'application.properties'
![img](Screens/properties.JPG)
