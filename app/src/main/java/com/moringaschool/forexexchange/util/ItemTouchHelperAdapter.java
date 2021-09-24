package com.moringaschool.forexexchange.util;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int ToPosition);
    void onItemDismiss(int position);
}
