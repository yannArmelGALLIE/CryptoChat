# 💬 CryptoChat

CryptoChat est un projet académique conçu pour illustrer les concepts suivants :
- Communication en **client-serveur** avec Java.
- Utilisation des **sockets TCP** pour l’échange de messages en temps réel.
- **Chiffrement/déchiffrement** des messages (XOR ou AES) pour assurer la confidentialité.
- **Interface graphique (GUI)** simple et intuitive pour le chat.
- Gestion **multi-clients** grâce à des threads.
- **Journalisation (logging)** des échanges côté serveur.

CryptoChat démontre comment allier **réseau, sécurité et UX** pour créer une messagerie locale et éducative.


![Last Commit](https://img.shields.io/github/last-commit/yannArmelGALLIE/CryptoChat?style=for-the-badge&logoColor=white)

![Languages Count](https://img.shields.io/github/languages/count/yannArmelGALLIE/CryptoChat?style=for-the-badge&logoColor=white)

---

## 🚀 Fonctionnalités
- 🔗 Connexion client-serveur via **sockets TCP**  
- 🔐 **Chiffrement/déchiffrement** des messages  
- 👥 Gestion **multi-clients** (plusieurs utilisateurs en simultané)  
- 💻 Interface **GUI** (JavaFX) pour un usage plus simple  
- 📝 **Journalisation** des messages côté serveur  
- 👤 Support de l’**authentification des utilisateurs** *(optionnel)*  
- ⚡ Communication en **temps réel**  

---

## 🏗️ Architecture du projet
```
CryptoChat/
├─ server/
│ └─ ChatServer.java # Serveur TCP
├─ client/
│ ├─ ChatClient.java # Client en console
│ └─ ChatClientGUI.java # Client avec interface graphique
├─ security/
│ └─ Crypto.java # Chiffrement/déchiffrement
└─ utils/
  └─ Logger.java # Gestion des logs
```

## 📦 Installation et exécution

### 1. Cloner le projet
```bash
git clone https://github.com/votre-compte/CryptoChat.git
cd CryptoChat
```
### 2. Compiler le serveur
```
javac server/ChatServer.java security/Crypto.java utils/Logger.java
```
### 3. Lancer le serveur
```
java server.ChatServer
```
### 4. Compiler le client (GUI)
```
javac client/ChatClientGUI.java security/Crypto.java
```

### 5. Lancer le client
```
java client.ChatClientGUI`
```

## 👨‍💻 Auteur

RTELCloud est développé par : 
Koffi Yann-Armel GALLIE – [github](https://github.com/yannArmelGALLIE/)


