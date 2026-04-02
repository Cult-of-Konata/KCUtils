# KCUtils
[![Build and Release](https://github.com/Cult-of-Konata/KCUtils/actions/workflows/build.yml/badge.svg)](https://github.com/Cult-of-Konata/KCUtils/actions/workflows/build.yml)
[![Publish to Hangar](https://github.com/Cult-of-Konata/KCUtils/actions/workflows/publish.yml/badge.svg)](https://github.com/Cult-of-Konata/KCUtils/actions/workflows/publish.yml)

[![KCUtils](https://img.shields.io/hangar/dt/KCUtils?link=https%3A%2F%2Fhangar.papermc.io%2FCult-of-Konata%2FKCUtils&style=flat)](https://hangar.papermc.io/Cult-of-Konata/KCUtils)
[![KCUtils](https://img.shields.io/hangar/stars/KCUtils?link=https%3A%2F%2Fhangar.papermc.io%2FCult-of-Konata%2FKCUtils&style=flat)](https://hangar.papermc.io/Cult-of-Konata/KCUtils)
[![KCUtils](https://img.shields.io/hangar/views/KCUtils?link=https%3A%2F%2Fhangar.papermc.io%2FCult-of-Konata%2FKCUtils&style=flat)](https://hangar.papermc.io/Cult-of-Konata/KCUtils)

KCUtils is a PaperMC plugin for common utilities, related to the KonaCraft server.

## Features

- IP Banning with reason support.
- Plugin information display.
- Built for Minecraft 1.21.11.

## Commands

| Command | Description | Usage | Permission | Aliases |
|---------|-------------|-------|------------|---------|
| `/ban-ip` | Ban a player or IP | `/ban-ip <IP or player> [reason]` | `KCUtils.banip` | banip |
| `/about` | Shows information about the plugin | `/about` | `KCUtils.about` | - |
| `/warn` | Warn a player | `/warn <player> [reason]` | `KCUtils.warn` | - |
| `/warnings` | Shows a player's warnings | `/warnings <player>` | `KCUtils.warnings` | - |
| `/warnclear` | Clears a player's warnings | `/warnclear <player>` | `KCUtils.warnclear` | - |
| `/kcutilsupdate` | Checks for updates for KCUtils | `/kcutilsupdate` | `KCUtils.update` | - |

## Build

To build the plugin, use Gradle:

```bash
./gradlew build
```

The compiled JAR will be located in `build/libs/`.

## CI/CD

This project uses GitHub Actions for continuous build and documentation updates. The workflow:
- Compiles the code using Java 21 on every push to `master`.
- Automatically updates the command reference in `README.md` from `plugin.yml`.
- Uploads the built JAR as an artifact.

