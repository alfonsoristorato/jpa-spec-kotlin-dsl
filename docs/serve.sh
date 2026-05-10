#!/bin/sh
set -e

ROOT="$(cd "$(dirname "$0")/.." && pwd)"

if [ ! -d "$ROOT/.venv" ]; then
  python3 -m venv "$ROOT/.venv"
fi

. "$ROOT/.venv/bin/activate"
pip install -q -r "$ROOT/requirements.txt"
mkdocs serve --config-file "$ROOT/mkdocs.yml"
