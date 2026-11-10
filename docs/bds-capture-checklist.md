# BDS 抓包清單（一次做完）

連線：用 ProxyPass 連 127.0.0.1:19150（環境依 skill bds-capture）。每項一行：操作 → 要看的封包。

1. pale_moss_carpet：放在地面，旁邊放實心方塊，再放高一格的牆 → UpdateBlock 的 block states（四邊 none/short/tall、upper_block_bit）
2. pale_hanging_moss：放在天花板，再接一個在下面 → UpdateBlock 的 tip 狀態
3. shelf（橡木）：放置、右鍵放入物品、用紅石訊號觸發、與相鄰 shelf 相連 → UpdateBlock、ContainerOpen/InventoryContent、BlockActorData（槽位 NBT）
4. 銅 chest：放置、開啟、放入物品、雙連 → UpdateBlock、ContainerOpen、BlockActorData、BlockEventPacket
5. copper_golem_statue：放置、右鍵、用斧頭 → UpdateBlock（姿勢狀態）、BlockActorData
6. hanging sign（pale_oak）：放置於天花板、牆邊、編輯文字 → UpdateBlock、OpenSign、BlockActorData
7. 銅方塊氧化／打蠟：蜜脾打蠟、斧頭刮蠟與氧化層 → UpdateBlock 前後方塊名稱、LevelEvent（粒子）
8. 野花／落葉堆：連續對同一格放置 4 次、骨粉 → UpdateBlock 的 growth 值、LevelEvent
9. 樹苗（pale_oak）長成樹：骨粉 → 一連串 UpdateBlock（取樹形座標）
10. chalkboard、creaking_heart、dried_ghast、disc_press、icicle、straw_bed：各放一個並互動 → UpdateBlock、BlockActorData

# 第二輪（對不上或推測的項目）

連線方式同第一輪。每項一行：操作 → 要看的封包。

1. shelf 朝向：同一個位置，面向北、東、南、西各放一次（視角也各換兩種，例如偏左、偏右）→ UpdateBlock 的 cardinal_direction 與放置時的 PlayerAuthInput 視角
2. shelf 連接：兩個以上 shelf 相鄰、對其中一個通紅石 → UpdateBlock 的 powered_bit、powered_shelf_type
3. hanging sign 牆面：貼在東、西、南、北四個牆面各放一次 → UpdateBlock 的 facing_direction、ground_sign_direction
4. hanging sign 天花板：從方塊下方放置，視角四個方向，並接在另一個 hanging sign 或鏈子上 → UpdateBlock 的 hanging、attached_bit、ground_sign_direction
5. hanging sign 文字：放置後編輯正面與背面 → OpenSign、BlockEntityData 的 FrontText、BackText
6. 樓梯（橡木）方向：站在四個方向各放一次，從方塊上半部與下半部點擊，再放在天花板下 → UpdateBlock 的 weirdo_direction、upside_down_bit
7. 樓梯轉角：放一排直樓梯後，在旁邊以不同方向放第二個，做出內角與外角（左右各一）→ UpdateBlock 的 minecraft:corner（none、inner_left、inner_right、outer_left、outer_right），並看相鄰第一個樓梯是否也收到 UpdateBlock
8. 樓梯位置：樓梯放在半磚、雕像、箱子旁邊，再把相鄰方塊拆掉 → 轉角是否還原（UpdateBlock）
9. 哥萊植物（chorus plant）：放在終界石上、旁邊再接一個、上方再接一個，再從不同面放 → UpdateBlock 的六個 minecraft:connection_* 狀態
10. 哥萊植物破壞：拆掉中間一個、拆掉底部 → 後續掉落與其他方塊連鎖破壞的 UpdateBlock，以及 AddItemEntity 的掉落物
11. 哥萊花（chorus flower）：放在終界石上，等隨機生長數分鐘（可用 `/gamerule randomtickspeed 100`）→ UpdateBlock 的 age 變化、新方塊位置
12. 哥萊花骨粉與破壞：對它用骨粉、拆掉、用箭射擊 → UpdateBlock、掉落物、LevelEvent

## 核心目前看得出的問題（尚未修）

stairs（`BlockStairs`）
- `getMinY`／`getMaxY` 的運算式 `this.y + (this.getDamage() & 0x04) > 0 ? ...` 因運算子優先順序，條件幾乎永遠成立，結果與 y 座標有關，程式內也已標 TODO。
- 官方 palette 的樓梯有 `minecraft:corner` 狀態（none、inner_left、inner_right、outer_left、outer_right），共 40 個狀態；核心只存方向與上下半，沒有轉角狀態與轉角碰撞箱，鄰居變動時也不重算形狀。
- 碰撞只處理直樓梯的階梯，沒有轉角版本。

chorus（`BlockChorusPlant`、`BlockChorusFlower`）
- chorus plant 在官方有六個連接狀態（`minecraft:connection_up/down/north/south/east/west`，64 個狀態）；核心的 `BlockChorusPlant` 沒有 meta，不存連接，也沒有依鄰居更新連接或對應的碰撞箱。
- chorus flower 有 age 0 到 5，核心有成長、死亡邏輯，但生長規則是參考 Java 版（隨機數與高度限制），與 BDS 是否一致沒驗證。
- 沒有骨粉處理，也沒有箭等投射物擊破 chorus flower 的處理（待抓包確認）。
- 掉落：chorus plant 隨機掉 chorus fruit，silk touch 掉自己，與官方是否一致沒驗證。
