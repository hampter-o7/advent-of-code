package year2016.day10;

public class Bot {
    private int botNumber;
    private int[] heldChips = { -1, -1 };
    private boolean heldChipsDistributed = false;
    private Bot low = null;
    private Bot high = null;

    public Bot(int botNumber) {
        this.botNumber = botNumber;
        if (botNumber < 0) {
            heldChipsDistributed = true;
        }
    }

    public boolean addHeldChip(int newHeldChip) {
        this.heldChips[this.heldChips[0] == -1 ? 0 : 1] = newHeldChip;
        return this.heldChips[1] != -1;
    }

    public void distributeChips() {
        if (heldChips[1] == -1 || this.heldChipsDistributed) {
            return;
        }
        this.heldChipsDistributed = true;
        if (low.addHeldChip(Integer.min(heldChips[0], heldChips[1]))) {
            low.distributeChips();
        }
        if (high.addHeldChip(Integer.max(heldChips[0], heldChips[1]))) {
            high.distributeChips();
        }
    }

    public void setLow(Bot low) {
        this.low = low;
    }

    public void setHigh(Bot high) {
        this.high = high;
    }

    public int getBotNumber() {
        return this.botNumber;
    }

    public int[] getHeldChips() {
        return heldChips;
    }

    public Bot getLow() {
        return this.low;
    }

    public Bot getHigh() {
        return this.high;
    }

    public String toString() {
        return String.format("bot%d, held chips =[%d, %d], distributed = %s, low bot: %d, high bot: %d", this.botNumber,
                this.heldChips[0], this.heldChips[1], this.heldChipsDistributed ? "true" : "false",
                (low != null) ? low.botNumber : -1, high != null ? high.botNumber : -1);
    }
}
