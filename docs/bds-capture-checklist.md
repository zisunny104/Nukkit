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
