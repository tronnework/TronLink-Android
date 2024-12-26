package com.tron.wallet.common.components.frescoimage.indicator;

import android.view.View;
import com.tron.wallet.common.components.frescoimage.view.BigImageView;
public interface ProgressIndicator {
    View getView(BigImageView bigImageView);

    void onFinish();

    void onProgress(int i);

    void onStart();
}
