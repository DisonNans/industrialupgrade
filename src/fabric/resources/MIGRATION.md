# Industrial Upgrade — Fabric 1.20.1 migration

The Fabric 1.20.1 port is standalone and does **not** require IndustrialCraft 2.

## Compatibility rule

IndustrialCraft 2 is not a dependency of the Fabric port and must not be required at runtime or compile time.

The original 1.12.2 implementation is used only as a behavioural reference while porting mechanics.

## Replacements for the original IC2/Forge APIs

- IC2 Energy -> Industrial Upgrade's own energy implementation, with a Fabric-compatible external energy bridge where useful.
- IC2 machine registry -> Fabric Registry.
- IC2 TileEntity -> Minecraft 1.20.1 BlockEntity.
- IC2 inventory slots -> native inventories plus Fabric Transfer API where appropriate.
- IC2 recipes -> native/custom Fabric recipe implementations.
- Forge OreDictionary -> Minecraft tags.
- Forge fluids -> Minecraft/Fabric fluid APIs.
- Forge GUI/container -> Screen + ScreenHandler.
- Forge network -> Fabric networking.
- Forge world generation -> 1.20.1 placed/configured feature system.

## Behavioural verification

For every migrated machine or subsystem, values and state transitions are compared with the 1.12.2 source before being considered complete. This includes processing time, energy consumption, storage, outputs, upgrades, NBT state and recipe behaviour.
