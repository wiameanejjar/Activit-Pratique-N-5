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
- Protection CSRF désactivée (*à implémenter en production
  
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

### 📦 Architecture Technique
```plaintext
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── config/      # Configuration Spring
│   │   │       ├── controller/  # Contrôleurs MVC
│   │   │       ├── entity/      # Entités JPA
│   │   │       ├── repository/  # Spring Data JPA
│   │   │       └── service/     # Logique métier
│   │   └── resources/
│   │       ├── static/          # CSS/JS
│   │       ├── templates/       # Vues Thymeleaf
│   │       └── application.yml  # Configuration
└── pom.xml                      # Dépendances
