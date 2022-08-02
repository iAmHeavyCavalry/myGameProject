package utilz;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{

        public static final int IDLE = 0;
        public static final int ATTACK = 1;
        public static final int RUNNING = 2;
        public static final int STOP = 3;
        public static final int DEATH = 4;
        public static final int BLOCK = 5;
        public static final int HIT = 6;
        public static final int JUMP = 7;
        //get is Get to differentiate that it's static
        public static int GetSpriteAmount(int player_action){

            return switch (player_action) {
                case RUNNING, JUMP -> 10;
                case IDLE, HIT -> 4;
                case ATTACK -> 17;
                case STOP -> 8;
                case BLOCK -> 13;
                case DEATH -> 12;
                default -> player_action;
            };
        }
    }
}

