package j$.io;

import com.tron.wallet.business.finance.FinanceFragment;
import j$.util.Spliterators;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.io.BufferedReader;
public final class DesugarBufferedReader {
    public static Stream lines(BufferedReader bufferedReader) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DesugarBufferedReaderLinesIterator(bufferedReader), FinanceFragment.STAKE), false);
    }
}
