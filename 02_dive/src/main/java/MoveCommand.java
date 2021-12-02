import java.util.Objects;

public class MoveCommand {

    private MoveEnum moveEnum;
    private int steps;

    public MoveCommand(MoveEnum moveEnum, int steps) {
        this.moveEnum = moveEnum;
        this.steps = steps;
    }

    public MoveEnum getMoveEnum() {
        return moveEnum;
    }

    public void setMoveEnum(MoveEnum moveEnum) {
        this.moveEnum = moveEnum;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveCommand that = (MoveCommand) o;
        return getSteps() == that.getSteps() && getMoveEnum() == that.getMoveEnum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoveEnum(), getSteps());
    }
}
