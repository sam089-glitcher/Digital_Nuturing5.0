# Hands-On 5 — Cleanup and Push to Remote

## Completed work

This repository is on a clean `master` branch and has been pushed to the local bare repository `remote-repository.git`. That bare repository represents the remote GitLab project for an offline, reproducible demonstration.

## Commands used

```bash
git status
git branch -a
git pull origin master
git push -u origin master
git log --oneline --decorate -n 5
```

## Using GitLab instead

Replace the local remote with the GitLab project URL and push:

```bash
git remote set-url origin https://gitlab.com/<your-username>/GitDemo.git
git pull origin master
git push -u origin master
```
