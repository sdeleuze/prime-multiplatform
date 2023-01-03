## Purpose

The purpose of the repository is to compare build time and performances of Kotlin on various platforms:
 - Kotlin/JVM
 - Kotlin/Native
 - Kotlin/JVM compiled to native using GraalVM native image compiler
 - Kotlin/Wasm

It is based on simple program generating pairs of twin primes from https://discuss.kotlinlang.org/t/why-is-kotlin-native-much-slower-than-jvm/10226/10.

## Data points

| Platform            | Build time | Execution time | Artifact size  | Peak RSS |
|---------------------|------------|----------------|----------------|----------|
| Kotlin/JVM          | 3.6s       | 1m13s          | 1.9 MB + JDK   | 398 MB   |
| GraalVM CE native   | 15.9s      | 1m40s          | 12.4 MB        | 60 MB    |
| GraalVM EE native   | 16.5s      | 1m33s          | 6.8 MB         | 59 MB    |
| Kotlin/Native       | 9s         | 1m37s          | 0.57 MB        | 4 MB     |
| Kotlin/Wasm preview | 5s         | 2m49s          | 0.18 MB + Node | 120 MB   |

## Environment
- Linux 5.15.85-1-lts x86_64
- AMD Ryzen 9 5900X 12-Core Processor 32 GB RAM
- Kotlin 1.8.0
- Java 17.0.5 (Liberica JDK)
- GraalVM 22.3 Java 17

## Getting started

[![Compile and run](https://github.com/sdeleuze/prime-multiplatform/actions/workflows/main.yml/badge.svg)](https://github.com/sdeleuze/prime-multiplatform/actions/workflows/main.yml)

Notice I was not able to use a single Gradle multiplatform build due to a conflict between Kotlin/Native and Native Build Tools plugins. 

### Kotlin/JVM

Compile
```
./mvnw clean package
```
Run
```
java -jar target/prime-multiplatform-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Kotlin/JVM with GraalVM native image 

Compile
```
./mvnw clean native:compile
```
Run
```
target/prime-multiplatform 
```

### Kotlin/Native

Compile
```
./gradlew clean linkReleaseExecutableNative
```
Run
```
build/bin/native/releaseExecutable/prime-multiplatform.kexe
```

### Kotlin/Wasm

Compile
```
./gradlew clean compileProductionExecutableKotlinWasm
```
Run
```
./gradlew wasmNodeProductionRun
```
