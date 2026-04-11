#!/bin/bash
set -e

REPO_URL="https://github.com/amartinsmg/mathlib.git"
BASE_DIR="$(pwd)"
EXTERNAL_DIR="$BASE_DIR/external"
LIB_NAME="mathlib-c"
DEST_DIR="$BASE_DIR/src/main/resources"

mkdir -p "$EXTERNAL_DIR"

if [ ! -d "$EXTERNAL_DIR/$LIB_NAME" ]; then
  echo "Cloning native repository..."
  git clone "$REPO_URL" "$EXTERNAL_DIR/$LIB_NAME"
else
  echo "Updating native repository..."
  cd "$EXTERNAL_DIR/$LIB_NAME" && git pull && cd "$BASE_DIR"
fi

cd "$EXTERNAL_DIR/$LIB_NAME"
make clean
make test > /dev/null
echo "Compiling native library..."
make

echo "Copying libmathlib.so to project resources..."
mkdir -p "$DEST_DIR"
cp lib/libmathlib.so "$DEST_DIR"

echo "Native library is ready at $DEST_DIR/libmathlib.so"