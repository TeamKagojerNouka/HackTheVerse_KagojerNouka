Kagojer Nouka's project for HackTheVerse
========================================

This project contains Team Kagojer Nouka's code for HackTheVerse. To get a better understanding of what we did, you can view [this short video](https://drive.google.com/file/d/1EV2TCK2Ui41bQqcJb3l_J8fAYBQYJ_8e/view). If you are more of a reader than a video-guy, read more about this project **[here](https://drive.google.com/file/d/1BdtuCk-HngjvCdV_R5fWpH2VlrNcLnfE/view?usp=sharing)**

## Project Structure
- **`backend`** contains the django backend
  - `api` source code for django-rest-framework application. Contains the db models, serializers, views and url endpoints.
    - `migrations` contains the database migration files
    - `management/commands` contains django-admin commands
  - `backend` django project folder [(README)](https://github.com/TeamKagojerNouka/HackTheVerse_KagojerNouka/blob/master/backend/readme.md)
- **`frontend`** contains the frontend vue.js dashboard app [(README)](https://github.com/TeamKagojerNouka/HackTheVerse_KagojerNouka/tree/master/frontend)
  - `src`- contains multiple subdirectories
    - `api` contains all api calls to backend using axios
    - `assets` contains application logo 
    - `components` contains Vue component files. These are kept in separate subfolders based on which view uses a certain component.
    - `constants` contains some arrays/lists used in the project
    - `plugins` contains plugin configurations (only vuetify in this project)
    - `router` contains routing configuration of the application using vue-router
    - `views` contains route-level vue components which consist of one or more small components

- **`android`** - contains the android app ([Android Repository](https://github.com/TeamKagojerNouka/HackTheVerse_KagojerNoukaAndroid))
  - `/app/src/main/java/com/kagojernouka` directory contains the user interfaces, view models, models, database etc. It has four sub-directories:
    - `data` directory contains the local model classes, constants & the remote response models
    - `views` directory contains all the ui related codes, fragments, activities, dialogs etc
    - `utils` directory contains the utility classes for the project
      - `api` directory contains the apiclient and apiservices for retrofit and rxjava2
    - `app` contains the base mulidexapplication for this app 
      
  - `app/src/main/res` contains the xml resources for this project i.e xml ui layouts, drawables, themes, colors and strings.


Team Members:
=============
1. Tahmeed Tarek
2. Kh. Ashiqur Rahman
3. Priyeta Saha
4. Md. Zunaed Karim

