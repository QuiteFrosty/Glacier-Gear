# Glacier Gear 3.0.0 — "Ice overhaul"

Follow-up to the 2.1.0 Frostbite/Glacial Pulse redesign. Scope, per your direction: keep the
"Glacier Gear" name for now, expand the ice content broadly (tier, blocks, mobs, deeper
integration), and add 1.20.1 builds alongside 1.21.4.

## New tier: Permafrost

A second, stronger gear tier above Glacier — gives players a progression path instead of one
flat set.

- **Permafrost Shard** — new material, dropped by Permafrost Ore and the new Frost Wraith mob.
- **Permafrost armor/tools** — higher stats than Glacier, crafted by surrounding a Glacier
  armor/tool piece with Permafrost Shards (a real upgrade recipe, not a from-scratch craft —
  mirrors how vanilla's netherite upgrade reuses your existing diamond gear).
- **Deeper Frostbite integration**: Permafrost weapons apply Frostbite starting at amplifier 1
  (skip the weak stage) and deal +50% damage to already-frozen (amplifier 3 / "frozen solid")
  targets — a reason to pair Permafrost weapons with anything that inflicts Frostbite, including
  your own Glacial Pulse.
- **Permafrost set bonus**: everything Glacial Pulse does, plus fire resistance while worn (deep
  ice resists deep heat) and a stronger pulse (bigger radius, higher Frostbite amplifier, shorter
  cooldown) than the base Glacier set.

## New blocks

- **Permafrost Ore** — world-gen ore, placed in cold biomes (frozen peaks, ice spikes, snowy
  plains, frozen ocean) via a data-driven biome modifier, not custom worldgen code. Drops
  Permafrost Shard, needs an iron-tier pickaxe or better.
- **Packed Permafrost** — building block crafted from Permafrost Shards, the tier-2 analog of
  Packed Ice.
- **Glacial Crystal Block** — decorative, light-emitting (level 10) block crafted from Permafrost
  Shards. Existing content in the mod had nothing worth building with; this gives builders a
  reason to install it beyond gear.

## New mobs

Both reuse an existing vanilla entity's model/renderer with a re-skin, rather than inventing new
model geometry from scratch — new custom model geometry is the single highest-risk thing to write
correctly without being able to render and check it in-game, so this design deliberately avoids it.

- **Frost Wraith** (hostile) — extends `Stray` (already thematically a "cold" skeleton archer that
  applies Slowness): reskinned, spawns in frozen biomes/frozen ocean, drops Permafrost Shard +
  chance of Ice Cube.
- **Snow Hare** (passive) — extends `Rabbit`: reskinned, spawns in snowy biomes, drops the new Ice
  Fur item, breeds normally.

## New material: Ice Fur

Dropped by Snow Hare. Used in one new recipe: Ice Fur + Leather → a cold-resistant leather variant
recipe hook (kept intentionally small — this is the "deeper item interaction" the postmortem asked
for without inventing a whole new armor line for it).

## Version support

This build adds 1.20.1 alongside the existing 1.21.4 target, across all three loaders (six build
targets total). 1.20.1 predates Mojang's 1.21.2 equipment/armor-material rewrite, so it is **not**
a copy-paste of the 1.21.4 source — armor material and tool material use the older
`ArmorMaterial`/`Tier` APIs. See `docs/PORTING_1.20.1.md` for what specifically differs.

## Confidence / risk (additive to docs/DESIGN.md)

- **New items/blocks (data-driven recipes, tags, loot tables)** — low risk, same pattern as what
  already shipped and matches vanilla JSON schemas.
- **Permafrost gear code** — low risk, near-identical to the already-drafted Glacier gear code.
- **Mob reskins** — medium risk. Extending `Stray`/`Rabbit` and overriding `getTextureLocation()`
  in a renderer is a standard, well-documented pattern, but the exact renderer constructor
  signatures and spawn-egg/spawn-placement registration calls were written from training
  knowledge, not verified against the actual 1.21.4/1.20.1 jars.
- **Placeholder textures** — the new blocks/mobs ship with flat-color placeholder PNGs (clearly
  labeled below), not real art. They exist so the project is texture-complete enough to load
  without a missing-texture purple/black checker, not as finished assets.
- **Worldgen (ore placement via biome modifier)** — medium risk: correct JSON schema in principle,
  but ore generation is the part of Minecraft's data-driven systems most sensitive to exact key
  names per version and is the first thing to check if a test world doesn't generate the ore.
