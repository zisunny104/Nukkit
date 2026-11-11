package cn.nukkit.block;

public class BlockPoplarButton extends BlockButtonWooden {

    public BlockPoplarButton() {
        this(0);
    }

    public BlockPoplarButton(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Poplar Button";
    }

    @Override
    public int getId() {
        return POPLAR_BUTTON;
    }
}
