package gamemechanic;

import java.io.Serializable;

public class StepPosition implements Serializable {
    private static final long serialVersionUID = 12345L;
    private String role;
    private int x;
    private int y;

    public StepPosition(String role, int x, int y) {
        this.role = role;
        this.x = x;
        this.y = y;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return role + "-" + x + "-" + y;
    }
}
