# KCUtils

KCUtils is a PaperMC plugin for common utilities, related to the KonaCraft server.

## Features

- IP Banning with reason support.
- Plugin information display.
- Built for Minecraft 1.21.1.

## Commands

| Command | Description | Usage | Permission | Aliases |
|---------|-------------|-------|------------|---------|
| `/ban-ip` | Ban a player or IP | `/ban-ip <IP or player> [reason]` | `KCUtils.banip` | banip |
| `/about` | Shows information about the plugin | `/about` | `KCUtils.about` | - |
| `/warn` | Warn a player | `/warn <player> [reason]` | `KCUtils.warn` | - |

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

