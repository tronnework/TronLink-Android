package j$.util.stream;

import j$.util.Spliterator;
import j$.util.concurrent.ConcurrentHashMap$SearchEntriesTask$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
abstract class AbstractShortCircuitTask extends AbstractTask {
    protected volatile boolean canceled;
    protected final AtomicReference sharedResult;

    public AbstractShortCircuitTask(AbstractShortCircuitTask abstractShortCircuitTask, Spliterator spliterator) {
        super(abstractShortCircuitTask, spliterator);
        this.sharedResult = abstractShortCircuitTask.sharedResult;
    }

    public AbstractShortCircuitTask(PipelineHelper pipelineHelper, Spliterator spliterator) {
        super(pipelineHelper, spliterator);
        this.sharedResult = new AtomicReference(null);
    }

    public void cancel() {
        this.canceled = true;
    }

    public void cancelLaterNodes() {
        AbstractShortCircuitTask abstractShortCircuitTask = this;
        for (AbstractShortCircuitTask abstractShortCircuitTask2 = (AbstractShortCircuitTask) getParent(); abstractShortCircuitTask2 != null; abstractShortCircuitTask2 = (AbstractShortCircuitTask) abstractShortCircuitTask2.getParent()) {
            if (abstractShortCircuitTask2.leftChild == abstractShortCircuitTask) {
                AbstractShortCircuitTask abstractShortCircuitTask3 = (AbstractShortCircuitTask) abstractShortCircuitTask2.rightChild;
                if (!abstractShortCircuitTask3.canceled) {
                    abstractShortCircuitTask3.cancel();
                }
            }
            abstractShortCircuitTask = abstractShortCircuitTask2;
        }
    }

    @Override
    public void compute() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: j$.util.stream.AbstractShortCircuitTask.compute():void");
    }

    protected abstract Object getEmptyResult();

    @Override
    public Object getLocalResult() {
        if (isRoot()) {
            Object obj = this.sharedResult.get();
            return obj == null ? getEmptyResult() : obj;
        }
        return super.getLocalResult();
    }

    @Override
    public Object getRawResult() {
        return getLocalResult();
    }

    @Override
    public void setLocalResult(Object obj) {
        if (!isRoot()) {
            super.setLocalResult(obj);
        } else if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, null, obj);
        }
    }

    public void shortCircuit(Object obj) {
        if (obj != null) {
            ConcurrentHashMap$SearchEntriesTask$ExternalSyntheticBackportWithForwarding0.m(this.sharedResult, null, obj);
        }
    }

    protected boolean taskCanceled() {
        boolean z = this.canceled;
        if (!z) {
            AbstractTask parent = getParent();
            while (true) {
                AbstractShortCircuitTask abstractShortCircuitTask = (AbstractShortCircuitTask) parent;
                if (z || abstractShortCircuitTask == null) {
                    break;
                }
                z = abstractShortCircuitTask.canceled;
                parent = abstractShortCircuitTask.getParent();
            }
        }
        return z;
    }
}
