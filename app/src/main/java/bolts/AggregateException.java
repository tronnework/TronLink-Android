package bolts;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
public class AggregateException extends Exception {
    private static final String DEFAULT_MESSAGE = "There were multiple errors.";
    private static final long serialVersionUID = 1;
    private List<Throwable> innerThrowables;

    public List<Throwable> getInnerThrowables() {
        return this.innerThrowables;
    }

    public AggregateException(String str, Throwable[] thArr) {
        this(str, Arrays.asList(thArr));
    }

    public AggregateException(String str, List<? extends Throwable> list) {
        super(str, (list == null || list.size() <= 0) ? null : list.get(0));
        this.innerThrowables = Collections.unmodifiableList(list);
    }

    public AggregateException(List<? extends Throwable> list) {
        this(DEFAULT_MESSAGE, list);
    }

    @Override
    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        int i = -1;
        for (Throwable th : this.innerThrowables) {
            printStream.append(StringUtils.LF);
            printStream.append("  Inner throwable #");
            i++;
            printStream.append((CharSequence) Integer.toString(i));
            printStream.append(": ");
            th.printStackTrace(printStream);
            printStream.append(StringUtils.LF);
        }
    }

    @Override
    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        int i = -1;
        for (Throwable th : this.innerThrowables) {
            printWriter.append(StringUtils.LF);
            printWriter.append("  Inner throwable #");
            i++;
            printWriter.append((CharSequence) Integer.toString(i));
            printWriter.append(": ");
            th.printStackTrace(printWriter);
            printWriter.append(StringUtils.LF);
        }
    }

    @Deprecated
    public List<Exception> getErrors() {
        ArrayList arrayList = new ArrayList();
        List<Throwable> list = this.innerThrowables;
        if (list == null) {
            return arrayList;
        }
        for (Throwable th : list) {
            if (th instanceof Exception) {
                arrayList.add((Exception) th);
            } else {
                arrayList.add(new Exception(th));
            }
        }
        return arrayList;
    }

    @Deprecated
    public Throwable[] getCauses() {
        List<Throwable> list = this.innerThrowables;
        return (Throwable[]) list.toArray(new Throwable[list.size()]);
    }
}
