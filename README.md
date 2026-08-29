# Glacier Gear

Ice-themed gear, blocks, and mobs for Minecraft, across NeoForge, Forge, and Fabric.

This repo previously held only a placeholder README — the mod's actual code only existed as the
compiled 2.0.0 jar published to Modrinth. It went through two follow-up passes since:

- **2.1.0** — recovered the source (decompiled from the jar, since no original source or MCreator
  project file existed anywhere) and added the mechanical hook the original release was missing
  (Frostbite, Glacial Pulse, Chilling Touch). See [`docs/DESIGN.md`](docs/DESIGN.md).
- **3.0.0** (current) — a second gear tier (Permafrost), three new blocks, two new mobs, and
  deeper crafting integration. See [`docs/DESIGN_V3.md`](docs/DESIGN_V3.md).

## Layout

- `neoforge/` — primary port, highest confidence, closest to the original release's lineage.
- `forge/` — Forge 1.21.4 port of the same source, adapted registration/event APIs.
- `fabric/` — Fabric port, built against Mojang's official mappings (not Yarn) so the code stays
  identical to the other two loaders.
- `docs/DESIGN.md` / `docs/DESIGN_V3.md` — what changed mechanically and why, plus a
  confidence/risk breakdown per file.
- `docs/LISTING.md` — rewritten Modrinth description and tags (pre-3.0.0; update before
  publishing to mention Permafrost/mobs/blocks).
- `docs/PROMOTION.md` — drafted posts for community promotion.
- `docs/PORTING_1.20.1.md` — scoping notes for the 1.20.1 port, not yet implemented (see
  "Version support" below).

## Status: unbuilt

This code was written in a sandbox with no network access to `maven.neoforged.net`,
`maven.minecraftforge.net`, `maven.fabricmc.net`, or `libraries.minecraft.net` — it has **not**
been compiled, run, or tested in-game. It's a strong first draft, not a finished build. Before
publishing anything:

1. Open each of `neoforge/`, `forge/`, `fabric/` in an IDE with the appropriate toolchain (or run
   `./gradlew build` with network access) and fix whatever the real compiler finds.
2. Pay special attention to the `NOTE (verify locally)` comments — they mark the specific lines
   most likely to need adjustment: Forge's `RegistryObject#getHolder()` accessor name, Fabric's
   official-mappings setup and its no-mixin spawn-placement/fire-immunity workarounds, armor
   client-render registration, and ore/spawn worldgen key names on all three loaders.
3. Test in a real client/server. In addition to the 2.1.0 checklist (Frostbite, Glacial Pulse,
   Chilling Touch, the Packed Ice Extractor bug fix): the Permafrost upgrade recipes, Permafrost's
   fire immunity and stronger pulse, Frost Wraith/Snow Hare spawning and drops, and Permafrost Ore
   generating in cold biomes.
4. Replace the placeholder textures. The new blocks, items, and mob skins added in 3.0.0 ship as
   flat-color placeholder PNGs (see `docs/DESIGN_V3.md`) so the project loads without
   missing-texture errors — they are not finished art.
5. Only then update the Modrinth listing (`docs/LISTING.md`) and post the promotion drafts
   (`docs/PROMOTION.md`).

## Version support

`neoforge/`, `forge/`, and `fabric/` all currently target Minecraft 1.21.4 only. 1.20.1 was in
scope for this pass but is deferred: it predates Mojang's 1.21.2 armor/tool-material rewrite, so
it isn't a copy-paste port like Forge/Fabric were from NeoForge — see
`docs/PORTING_1.20.1.md` for exactly what has to change and where to start.
