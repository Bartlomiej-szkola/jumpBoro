package game.mechanics;

import game.entities.Player;

public class Movement {
    private final Player player;
    private final Gravity gravity;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean chargingJump = false;
    private boolean jumping = false;
    private boolean jumpingLeft = false;
    private boolean jumpingRight = false;

    // Trzeba naprawić że podczas spradania można się ruszać lewo prawo


    private double currentJumpHeight = 0;
    private double jumpVerticalSpeed = 0;

    public Movement(Player player, Gravity gravity) {
        this.player = player;
        this.gravity = gravity;
    }

    // --------------------------------- STEROWANIE LEWO-PRAWO ----------------------------------
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
        player.faceLeft();
    }
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
        player.faceRight();
    }

    private void handleHorizontalMovement(int panelWidth) {
        //---------------------------- MOVEMENT LEWO-PRAWO ----------------------------
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        if(chargingJump || jumping) return; // Blokada ruszania podczas ładowania skoku i skakania !!! NIE MA BLOKADY NA OPADANIE !!!

        if (movingLeft) dx -= effectiveSpeed;
        if (movingRight) dx += effectiveSpeed;

        player.moveX(dx);

        //---------------------------- OGRANICZENIA ---------------------------
        if (player.getX() < 0) { // po lewej
            player.moveX(-player.getX());
        }
        if (player.getX() > panelWidth - player.getWidth()){ // po prawej
            player.moveX(panelWidth - player.getWidth() - player.getX());
        }
    }

    private void handleHorizontalMovementWhileJumping(int panelWidth) {
        double dx = 0;
        double effectiveSpeed = player.getBaseSpeed() * player.getSpeedMultiplier();

        if (jumpingLeft) dx -= effectiveSpeed;
        if (jumpingRight) dx += effectiveSpeed;

        player.moveX(dx);

        if (player.getX() < 0) { // po lewej
            player.moveX(-player.getX());
        }
        if (player.getX() > panelWidth - player.getWidth()){ // po prawej
            player.moveX(panelWidth - player.getWidth() - player.getX());
        }
    }

    // -------------------------- STROWANIE SKOKIEM --------------------

    private void handleJump(int panelWidth) { //****************** WYWOŁYWANE CYKLICZNIE *****************
        // ładowanie skoku
        if (chargingJump) { // zwiększanie wysokości skoku podczas trzymania spacji (chargingJump == true)
            currentJumpHeight += player.getChargeSpeed(); // zwiększa się o wartość zależną od postaci raz na każdą klatkę

            if (currentJumpHeight > player.getMaxJumpHeight()){ // gracz nie może wyskorzyć wyżej niż ma zapisaną maksymalna wysokość skoku
                currentJumpHeight = player.getMaxJumpHeight();
            }

            jumpingLeft = movingLeft;
            jumpingRight = movingRight;
            // przy puszczeniu spacji, odwrotność currentJumpHeight przypisuje się do zmiennej jumpVerticalSpeed, a currentJumpHeight jest ustawiane na 0
        }

        // faktyczny skok (wznoszenie)
        if (jumping) {
            player.moveY(jumpVerticalSpeed); // cylkiczne zwiekszanie wysokosci gracza
            jumpVerticalSpeed += gravity.getGravityForce(); // spowolnienie wznoszenia z czasem - ładniejsza animacja
            handleHorizontalMovementWhileJumping(panelWidth);

            // osiągnięcie szczytu lotu
            if (jumpVerticalSpeed >= 0) { // kiedy prędkość gracza zmniejszy się do zera, kończymy skok
                jumping = false;
                jumpingLeft = false;
                jumpingRight = false;
            }
        }
    }

    public boolean isJumping(){return jumping;}

    // --------------------------- ŁADOWANIE SKOKU - KEYLISTENERY  -------------------
    public void startChargingJump() {
        if (!jumping) {
            chargingJump = true;
            player.setBeforeJumpImage();
        }
    }

    public void releaseJump() {
        if (chargingJump && !jumping) {
            chargingJump = false;
            jumping = true;
            jumpVerticalSpeed = -(currentJumpHeight * gravity.getGravityForce()); // moc skoku zależna od ładowania (DO POPRAWY BO PRZY NISKIEJ GRAWITACJI NISKO SIE SKACZE)
            currentJumpHeight = 0;
            player.setJumpingImage();
        }
    }


    // ---------------------------- AKTUALIZACJA RUCHU ------------------------------
    public void update(int panelWidth) {
        handleHorizontalMovement(panelWidth);
        handleJump(panelWidth);
    }

    public boolean isJumpingLeft() {
        return jumpingLeft;
    }

    public boolean isJumpingRight() {
        return jumpingRight;
    }
}
