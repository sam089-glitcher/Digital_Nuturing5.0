# Hands-On 1 — Git Setup and First Commit

## Completed work

1. Initialised this directory as a Git repository.
2. Configured the repository user identity.
3. Created and committed `welcome.txt`.
4. Verified that the working tree is clean.

## Commands used

```bash
git --version
git config --global user.name "Saumitra"
git config --global user.email "your-email@example.com"
git config --global core.editor "notepad++.exe -multiInst -notabbar -nosession -noPlugin"
git init
git status
git add welcome.txt README.md
git commit -m "Add welcome message"
git status
```

## GitLab connection (run with your own project URL)

```bash
git remote add origin https://gitlab.com/<your-username>/GitDemo.git
git branch -M master
git pull origin master --allow-unrelated-histories
git push -u origin master
```

`notepad++` must be installed and present in the Windows PATH before configuring it as the Git editor.
