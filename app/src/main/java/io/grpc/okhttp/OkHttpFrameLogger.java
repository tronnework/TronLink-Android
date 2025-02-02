package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.grpc.okhttp.internal.framed.Settings;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.ByteString;
public class OkHttpFrameLogger {
    private static final int BUFFER_LENGTH_THRESHOLD = 64;
    private final Level level;
    private final Logger logger;

    public enum Direction {
        INBOUND,
        OUTBOUND
    }

    public OkHttpFrameLogger(Level level, Class<?> cls) {
        this(level, Logger.getLogger(cls.getName()));
    }

    OkHttpFrameLogger(Level level, Logger logger) {
        this.level = (Level) Preconditions.checkNotNull(level, FirebaseAnalytics.Param.LEVEL);
        this.logger = (Logger) Preconditions.checkNotNull(logger, "logger");
    }

    private static String toString(Settings settings) {
        SettingParams[] values;
        EnumMap enumMap = new EnumMap(SettingParams.class);
        for (SettingParams settingParams : SettingParams.values()) {
            if (settings.isSet(settingParams.getBit())) {
                enumMap.put((EnumMap) settingParams, (SettingParams) Integer.valueOf(settings.get(settingParams.getBit())));
            }
        }
        return enumMap.toString();
    }

    private static String toString(Buffer buffer) {
        if (buffer.size() <= 64) {
            return buffer.snapshot().hex();
        }
        int min = (int) Math.min(buffer.size(), 64L);
        return buffer.snapshot(min).hex() + "...";
    }

    private boolean isEnabled() {
        return this.logger.isLoggable(this.level);
    }

    public void logData(Direction direction, int i, Buffer buffer, int i2, boolean z) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " DATA: streamId=" + i + " endStream=" + z + " length=" + i2 + " bytes=" + toString(buffer));
        }
    }

    public void logHeaders(Direction direction, int i, List<Header> list, boolean z) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " HEADERS: streamId=" + i + " headers=" + list + " endStream=" + z);
        }
    }

    public void logPriority(Direction direction, int i, int i2, int i3, boolean z) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " PRIORITY: streamId=" + i + " streamDependency=" + i2 + " weight=" + i3 + " exclusive=" + z);
        }
    }

    public void logRstStream(Direction direction, int i, ErrorCode errorCode) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " RST_STREAM: streamId=" + i + " errorCode=" + errorCode);
        }
    }

    public void logSettingsAck(Direction direction) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " SETTINGS: ack=true");
        }
    }

    public void logSettings(Direction direction, Settings settings) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " SETTINGS: ack=false settings=" + toString(settings));
        }
    }

    public void logPing(Direction direction, long j) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " PING: ack=false bytes=" + j);
        }
    }

    public void logPingAck(Direction direction, long j) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " PING: ack=true bytes=" + j);
        }
    }

    public void logPushPromise(Direction direction, int i, int i2, List<Header> list) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " PUSH_PROMISE: streamId=" + i + " promisedStreamId=" + i2 + " headers=" + list);
        }
    }

    public void logGoAway(Direction direction, int i, ErrorCode errorCode, ByteString byteString) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " GO_AWAY: lastStreamId=" + i + " errorCode=" + errorCode + " length=" + byteString.size() + " bytes=" + toString(new Buffer().write(byteString)));
        }
    }

    public void logWindowsUpdate(Direction direction, int i, long j) {
        if (isEnabled()) {
            Logger logger = this.logger;
            Level level = this.level;
            logger.log(level, direction + " WINDOW_UPDATE: streamId=" + i + " windowSizeIncrement=" + j);
        }
    }

    public enum SettingParams {
        HEADER_TABLE_SIZE(1),
        ENABLE_PUSH(2),
        MAX_CONCURRENT_STREAMS(4),
        MAX_FRAME_SIZE(5),
        MAX_HEADER_LIST_SIZE(6),
        INITIAL_WINDOW_SIZE(7);
        
        private final int bit;

        public int getBit() {
            return this.bit;
        }

        SettingParams(int i) {
            this.bit = i;
        }
    }
}
