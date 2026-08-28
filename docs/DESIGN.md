# Glacier Gear 2.1.0 — "Glacial Frost" redesign

## Why

The postmortem on 2.0.0's low traction (149 downloads / 2 followers) found the mod had no
mechanical reason to exist over vanilla diamond gear — it was a palette swap with slightly
different numbers. This redesign gives it one, without changing what the mod fundamentally is
(ice-themed armor and tools).

## What changed

**Frostbite** (new status effect, `glacier_gear:frostbite`)
- Glacier Sword and Glacier Axe stack it on every hit (amplifier 0→3).
- Each stack slows the target further; at max stack (IV) the target is pinned in place —
  "frozen solid" — for as long as the effect holds.
- Deals periodic freeze damage that scales with amplifier.

**Glacial Pulse** (armor set bonus)
- Wearing the full Glacier armor set grants immunity to freeze/hypothermia damage.
- When the wearer's health drops below 30%, an automatic AoE frost burst fires (30s cooldown):
  every hostile mob within 6 blocks gets hit with Frostbite II and knocked back, with a
  particle/sound cue. It's a panic button that rewards committing to the full set.

**Chilling Touch** (tool passive)
- Glacier Pickaxe and Glacier Shovel have a 15% chance per block mined to freeze adjacent
  source water into ice — a small terraforming utility that didn't exist before.

**Bug fix**
- `PackedIceExtractorItem` shipped in 2.0.0 without ever calling the
  `PackedIceExtractorBlockDestroyedWithToolProcedure` class MCreator generated for it — breaking
  packed ice with that tool silently did nothing extra, despite the item's name and recipe
  implying it should behave like the (working) Ice Extractor. Fixed: it now drops a bonus Ice
  Shard exactly like the Ice Extractor does for regular ice.

**De-branding**
- Java package renamed from `net.mcreator.glaciergear` to `dev.gigastudios.glaciergear`. The
  mod ID (`glacier_gear`) and all existing recipes/tags/item IDs are unchanged, so this is not a
  breaking change for players — only the internal package naming that previously advertised the
  mod as an unedited MCreator export.

## What did not change

Base armor/tool stats, textures, models, recipes, and creative tab layout are untouched — this
is additive, not a rebalance. Existing installs (and the Modrinth listing's crafting recipes,
advancements) keep working the same way.

## Confidence / what needs local verification

This was written and structurally sanity-checked (balanced braces, valid JSON) in a sandbox with
no network access to the Minecraft/NeoForge/Forge/Fabric Maven repos, so **none of it has been
compiled or run**. Rank the risk before assuming any of it works:

1. **Low risk** — item stat classes, procedures, lang file, recipes/tags/textures (unchanged,
   copied verbatim from the working 2.0.0 jar).
2. **Medium risk** — `FrostbiteEffect`, `GlacierCombatUtil`, `GlacierArmorSetBonus`: real,
   plausible 1.21.4 APIs, but the exact method names (`applyEffectTick`,
   `shouldApplyEffectTickThisTick`, `addAttributeModifier`, `LivingIncomingDamageEvent`,
   `PlayerTickEvent.Post`) were written from training knowledge, not verified against the actual
   NeoForge 21.4.137 jar.
3. **Highest risk** — the Forge and Fabric ports' loader-specific glue (`RegistryObject#getHolder()`
   on Forge, Loom's `officialMojangMappings()` on Fabric, and armor client-rendering registration
   on all three). Each has an inline `NOTE (verify locally)` comment at the exact line most likely
   to need adjustment.

Build each loader locally (`./gradlew build`, or open in IntelliJ/the NeoForge template) and fix
compile errors against the real API before publishing — treat this as a strong first draft, not
a finished build.
