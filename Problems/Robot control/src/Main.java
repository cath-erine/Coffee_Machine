class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        int x0 = robot.getX();
        int y0 = robot.getY();
        Direction dir0 = robot.getDirection();
        int xSteps = toX - x0;
        int ySteps = toY - y0;

        if (ySteps > 0) {
            switch (dir0) {
                case LEFT: {
                    robot.turnRight();
                    break;
                }
                case RIGHT: {
                    robot.turnLeft();
                    break;
                }
                case DOWN: {
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                }
            }
            for (int i = 0; i < ySteps; i++) {
                robot.stepForward();
            }
        } else {
            switch (dir0) {
                case LEFT: {
                    robot.turnLeft();
                    break;
                }
                case RIGHT: {
                    robot.turnRight();
                    break;
                }
                case UP: {
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                }
            }
            for (int i = 0; i < -ySteps; i++) {
                robot.stepForward();
            }
        }
        dir0 = robot.getDirection();
        if (xSteps > 0) {
            switch (dir0) {
                case LEFT: {
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                }
                case UP: {
                    robot.turnRight();
                    break;
                }
                case DOWN: {
                    robot.turnLeft();
                    break;
                }
            }
            for (int i = 0; i < xSteps; i++) {
                robot.stepForward();
            }
        } else {
            switch (dir0) {
                case RIGHT: {
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                }
                case UP: {
                    robot.turnLeft();
                    break;
                }
                case DOWN: {
                    robot.turnRight();
                    break;
                }
            }
            for (int i = 0; i < -xSteps; i++) {
                robot.stepForward();
            }
        }
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
