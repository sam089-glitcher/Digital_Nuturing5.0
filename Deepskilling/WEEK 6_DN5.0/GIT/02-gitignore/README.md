# Hands-On 2 — Ignoring Unwanted Files

## Completed work

The `.gitignore` file excludes all files with the `.log` extension and the complete `logs/` folder. The sample log files were intentionally left untracked and `git status --ignored` can be used to verify that the rules apply.

## Commands used

```bash
git status
git status --ignored
git add .gitignore README.md app.txt
git commit -m "Add ignore rules for logs"
git status
```

## Expected verification

- `application.log` is ignored because of `*.log`.
- `logs/runtime.txt` is ignored because of `logs/`.
- `app.txt`, `.gitignore`, and this README are tracked.
