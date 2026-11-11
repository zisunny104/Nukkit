# 分支說明（origin = zisunny104/Nukkit）

整合分支是 `local/all-fixes`，佈署都從它建置。上游 `CloudburstMC/Nukkit` 只讀取，不推送（本機 upstream 的推送網址已設為無效）。
上游現況：`master` 只比共同基底多 1 個提交（改用 NetherNet、Java 17+，#2296），我們已有對應的 `merge/nethernet-2296`，與 all-fixes 試合併沒有衝突。

## 整合與鏡像（保留）
- `local/all-fixes`：佈署用的整合分支。
- `master`：上游鏡像，不在此開發。

## 進行中（尚未合併，保留）
- `feature/redstone-comparator`、`feature/redstone-piston`：紅石元件行為檢查，等完成後合併。
- `docs/states-remaining`：剩餘缺少狀態的方塊清單。

## 已被取代（內容已在 all-fixes，可刪）
- `feature/states-bookshelf`、`-fire`、`-redstone`、`-tripwire`、`-sculk-crafter`：調色盤二進位檔彼此衝突，已由 `feature/states-combined` 一次整合取代。

## 已合併（ahead=0，可刪）
- 方塊：`feature/blocks-*`（flora、meta、concrete-wool、copper、resin、stone、rest、pale-moss、pale-moss2、fix-stairs-chorus、behavior-*、r2-*、r3-*）。
- 狀態：`feature/states-combined`、`-frame`、`-hangingsign`。
- 物品與創造欄：`feature/items-*`、`feature/creative-items`、`-fallback`、`feature/command-enum-names`。
- 配方：`feature/recipes-deprecated`、`-official`。
- 紅石：`feature/redstone-bulb-rod`、`-torch-burnout`。
- 生成：`feature/golem-spawn`、`-spawn2`。
- 修正：`fix/concrete-powder`、`fix/copper-chest-palette`、`fix/item-id-collision`、`fix/connection-states`、`fix/leaf-persistent-flags`。
- 其他：`chore/cleanup-dead-code`、`feature/docs-capture-round2`。

## 較舊或與上游有落差（需您決定）
- `fix/untranslated-messages`、`i18n/cht-update`：10/30 的繁中文字修正，`i18n/cht-update` 有 1 個未合併提交。
- `merge/nethernet-2296`：上游 NetherNet 變更的合併分支，上游已併入該內容，可在確認後刪。
- `pr-2290-screenshots`：向上游提的 PR 分支，保留。
