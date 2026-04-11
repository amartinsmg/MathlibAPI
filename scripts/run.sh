#!/bin/bash
set -e

BASE_DIR="$(pwd)"
RESOURCES_DIR="$BASE_DIR/src/main/resources"
LIB_DIR="$BASE_DIR/lib"
JAR_PATH="$BASE_DIR/target/mathlib-api-0.1.jar"
NATIVE_LIB="$RESOURCES_DIR/libmathlib.so"

# ─────────────────────────────────────────────
# 1. Ensure the native library exists
# ─────────────────────────────────────────────
if [ ! -f "$NATIVE_LIB" ]; then
  echo "Native library not found. Running setup-native.sh first..."
  bash "$BASE_DIR/scripts/setup-native.sh"
fi

# ─────────────────────────────────────────────
# 2. Build the fat JAR
# ─────────────────────────────────────────────
echo "Building project..."
mvn -q package -DskipTests

# ─────────────────────────────────────────────
# 3. Extract the .so to lib/ for JNA
#    (JNA cannot load .so directly from inside a JAR)
# ─────────────────────────────────────────────
mkdir -p "$LIB_DIR"
cp "$NATIVE_LIB" "$LIB_DIR/libmathlib.so"

# ─────────────────────────────────────────────
# 4. Run
# ─────────────────────────────────────────────
echo "Starting server..."
java -Djava.library.path="$LIB_DIR" -jar "$JAR_PATH"