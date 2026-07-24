# Hands-On 4 — Merge Conflict Resolution

## Completed work

The same `hello.xml` file was changed differently in `master` and `GitWork`, producing a merge conflict. The final version keeps both changes in valid XML and was committed after resolution. Backup files are excluded through `.gitignore`.

## Commands used

```bash
git status
git switch -c GitWork
# edit hello.xml for the branch
git add hello.xml
git commit -m "Update hello message in GitWork"
git switch master
# edit hello.xml differently on master
git add hello.xml
git commit -m "Update hello message on master"
git log --oneline --graph --decorate --all
git diff master..GitWork
git merge GitWork
# resolve the conflict in hello.xml
git add hello.xml
git commit -m "Resolve hello.xml merge conflict"
git add .gitignore README.md
git commit -m "Ignore backup files after conflict resolution"
git branch -d GitWork
git log --oneline --graph --decorate
```

## P4Merge alternative

Run `git mergetool` while the conflict is active to resolve it with a configured 3-way merge tool.
