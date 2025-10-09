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
└─ client/
    ├─ChatConnection.java
    ├─Session.java
    ├─Login.java
    ├─ChatScene.java
    ├─ ChatClient.java # Client en console
    └─ ChatClientGUI.java # Client avec interface graphique


```

## 📦 Installation et exécution

### 1. Cloner le projet
```bash
git clone https://github.com/votre-compte/CryptoChat.git
cd CryptoChat
```
### 2. Compiler et lancer le serveur
```
cd server
javac ChatServer.java 
java ChatServer
```
### 3. Compiler et lancer le client (console)
```
cd client
javac ChatConnection.java ChatClient.java
java ChatClient
```
### 4. Compiler et lancer le client (GUI)
```
cd client
javac ChatConnection.java Session.java
javac --module-path /c/openjdk/lib --add-modules javafx.controls,javafx.fxml ChatClientGUI.java ChatScene.java Login.java ChatScene.java
java --module-path /c/openjdk/lib --add-modules javafx.controls,javafx.fxml ChatClientGUI
```

## 👨‍💻 Auteurs

<p>RTELCloud est développé par :</p><br>
<p>Koffi Yann-Armel GALLIE – [github](https://github.com/yannArmelGALLIE/)</p><br>
<p>Kouakou Paul Oswald Djônon KOUASSI – [github](https://github.com/djonon0210/)</p><br>
<p>Diallo Gilles N’GASSA – [github](https://github.com/Gilles19F/)</p>



