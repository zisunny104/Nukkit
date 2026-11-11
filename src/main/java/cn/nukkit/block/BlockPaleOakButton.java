package cn.nukkit.block;

public class BlockPaleOakButton extends BlockButtonWooden {

    public BlockPaleOakButton() {
        this(0);
    }

    public BlockPaleOakButton(int meta) {
        super(meta);
    }

    @Override
    public String getName() {
        return "Pale Oak Button";
    }

    @Override
    public int getId() {
        return PALE_OAK_BUTTON;
    }
}
