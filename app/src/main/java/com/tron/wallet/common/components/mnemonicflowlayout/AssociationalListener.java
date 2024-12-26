package com.tron.wallet.common.components.mnemonicflowlayout;

import java.util.ArrayList;
public interface AssociationalListener {
    void hasMistake(int i);

    void onTagChanged();

    void update(String str, ArrayList arrayList);
}
