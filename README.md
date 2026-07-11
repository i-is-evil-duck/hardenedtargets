# Hardened Targets

A Minecraft Forge 1.20.1 addon for [Timeless and Classics Zero (TaCZ)](https://modrinth.com/mod/timeless-and-classics-zero) that adds a reinforced target block requiring multiple hits to knock down.

## Features

- **Reinforced Target Block** — A new double-tall target block that accumulates damage before falling over
- **Configurable Health** — Adjust max health and reset time via config file
- **Player Head Display** — Shows the placing player's skin on the target head
- **Custom Names** — Name your target with an anvil; the name persists on the item
- **Redstone Output** — Emits a redstone signal when knocked down, like the original TaCZ target

## Requirements

- Minecraft 1.20.1
- Minecraft Forge 47.2.0+
- [Timeless and Classics Zero (TaCZ)](https://modrinth.com/mod/timeless-and-classics-zero) 1.0.0+

## Installation

1. Install Minecraft Forge for 1.20.1
2. Install [Timeless and Classics Zero](https://modrinth.com/mod/timeless-and-classics-zero)
3. Download `hardenedtargets-1.0.0.jar` from [Releases](https://github.com/i-is-evil-duck/hardenedtargets/releases/tag/v1.0.0)
4. Place the jar in your `mods/` folder
5. Restart the game

## Configuration

Edit `config/hardenedtargets-common.toml`:

| Option | Default | Description |
|---|---|---|
| `maxHealth` | 20.0 | Damage required before the target falls (20.0 = same as a player) |
| `resetTicks` | 100 | Ticks before the target stands back up (20 ticks = 1 second) |

## Usage

1. Craft or obtain the Reinforced Target item
2. Place it on the ground (it occupies two blocks tall, like the original target)
3. Shoot it — it accumulates damage and falls after reaching the configured health threshold
4. It resets automatically after the configured number of ticks

## License

MIT
