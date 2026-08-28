# Glacier Gear

Ice-themed armor and tools for Minecraft. This repo previously held only a placeholder README —
the mod's actual code only existed as the compiled 2.0.0 jar published to Modrinth. This commit
recovers that source (decompiled from the jar, since no original source or MCreator project file
existed anywhere) and adds the mechanical hook the mod launch was missing. See
[`docs/DESIGN.md`](docs/DESIGN.md) for the full rationale.

## Layout

- `neoforge/` — primary port, closest to the original 2.0.0 release. Highest confidence.
- `forge/` — Forge 1.21.4 port of the same source, adapted registration/event APIs.
- `fabric/` — Fabric port, built against Mojang's official mappings (not Yarn) so the code stays
  identical to the other two loaders.
- `docs/DESIGN.md` — what changed mechanically and why, plus a confidence/risk breakdown per file.
- `docs/LISTING.md` — rewritten Modrinth description and tags.
- `docs/PROMOTION.md` — drafted posts for community promotion.

## Status: unbuilt

This code was written in a sandbox with no network access to `maven.neoforged.net`,
`maven.minecraftforge.net`, `maven.fabricmc.net`, or `libraries.minecraft.net` — it has **not**
been compiled, run, or tested in-game. It's a strong first draft, not a finished build. Before
publishing anything:

1. Open each of `neoforge/`, `forge/`, `fabric/` in an IDE with the appropriate toolchain (or run
   `./gradlew build` with network access) and fix whatever the real compiler finds.
2. Pay special attention to the `NOTE (verify locally)` comments — they mark the specific lines
   most likely to need adjustment (Forge's `RegistryObject#getHolder()` accessor name, Fabric's
   official-mappings setup, and armor client-render registration on all three loaders).
3. Test in a real client/server: Frostbite stacking and freeze, Glacial Pulse triggering below
   30% HP, Chilling Touch freezing water, and — specifically — that Packed Ice Extractor now
   drops a bonus Ice Shard (previously a dead code path in 2.0.0, see `docs/DESIGN.md`).
4. Only then update the Modrinth listing (`docs/LISTING.md`) and post the promotion drafts
   (`docs/PROMOTION.md`).

## Version support

`neoforge/`, `forge/`, and `fabric/` all currently target Minecraft 1.21.4 only, matching the
mod's original release. Widening the supported Minecraft version range (the other postmortem
recommendation) means maintaining additional per-version branches once this baseline is verified
working — deliberately left as a follow-up rather than multiplying the amount of unverified code
in this change.
