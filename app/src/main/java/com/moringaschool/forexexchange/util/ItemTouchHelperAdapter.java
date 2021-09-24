package com.moringaschool.forexexchange.util;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPositionToPosition);
    void onItemDismiss(int position);
}
