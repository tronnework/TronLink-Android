package com.tron.wallet.common.components.frescoimage;

import android.content.Context;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.tron.wallet.common.components.frescoimage.utils.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
public abstract class ImageDownloadSubscriber extends BaseDataSubscriber<CloseableReference<PooledByteBuffer>> {
    private volatile boolean mFinished;
    private final File mTempFile;

    protected abstract void onFail(Throwable th);

    protected abstract void onProgress(int i);

    protected abstract void onSuccess(File file);

    public ImageDownloadSubscriber(Context context) {
        File cacheDir = context.getCacheDir();
        this.mTempFile = new File(cacheDir, "" + System.currentTimeMillis() + ".png");
    }

    @Override
    public void onProgressUpdate(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        if (this.mFinished) {
            return;
        }
        onProgress((int) (dataSource.getProgress() * 100.0f));
    }

    @Override
    protected void onNewResultImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        PooledByteBufferInputStream pooledByteBufferInputStream;
        Throwable th;
        IOException e;
        FileOutputStream fileOutputStream;
        if (!dataSource.isFinished() || dataSource.getResult() == null) {
            return;
        }
        try {
            try {
                pooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer) ((CloseableReference) dataSource.getResult()).get());
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                fileOutputStream = new FileOutputStream(this.mTempFile);
                try {
                    IOUtils.copy(pooledByteBufferInputStream, fileOutputStream);
                    this.mFinished = true;
                    onSuccess(this.mTempFile);
                    dataSource = fileOutputStream;
                } catch (IOException e2) {
                    e = e2;
                    onFail(e);
                    dataSource = fileOutputStream;
                    IOUtils.closeQuietly(pooledByteBufferInputStream);
                    IOUtils.closeQuietly((OutputStream) dataSource);
                }
            } catch (IOException e3) {
                e = e3;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                dataSource = 0;
                IOUtils.closeQuietly(pooledByteBufferInputStream);
                IOUtils.closeQuietly((OutputStream) dataSource);
                throw th;
            }
        } catch (IOException e4) {
            pooledByteBufferInputStream = null;
            e = e4;
            fileOutputStream = null;
        } catch (Throwable th4) {
            pooledByteBufferInputStream = null;
            th = th4;
            dataSource = 0;
        }
        IOUtils.closeQuietly(pooledByteBufferInputStream);
        IOUtils.closeQuietly((OutputStream) dataSource);
    }

    @Override
    protected void onFailureImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource) {
        this.mFinished = true;
        onFail(new RuntimeException("onFailureImpl"));
    }
}
