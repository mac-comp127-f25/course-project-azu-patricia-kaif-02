public interface Chip {
    /**
     *  This method checks if there is a space the chip can move into in a digonal matter. 
     * If there is another chip? If yes, is there space beyond that chip to move to?
     */ 
    void move();

    /**
     * When chip 1 jumps over the respective chip, then this one will get remove
     */
    void remove();

    /*
     * When a chip gets to the opposite side of the board, then it becomes king
     */
    void promote();
}
