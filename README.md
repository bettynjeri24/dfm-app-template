How to clone this project including all its Git Submodules

1. Requirements
For this project to build smoothly, make sure you have been added as a member to these repos:


2. Cloning the project
Run this command on Git Bash (Windows) or Terminal (Mac and Linux) i.e inside the directory you want to clone the project
git clone --recurse-submodules https://
Do some small house-keeping using this command
git submodule update
Create your feature branch and checkout into it.

3. Some important contribution guidelines you need to follow
We settled on a few guidelines to avoid errors while generating the app bundle:

For the individual parent repos e.g. dkimani, maintain the base app module to only have the splash screen and Dagger setup (to avoid having to transfer dependencies from the base app module once we fetch the dkimani module onto the mobile banking repo)
For the dynamic feature modules(inside this repo) and modules to be fetched as a submodule from the individual parent repository e.g. dkimani module, rename files in the Resources folder to end with the module suffix i.e.

Drawable e.g. ic_delete_dkimani (additional suffix being dkimani)
Layout e.g. fragment_main_dkimani
Menu e.g. bottom_navigation_dkimani
Navigation e.g. navigation_graph_dkimani
Raw/anim(animation folder) e.g. fingerprint_dkimani
Fonts e.g. nunito_sans_dkimani



For reference, see what has been done in Diaspora Remittance and Savings submodules.
Happy coding 😎

Some useful Git Submodule commands

1. Add a git submodule into this project.
Use this command to create a new git submodule into your project. Make sure to be in the parent directory of your project
Make sure you have cloning permissions of the repo you want to add as a git submodule
git submodule add <remote-url>

2. Clone a repo including all its submodules.
Use this command to clone an existing repo that contains git submodules. This will clone the repo inclusive of all its submodules
Make sure to have cloning permissions of all the git submodules repos
git clone --recurse-submodules <remote-url>
Then execute:
git submodule update

3. Remove a git submodule from your project.
In your parent directory, execute the following commands in succession:
git rm -r the_submodule_name
Then execute:
rm -rf .git/modules/the_submodule_name