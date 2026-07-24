# Hands-On 3 — Branching and Merging

## Completed work

`GitNewBranch` was created from `master`, used to add feature documentation, merged back into `master`, and deleted after the merge. The commit graph preserves this workflow.

## Commands used

```bash
git branch GitNewBranch
git branch -a
git switch GitNewBranch
git add feature-notes.txt
git commit -m "Add notes from GitNewBranch"
git status
git switch master
git diff master..GitNewBranch
git merge --no-ff GitNewBranch -m "Merge GitNewBranch into master"
git log --oneline --graph --decorate
git branch -d GitNewBranch
git status
```

## P4Merge comparison

If P4Merge is configured, compare the branches visually with:

```bash
git mergetool master GitNewBranch
```
