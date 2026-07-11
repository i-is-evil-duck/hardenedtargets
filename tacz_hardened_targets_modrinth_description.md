# TaCZ Hardened Targets

A Minecraft Forge 1.20.1 addon for [Timeless and Classics Zero (TaCZ)](https://modrinth.com/mod/timeless-and-classics-zero) that increases target durability.

## What it adds

TaCZ Hardened Targets modifies all TaCZ target entities to require more shots before being knocked down, targets will wobble and react to hits as normal but require more sustained fire to actually fall.

## Installation

1. Install Minecraft Forge 1.20.1
2. Install [Timeless and Classics Zero](https://modrinth.com/mod/timeless-and-classics-zero)
3. Place `tacz-hardened-targets-1.0.0.jar` in your `mods/` folder
4. Restart the game or server

## Configuration

Edit `tacz-hardened-targets.toml` in your config folder to adjust:

- `targetHealthMultiplier` — How many times more durable targets are (default: 3.0x)
- `enableVisualFeedback` — Whether targets show hit sparks or damage indicators

## Why Download

- **More engaging practice** — Targets don't fall over from a single burst
- **Better minigames** — Supports scoring systems based on accuracy and speed
- **Fully compatible** — Works with all TaCZ guns and target types
- **Zero bloat** — Only touches target durability, nothing else

## Important Notes

- **Requires Timeless and Classics Zero (TaCZ) to be installed.** Will not function without it.
- Only affects TaCZ target entities — other blocks or mobs are unchanged
- Config changes require a server restart to take effect
- Multiplier values above 10x may make targets feel excessively tanky
