# ClaimViz

A client-side Fabric mod for Minecraft 1.21.x that visualizes GriefPrevention land claims and live player positions by reading data from a server's [SquareMap](https://modrinth.com/plugin/squaremap) web map. No server-side component required.

---

## Features

### Claim Borders
- Colored 3D border lines rendered around every nearby claim
- **Color coded by ownership:**
  - Purple - your own claims
  - Teal - admin/server claims
  - Yellow - claims you are currently standing inside (others')
  - SquareMap color - all other players' claims
- Floating owner name labels along each border edge, repeating at a configurable interval
- Configurable render distance

### Player Tracking
- Live player positions fetched from SquareMap and rendered in the world
- **Per-player overlays:**
  - Skin face icon projected onto the HUD at each player's world position
  - Health cross marker (color shifts green → red based on health)
  - Yaw direction tick above the health cross
  - Floating name tag billboard
- Configurable render distance (50–25,000 blocks)

### Action Bar Messages
- Enter/leave messages when crossing claim boundaries, color coded by claim type
- Optional persistent claim bar showing which claim you're currently standing in

### Xaero's Minimap Integration *(optional)*
- Player positions shown as live dots on the minimap radar
- Claim centers added as color-coded waypoints (purple = own, teal = admin, green = others)

### Keybinds
| Key | Action |
|-----|--------|
| `C` | Toggle claim border visibility |
| `P` | Toggle player overlay visibility |

---

## Requirements

| Dependency | Required | Notes |
|---|---|---|
| Fabric Loader ≥ 0.15 | Yes | |
| Fabric API | Yes | |
| Java 21 | Yes | |
| [ModMenu](https://modrinth.com/mod/modmenu) | Recommended | Required to access the config screen in-game |
| [Cloth Config](https://modrinth.com/mod/cloth-config) | Recommended | Required for the config screen UI |
| [Xaero's Minimap](https://modrinth.com/mod/xaeros-minimap) | Optional | Enables player radar and claim waypoints |

Your server must have [SquareMap](https://modrinth.com/plugin/squaremap) installed and publicly accessible. ClaimViz reads claim and player data directly from SquareMap's web API - nothing is installed on the server side.

---

## Setup

1. Install ClaimViz and the recommended dependencies into your Fabric mods folder.
2. Launch the game and join your server.
3. Open **Mods → ClaimViz → Config** (requires ModMenu + Cloth Config).
4. Under **Servers**, click **+ Add New Server** and fill in:
   - **Server Address** - a substring of your server's IP (e.g. `play.example.com`)
   - **SquareMap URL** - the base URL of your server's SquareMap web map, no trailing slash (e.g. `https://map.example.com`)
5. Save. Claims and players will load automatically on your next join.

---

## Configuration

All settings are per-server and configured through the in-game ModMenu screen.

| Setting | Default | Description |
|---|---|---|
| Enabled | `true` | Toggle this server entry on/off without deleting it |
| Claim Refresh Interval | `120s` | How often claim data is re-fetched from SquareMap |
| Show Claims | `true` | Render claim border lines |
| Claim Owner Labels | `true` | Show floating owner name labels on claim borders |
| Label Spacing | `12 blocks` | Distance between repeated owner labels along an edge |
| Show Players | `true` | Render live player overlays |
| Player Render Distance | `500 blocks` | Max distance at which player overlays are rendered (50–25,000) |
| Claim Enter/Leave Messages | `true` | Action bar message on claim boundary crossing |
| Persistent Claim Bar | `false` | Continuously show the current claim on the action bar |
| Xaero Waypoints | `true` | Sync claim waypoints to Xaero's Minimap |
| [EXPERIMENTAL] Terrain-Following Borders | `false` | Render claim borders that follow ground elevation - CPU intensive |

A global **Claim Render Distance** setting (default 200 blocks) is also available under the General category.

---

## Building

Requires Java 21 (tested with Eclipse Temurin 21).

```bash
./gradlew build
```

Output jar is in `build/libs/`.

---

## License

MIT - see [LICENSE](LICENSE).
