package org.tron.common.crypto.spi;

import java.util.function.Supplier;
import org.tron.common.crypto.FunctionEncoder;
public interface FunctionEncoderProvider extends Supplier<FunctionEncoder> {
}
