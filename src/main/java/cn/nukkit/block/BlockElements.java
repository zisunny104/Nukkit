package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.utils.BlockColor;

/**
 * The education edition element blocks, one class per element because every block class has a fixed id.
 */
public final class BlockElements {

    private BlockElements() {
    }

    public abstract static class Element extends BlockSolid {

        public abstract int getElement();

        @Override
        public String getName() {
            return "Element " + getElement();
        }

        @Override
        public double getHardness() {
            return 0;
        }

        @Override
        public double getResistance() {
            return 0;
        }

        @Override
        public boolean canHarvestWithHand() {
            return true;
        }

        @Override
        public BlockColor getColor() {
            return BlockColor.STONE_BLOCK_COLOR;
        }
    }

    public static class E0 extends Element {
        @Override
        public int getId() {
            return 36;
        }

        @Override
        public int getElement() {
            return 0;
        }
    }

    public static class E1 extends Element {
        @Override
        public int getId() {
            return 267;
        }

        @Override
        public int getElement() {
            return 1;
        }
    }

    public static class E2 extends Element {
        @Override
        public int getId() {
            return 268;
        }

        @Override
        public int getElement() {
            return 2;
        }
    }

    public static class E3 extends Element {
        @Override
        public int getId() {
            return 269;
        }

        @Override
        public int getElement() {
            return 3;
        }
    }

    public static class E4 extends Element {
        @Override
        public int getId() {
            return 270;
        }

        @Override
        public int getElement() {
            return 4;
        }
    }

    public static class E5 extends Element {
        @Override
        public int getId() {
            return 271;
        }

        @Override
        public int getElement() {
            return 5;
        }
    }

    public static class E6 extends Element {
        @Override
        public int getId() {
            return 272;
        }

        @Override
        public int getElement() {
            return 6;
        }
    }

    public static class E7 extends Element {
        @Override
        public int getId() {
            return 273;
        }

        @Override
        public int getElement() {
            return 7;
        }
    }

    public static class E8 extends Element {
        @Override
        public int getId() {
            return 274;
        }

        @Override
        public int getElement() {
            return 8;
        }
    }

    public static class E9 extends Element {
        @Override
        public int getId() {
            return 275;
        }

        @Override
        public int getElement() {
            return 9;
        }
    }

    public static class E10 extends Element {
        @Override
        public int getId() {
            return 276;
        }

        @Override
        public int getElement() {
            return 10;
        }
    }

    public static class E11 extends Element {
        @Override
        public int getId() {
            return 277;
        }

        @Override
        public int getElement() {
            return 11;
        }
    }

    public static class E12 extends Element {
        @Override
        public int getId() {
            return 278;
        }

        @Override
        public int getElement() {
            return 12;
        }
    }

    public static class E13 extends Element {
        @Override
        public int getId() {
            return 279;
        }

        @Override
        public int getElement() {
            return 13;
        }
    }

    public static class E14 extends Element {
        @Override
        public int getId() {
            return 280;
        }

        @Override
        public int getElement() {
            return 14;
        }
    }

    public static class E15 extends Element {
        @Override
        public int getId() {
            return 281;
        }

        @Override
        public int getElement() {
            return 15;
        }
    }

    public static class E16 extends Element {
        @Override
        public int getId() {
            return 282;
        }

        @Override
        public int getElement() {
            return 16;
        }
    }

    public static class E17 extends Element {
        @Override
        public int getId() {
            return 283;
        }

        @Override
        public int getElement() {
            return 17;
        }
    }

    public static class E18 extends Element {
        @Override
        public int getId() {
            return 284;
        }

        @Override
        public int getElement() {
            return 18;
        }
    }

    public static class E19 extends Element {
        @Override
        public int getId() {
            return 285;
        }

        @Override
        public int getElement() {
            return 19;
        }
    }

    public static class E20 extends Element {
        @Override
        public int getId() {
            return 286;
        }

        @Override
        public int getElement() {
            return 20;
        }
    }

    public static class E21 extends Element {
        @Override
        public int getId() {
            return 287;
        }

        @Override
        public int getElement() {
            return 21;
        }
    }

    public static class E22 extends Element {
        @Override
        public int getId() {
            return 288;
        }

        @Override
        public int getElement() {
            return 22;
        }
    }

    public static class E23 extends Element {
        @Override
        public int getId() {
            return 289;
        }

        @Override
        public int getElement() {
            return 23;
        }
    }

    public static class E24 extends Element {
        @Override
        public int getId() {
            return 290;
        }

        @Override
        public int getElement() {
            return 24;
        }
    }

    public static class E25 extends Element {
        @Override
        public int getId() {
            return 291;
        }

        @Override
        public int getElement() {
            return 25;
        }
    }

    public static class E26 extends Element {
        @Override
        public int getId() {
            return 292;
        }

        @Override
        public int getElement() {
            return 26;
        }
    }

    public static class E27 extends Element {
        @Override
        public int getId() {
            return 293;
        }

        @Override
        public int getElement() {
            return 27;
        }
    }

    public static class E28 extends Element {
        @Override
        public int getId() {
            return 294;
        }

        @Override
        public int getElement() {
            return 28;
        }
    }

    public static class E29 extends Element {
        @Override
        public int getId() {
            return 295;
        }

        @Override
        public int getElement() {
            return 29;
        }
    }

    public static class E30 extends Element {
        @Override
        public int getId() {
            return 296;
        }

        @Override
        public int getElement() {
            return 30;
        }
    }

    public static class E31 extends Element {
        @Override
        public int getId() {
            return 297;
        }

        @Override
        public int getElement() {
            return 31;
        }
    }

    public static class E32 extends Element {
        @Override
        public int getId() {
            return 298;
        }

        @Override
        public int getElement() {
            return 32;
        }
    }

    public static class E33 extends Element {
        @Override
        public int getId() {
            return 299;
        }

        @Override
        public int getElement() {
            return 33;
        }
    }

    public static class E34 extends Element {
        @Override
        public int getId() {
            return 300;
        }

        @Override
        public int getElement() {
            return 34;
        }
    }

    public static class E35 extends Element {
        @Override
        public int getId() {
            return 301;
        }

        @Override
        public int getElement() {
            return 35;
        }
    }

    public static class E36 extends Element {
        @Override
        public int getId() {
            return 302;
        }

        @Override
        public int getElement() {
            return 36;
        }
    }

    public static class E37 extends Element {
        @Override
        public int getId() {
            return 303;
        }

        @Override
        public int getElement() {
            return 37;
        }
    }

    public static class E38 extends Element {
        @Override
        public int getId() {
            return 304;
        }

        @Override
        public int getElement() {
            return 38;
        }
    }

    public static class E39 extends Element {
        @Override
        public int getId() {
            return 305;
        }

        @Override
        public int getElement() {
            return 39;
        }
    }

    public static class E40 extends Element {
        @Override
        public int getId() {
            return 306;
        }

        @Override
        public int getElement() {
            return 40;
        }
    }

    public static class E41 extends Element {
        @Override
        public int getId() {
            return 307;
        }

        @Override
        public int getElement() {
            return 41;
        }
    }

    public static class E42 extends Element {
        @Override
        public int getId() {
            return 308;
        }

        @Override
        public int getElement() {
            return 42;
        }
    }

    public static class E43 extends Element {
        @Override
        public int getId() {
            return 309;
        }

        @Override
        public int getElement() {
            return 43;
        }
    }

    public static class E44 extends Element {
        @Override
        public int getId() {
            return 310;
        }

        @Override
        public int getElement() {
            return 44;
        }
    }

    public static class E45 extends Element {
        @Override
        public int getId() {
            return 311;
        }

        @Override
        public int getElement() {
            return 45;
        }
    }

    public static class E46 extends Element {
        @Override
        public int getId() {
            return 312;
        }

        @Override
        public int getElement() {
            return 46;
        }
    }

    public static class E47 extends Element {
        @Override
        public int getId() {
            return 313;
        }

        @Override
        public int getElement() {
            return 47;
        }
    }

    public static class E48 extends Element {
        @Override
        public int getId() {
            return 314;
        }

        @Override
        public int getElement() {
            return 48;
        }
    }

    public static class E49 extends Element {
        @Override
        public int getId() {
            return 315;
        }

        @Override
        public int getElement() {
            return 49;
        }
    }

    public static class E50 extends Element {
        @Override
        public int getId() {
            return 316;
        }

        @Override
        public int getElement() {
            return 50;
        }
    }

    public static class E51 extends Element {
        @Override
        public int getId() {
            return 317;
        }

        @Override
        public int getElement() {
            return 51;
        }
    }

    public static class E52 extends Element {
        @Override
        public int getId() {
            return 318;
        }

        @Override
        public int getElement() {
            return 52;
        }
    }

    public static class E53 extends Element {
        @Override
        public int getId() {
            return 319;
        }

        @Override
        public int getElement() {
            return 53;
        }
    }

    public static class E54 extends Element {
        @Override
        public int getId() {
            return 320;
        }

        @Override
        public int getElement() {
            return 54;
        }
    }

    public static class E55 extends Element {
        @Override
        public int getId() {
            return 321;
        }

        @Override
        public int getElement() {
            return 55;
        }
    }

    public static class E56 extends Element {
        @Override
        public int getId() {
            return 322;
        }

        @Override
        public int getElement() {
            return 56;
        }
    }

    public static class E57 extends Element {
        @Override
        public int getId() {
            return 323;
        }

        @Override
        public int getElement() {
            return 57;
        }
    }

    public static class E58 extends Element {
        @Override
        public int getId() {
            return 324;
        }

        @Override
        public int getElement() {
            return 58;
        }
    }

    public static class E59 extends Element {
        @Override
        public int getId() {
            return 325;
        }

        @Override
        public int getElement() {
            return 59;
        }
    }

    public static class E60 extends Element {
        @Override
        public int getId() {
            return 326;
        }

        @Override
        public int getElement() {
            return 60;
        }
    }

    public static class E61 extends Element {
        @Override
        public int getId() {
            return 327;
        }

        @Override
        public int getElement() {
            return 61;
        }
    }

    public static class E62 extends Element {
        @Override
        public int getId() {
            return 328;
        }

        @Override
        public int getElement() {
            return 62;
        }
    }

    public static class E63 extends Element {
        @Override
        public int getId() {
            return 329;
        }

        @Override
        public int getElement() {
            return 63;
        }
    }

    public static class E64 extends Element {
        @Override
        public int getId() {
            return 330;
        }

        @Override
        public int getElement() {
            return 64;
        }
    }

    public static class E65 extends Element {
        @Override
        public int getId() {
            return 331;
        }

        @Override
        public int getElement() {
            return 65;
        }
    }

    public static class E66 extends Element {
        @Override
        public int getId() {
            return 332;
        }

        @Override
        public int getElement() {
            return 66;
        }
    }

    public static class E67 extends Element {
        @Override
        public int getId() {
            return 333;
        }

        @Override
        public int getElement() {
            return 67;
        }
    }

    public static class E68 extends Element {
        @Override
        public int getId() {
            return 334;
        }

        @Override
        public int getElement() {
            return 68;
        }
    }

    public static class E69 extends Element {
        @Override
        public int getId() {
            return 335;
        }

        @Override
        public int getElement() {
            return 69;
        }
    }

    public static class E70 extends Element {
        @Override
        public int getId() {
            return 336;
        }

        @Override
        public int getElement() {
            return 70;
        }
    }

    public static class E71 extends Element {
        @Override
        public int getId() {
            return 337;
        }

        @Override
        public int getElement() {
            return 71;
        }
    }

    public static class E72 extends Element {
        @Override
        public int getId() {
            return 338;
        }

        @Override
        public int getElement() {
            return 72;
        }
    }

    public static class E73 extends Element {
        @Override
        public int getId() {
            return 339;
        }

        @Override
        public int getElement() {
            return 73;
        }
    }

    public static class E74 extends Element {
        @Override
        public int getId() {
            return 340;
        }

        @Override
        public int getElement() {
            return 74;
        }
    }

    public static class E75 extends Element {
        @Override
        public int getId() {
            return 341;
        }

        @Override
        public int getElement() {
            return 75;
        }
    }

    public static class E76 extends Element {
        @Override
        public int getId() {
            return 342;
        }

        @Override
        public int getElement() {
            return 76;
        }
    }

    public static class E77 extends Element {
        @Override
        public int getId() {
            return 343;
        }

        @Override
        public int getElement() {
            return 77;
        }
    }

    public static class E78 extends Element {
        @Override
        public int getId() {
            return 344;
        }

        @Override
        public int getElement() {
            return 78;
        }
    }

    public static class E79 extends Element {
        @Override
        public int getId() {
            return 345;
        }

        @Override
        public int getElement() {
            return 79;
        }
    }

    public static class E80 extends Element {
        @Override
        public int getId() {
            return 346;
        }

        @Override
        public int getElement() {
            return 80;
        }
    }

    public static class E81 extends Element {
        @Override
        public int getId() {
            return 347;
        }

        @Override
        public int getElement() {
            return 81;
        }
    }

    public static class E82 extends Element {
        @Override
        public int getId() {
            return 348;
        }

        @Override
        public int getElement() {
            return 82;
        }
    }

    public static class E83 extends Element {
        @Override
        public int getId() {
            return 349;
        }

        @Override
        public int getElement() {
            return 83;
        }
    }

    public static class E84 extends Element {
        @Override
        public int getId() {
            return 350;
        }

        @Override
        public int getElement() {
            return 84;
        }
    }

    public static class E85 extends Element {
        @Override
        public int getId() {
            return 351;
        }

        @Override
        public int getElement() {
            return 85;
        }
    }

    public static class E86 extends Element {
        @Override
        public int getId() {
            return 352;
        }

        @Override
        public int getElement() {
            return 86;
        }
    }

    public static class E87 extends Element {
        @Override
        public int getId() {
            return 353;
        }

        @Override
        public int getElement() {
            return 87;
        }
    }

    public static class E88 extends Element {
        @Override
        public int getId() {
            return 354;
        }

        @Override
        public int getElement() {
            return 88;
        }
    }

    public static class E89 extends Element {
        @Override
        public int getId() {
            return 355;
        }

        @Override
        public int getElement() {
            return 89;
        }
    }

    public static class E90 extends Element {
        @Override
        public int getId() {
            return 356;
        }

        @Override
        public int getElement() {
            return 90;
        }
    }

    public static class E91 extends Element {
        @Override
        public int getId() {
            return 357;
        }

        @Override
        public int getElement() {
            return 91;
        }
    }

    public static class E92 extends Element {
        @Override
        public int getId() {
            return 358;
        }

        @Override
        public int getElement() {
            return 92;
        }
    }

    public static class E93 extends Element {
        @Override
        public int getId() {
            return 359;
        }

        @Override
        public int getElement() {
            return 93;
        }
    }

    public static class E94 extends Element {
        @Override
        public int getId() {
            return 360;
        }

        @Override
        public int getElement() {
            return 94;
        }
    }

    public static class E95 extends Element {
        @Override
        public int getId() {
            return 361;
        }

        @Override
        public int getElement() {
            return 95;
        }
    }

    public static class E96 extends Element {
        @Override
        public int getId() {
            return 362;
        }

        @Override
        public int getElement() {
            return 96;
        }
    }

    public static class E97 extends Element {
        @Override
        public int getId() {
            return 363;
        }

        @Override
        public int getElement() {
            return 97;
        }
    }

    public static class E98 extends Element {
        @Override
        public int getId() {
            return 364;
        }

        @Override
        public int getElement() {
            return 98;
        }
    }

    public static class E99 extends Element {
        @Override
        public int getId() {
            return 365;
        }

        @Override
        public int getElement() {
            return 99;
        }
    }

    public static class E100 extends Element {
        @Override
        public int getId() {
            return 366;
        }

        @Override
        public int getElement() {
            return 100;
        }
    }

    public static class E101 extends Element {
        @Override
        public int getId() {
            return 367;
        }

        @Override
        public int getElement() {
            return 101;
        }
    }

    public static class E102 extends Element {
        @Override
        public int getId() {
            return 368;
        }

        @Override
        public int getElement() {
            return 102;
        }
    }

    public static class E103 extends Element {
        @Override
        public int getId() {
            return 369;
        }

        @Override
        public int getElement() {
            return 103;
        }
    }

    public static class E104 extends Element {
        @Override
        public int getId() {
            return 370;
        }

        @Override
        public int getElement() {
            return 104;
        }
    }

    public static class E105 extends Element {
        @Override
        public int getId() {
            return 371;
        }

        @Override
        public int getElement() {
            return 105;
        }
    }

    public static class E106 extends Element {
        @Override
        public int getId() {
            return 372;
        }

        @Override
        public int getElement() {
            return 106;
        }
    }

    public static class E107 extends Element {
        @Override
        public int getId() {
            return 373;
        }

        @Override
        public int getElement() {
            return 107;
        }
    }

    public static class E108 extends Element {
        @Override
        public int getId() {
            return 374;
        }

        @Override
        public int getElement() {
            return 108;
        }
    }

    public static class E109 extends Element {
        @Override
        public int getId() {
            return 375;
        }

        @Override
        public int getElement() {
            return 109;
        }
    }

    public static class E110 extends Element {
        @Override
        public int getId() {
            return 376;
        }

        @Override
        public int getElement() {
            return 110;
        }
    }

    public static class E111 extends Element {
        @Override
        public int getId() {
            return 377;
        }

        @Override
        public int getElement() {
            return 111;
        }
    }

    public static class E112 extends Element {
        @Override
        public int getId() {
            return 378;
        }

        @Override
        public int getElement() {
            return 112;
        }
    }

    public static class E113 extends Element {
        @Override
        public int getId() {
            return 379;
        }

        @Override
        public int getElement() {
            return 113;
        }
    }

    public static class E114 extends Element {
        @Override
        public int getId() {
            return 380;
        }

        @Override
        public int getElement() {
            return 114;
        }
    }

    public static class E115 extends Element {
        @Override
        public int getId() {
            return 381;
        }

        @Override
        public int getElement() {
            return 115;
        }
    }

    public static class E116 extends Element {
        @Override
        public int getId() {
            return 382;
        }

        @Override
        public int getElement() {
            return 116;
        }
    }

    public static class E117 extends Element {
        @Override
        public int getId() {
            return 383;
        }

        @Override
        public int getElement() {
            return 117;
        }
    }

    public static class E118 extends Element {
        @Override
        public int getId() {
            return 384;
        }

        @Override
        public int getElement() {
            return 118;
        }
    }

    public static void register(Class[] list) {
        list[36] = E0.class;
        list[267] = E1.class;
        list[268] = E2.class;
        list[269] = E3.class;
        list[270] = E4.class;
        list[271] = E5.class;
        list[272] = E6.class;
        list[273] = E7.class;
        list[274] = E8.class;
        list[275] = E9.class;
        list[276] = E10.class;
        list[277] = E11.class;
        list[278] = E12.class;
        list[279] = E13.class;
        list[280] = E14.class;
        list[281] = E15.class;
        list[282] = E16.class;
        list[283] = E17.class;
        list[284] = E18.class;
        list[285] = E19.class;
        list[286] = E20.class;
        list[287] = E21.class;
        list[288] = E22.class;
        list[289] = E23.class;
        list[290] = E24.class;
        list[291] = E25.class;
        list[292] = E26.class;
        list[293] = E27.class;
        list[294] = E28.class;
        list[295] = E29.class;
        list[296] = E30.class;
        list[297] = E31.class;
        list[298] = E32.class;
        list[299] = E33.class;
        list[300] = E34.class;
        list[301] = E35.class;
        list[302] = E36.class;
        list[303] = E37.class;
        list[304] = E38.class;
        list[305] = E39.class;
        list[306] = E40.class;
        list[307] = E41.class;
        list[308] = E42.class;
        list[309] = E43.class;
        list[310] = E44.class;
        list[311] = E45.class;
        list[312] = E46.class;
        list[313] = E47.class;
        list[314] = E48.class;
        list[315] = E49.class;
        list[316] = E50.class;
        list[317] = E51.class;
        list[318] = E52.class;
        list[319] = E53.class;
        list[320] = E54.class;
        list[321] = E55.class;
        list[322] = E56.class;
        list[323] = E57.class;
        list[324] = E58.class;
        list[325] = E59.class;
        list[326] = E60.class;
        list[327] = E61.class;
        list[328] = E62.class;
        list[329] = E63.class;
        list[330] = E64.class;
        list[331] = E65.class;
        list[332] = E66.class;
        list[333] = E67.class;
        list[334] = E68.class;
        list[335] = E69.class;
        list[336] = E70.class;
        list[337] = E71.class;
        list[338] = E72.class;
        list[339] = E73.class;
        list[340] = E74.class;
        list[341] = E75.class;
        list[342] = E76.class;
        list[343] = E77.class;
        list[344] = E78.class;
        list[345] = E79.class;
        list[346] = E80.class;
        list[347] = E81.class;
        list[348] = E82.class;
        list[349] = E83.class;
        list[350] = E84.class;
        list[351] = E85.class;
        list[352] = E86.class;
        list[353] = E87.class;
        list[354] = E88.class;
        list[355] = E89.class;
        list[356] = E90.class;
        list[357] = E91.class;
        list[358] = E92.class;
        list[359] = E93.class;
        list[360] = E94.class;
        list[361] = E95.class;
        list[362] = E96.class;
        list[363] = E97.class;
        list[364] = E98.class;
        list[365] = E99.class;
        list[366] = E100.class;
        list[367] = E101.class;
        list[368] = E102.class;
        list[369] = E103.class;
        list[370] = E104.class;
        list[371] = E105.class;
        list[372] = E106.class;
        list[373] = E107.class;
        list[374] = E108.class;
        list[375] = E109.class;
        list[376] = E110.class;
        list[377] = E111.class;
        list[378] = E112.class;
        list[379] = E113.class;
        list[380] = E114.class;
        list[381] = E115.class;
        list[382] = E116.class;
        list[383] = E117.class;
        list[384] = E118.class;
    }
}
